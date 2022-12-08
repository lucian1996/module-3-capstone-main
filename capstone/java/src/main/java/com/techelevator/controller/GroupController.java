package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.UtilDao;
import com.techelevator.dao.exceptions.CreateException;
import com.techelevator.dao.exceptions.DeleteException;
import com.techelevator.dao.exceptions.GetException;
import com.techelevator.dao.exceptions.UpdateException;
import com.techelevator.model.Group;
import com.techelevator.model.GroupMember;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/groups")
@PreAuthorize("isAuthenticated()")
public class GroupController {
    private GroupDao groupDao;
    private UserDao userDao;
    private UtilDao utilDao;

    public GroupController(GroupDao groupDao, UserDao userDao, UtilDao utilDao) {
        this.groupDao = groupDao;
        this.userDao = userDao;
        this.utilDao = utilDao;
    }

    @GetMapping("")
    public List<Group> findAllGroups() {
        try {
            return groupDao.getAllGroups();
        } catch (GetException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve groups");
        }
    }

    @GetMapping("/{groupId}")
    public Group findGroupById(@PathVariable int groupId, Principal principal) {
        //TODO: check firstly if the group exists. No issues right now besides the http response says it was OK.
        try {
            return groupDao.getGroupById(groupId);
        } catch (GetException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve group by id");
        }
    }
    // Requires only a groupId
    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAGroup(@RequestBody Group group, Principal principal) {
        int ownerId = group.getGroupOwnerId();
        //TODO: check firstly if the group exists. No issues right now besides the http response says it was OK.
        if(!isOwner(principal.getName(), ownerId)) {
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "only owners may delete a group.");
        }
            try {
                groupDao.deleteGroup(group.getGroupId());
            } catch (DeleteException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not delete group");
            }
        }


    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAGroup(Principal principal, @RequestBody Group group){
        group.setGroupOwnerId(userDao.findIdByUsername(principal.getName()));
        System.out.println(group);
        try {
           groupDao.createGroup(group);
        } catch (CreateException e) {

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not create group");
        }
    }

    @PutMapping("")
    public void editAGroup(@RequestBody @Valid Group group, Principal principal) {
        if(!isOwner(principal.getName(), group.getGroupOwnerId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "only owners may edit a group");
        }
        try {
          groupDao.editGroup(group);
        } catch (UpdateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not update group");
        }
    }
// GroupMember Area ---------------------------------------------------------------------------------------------
    @PostMapping("/{groupId}/members/")
    public void addUserToGroup(Principal principal, @RequestBody Group group, @PathVariable int groupId, @RequestParam String groupCode){
        if(!group.getGroupCode().equals(groupCode)){
            throw new CreateException("Invalid group code");
        }
        GroupMember groupMember = new GroupMember();
        groupMember.setMemberId(userDao.findIdByUsername(principal.getName()));
        groupMember.setGroupId(group.getGroupId());
        try {
            groupDao.addUserToGroup(groupMember);
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not add user to group");
        }
    }

    @GetMapping("/{groupId}/members")
    public List <GroupMember> getAllMembers(@PathVariable int groupId, Principal principal){
        if(!utilDao.isVerified(principal.getName(), groupId)){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User is not a member of this group");
        }
        try {
            return groupDao.getAllMembers(groupId);
        } catch (GetException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not get users in group");
        }
    }

    @DeleteMapping("/{groupId}/members")
    public void removeUserFromGroup(Principal principal, @PathVariable int groupId, GroupMember groupMember){
        if(!isOwner(principal.getName(),groupDao.getGroupById(groupId).getGroupOwnerId())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Owner can not remove themselves from group");
        }
        try {
            groupDao.removeUserFromGroup(groupMember);
        } catch (DeleteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not remove user from group");
        }
    }

    private boolean isOwner(String username, int ownerId){
       int userId = userDao.findIdByUsername(username);
       if(userId == ownerId){
           return true;
       }
       return false;
    }

}
