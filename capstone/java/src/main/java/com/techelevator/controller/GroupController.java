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

    public GroupController(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @GetMapping("")
    public List<Group> findAllGroups(Principal principal) {
        try {
            return groupDao.getAllGroups();
        } catch (GetException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve groups");
        }
    }

    @GetMapping("/{groupId}")
    public Group findGroupById(@PathVariable int groupId, Principal principal) {
        try {
            return groupDao.getGroupById(groupId, principal.getName());
        } catch (GetException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve group by id");
        }
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAGroup(@PathVariable @Valid int groupId, Principal principal) {
        try {
           groupDao.deleteGroup(groupId, principal.getName());
        } catch (DeleteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not delete group");
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAGroup(Principal principal, @RequestParam @Valid String groupName) {
        try {
           groupDao.createGroup(principal.getName(), groupName);
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not create group");
        }
    }

    @PutMapping("")
    public void editAGroup(@RequestBody @Valid Group group, Principal principal) {
        try {
          groupDao.editGroup(group, principal.getName());
        } catch (UpdateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not update group");
        }
    }
// GroupMember Area ---------------------------------------------------------------------------------------------
    @PostMapping("/{groupId}/members/")
    public void addUserToGroup(Principal principal, @PathVariable int groupId, @RequestParam String groupCode){
        try {
            groupDao.addUserToGroup((principal.getName()), groupId, groupCode);
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not add user to group");
        }
    }
    //TODO: can we preAuthorize individuals at this level?
    @GetMapping("/{groupId}/members")
    public List <GroupMember> getAllMembers(@PathVariable int groupId){
        try {
            return groupDao.getAllMembers(groupId);
        } catch (GetException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not get users in group");
        }
    }

    @DeleteMapping("/{groupId}/members")
    public void removeUserFromGroup(Principal principal, @PathVariable int groupId){
        try {
            groupDao.removeUserFromGroup(principal.getName(), groupId);
        } catch (DeleteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not remove user from group");
        }
    }


}
