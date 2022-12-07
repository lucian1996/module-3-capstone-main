package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.exceptions.CreateException;
import com.techelevator.dao.exceptions.DeleteException;
import com.techelevator.dao.exceptions.GetException;
import com.techelevator.dao.exceptions.UpdateException;
import com.techelevator.model.Group;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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

    @GetMapping("/{id}")
    public Group findGroupById(@PathVariable int id, Principal principal) {
        try {
            return groupDao.getGroupById(id, principal.getName());
        } catch (GetException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve group by id");
        }
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAGroup(@RequestBody @Valid int groupId, Principal principal) {
        try {
           groupDao.deleteGroup(groupId, principal.getName());
        } catch (DeleteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not delete group");
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAGroup( Principal principal, @RequestBody @Valid Group group) {
        try {
           groupDao.createGroup(principal.getName(), group.getGroupName());
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

}
