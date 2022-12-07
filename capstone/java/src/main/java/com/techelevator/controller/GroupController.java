package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.UserDao;
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

    public GroupController(GroupDao groupDao, UserDao userDao) {
        this.groupDao = groupDao;
        this.userDao = userDao;
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
        try {
            return groupDao.getGroupById(groupId);
        } catch (GetException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve group by id");
        }
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAGroup(@RequestBody Group group, Principal principal) {
        int ownerId = group.getGroupOwnerId();
        if(isOwner(principal.getName(), ownerId)) {
            try {
                groupDao.deleteGroup(group.getGroupId());
            } catch (DeleteException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not delete group");
            }
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
    public List <GroupMember> getAllMembers(@PathVariable int groupId){
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

    private boolean isOwner(String username, int owner){
       int userId = userDao.findIdByUsername(username);
       int ownerId = owner;
       if(userId == ownerId){
           return true;
       }
       return false;
    }

}
