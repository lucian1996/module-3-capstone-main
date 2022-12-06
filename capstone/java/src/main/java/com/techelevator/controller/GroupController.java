package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Group;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

//TODO: Do we want the principal to be passed into all parameters incase we need to verfiy or determine the user?

    @GetMapping(path = "")
    public List<Group> findAllGroups(Principal principal){
         return groupDao.getAllGroups();
}
    @GetMapping(path = "/{id}")
    public Group findGroupById(@PathVariable int id, Principal principal){
        return groupDao.getGroupById(id);
    }

    @DeleteMapping(path = "")
    public boolean deleteAGroup(@RequestBody @Valid int groupId, int userId){
         return groupDao.deleteGroup(groupId,userId);
    }

    @PostMapping(path = "")
    public boolean createAGroup(@RequestBody @Valid int userId, String name){
         return groupDao.createGroup(userId, name);
    }
    @PutMapping(path = "")
    public boolean editAGroup(@RequestBody @Valid Group group){
         return groupDao.editGroup(group);
    }

}
