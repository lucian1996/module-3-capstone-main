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

     public GroupController(GroupDao groupDao){
         this.groupDao = groupDao;
     }

    @GetMapping("")
    public List<Group> findAllGroups(Principal principal){
         try{
         return groupDao.getAllGroups();
         } catch (GetException e) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve groups");
         }
}
    @GetMapping("/{id}")
    public Group findGroupById(@PathVariable int id, Principal principal){
         try{
        return groupDao.getGroupById(id);
         } catch (GetException e) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve group by id");
         }
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteAGroup(@RequestBody @Valid int groupId, int userId){
         try{
         return groupDao.deleteGroup(groupId,userId);
         } catch (DeleteException e) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not delete group");
         }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createAGroup(@RequestBody @Valid int userId, String name){
         try{
         return groupDao.createGroup(userId, name);
         } catch (CreateException e) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not create group");
         }
    }
    @PutMapping("")
    public boolean editAGroup(@RequestBody @Valid Group group){
         try{
         return groupDao.editGroup(group);
         } catch (UpdateException e) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not update group");
         }
    }

}
