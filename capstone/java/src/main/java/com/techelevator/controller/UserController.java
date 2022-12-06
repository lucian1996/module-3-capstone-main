package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.model.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class UserController {
    private UserDao userDao;

    @GetMapping(path = "/all")
    public List<User> getAllUsers(Principal principal){
        return userDao.findAll();
    }
    @GetMapping(path = "/{userId}")
    public User getUserById(@PathVariable("userId") int userId, Principal principal){
        return userDao.getUserById(userId);
    }
    //TODO: Does the [ principal.getName() ] return the username?
    @GetMapping(path = "/username")
    public User findByUsername(Principal principal){
        return userDao.findByUsername(principal.getName());
    }
    @GetMapping(path = "")
    public int findIdByUsername(Principal principal){
        return userDao.findIdByUsername(principal.getName());
    }
    @PostMapping(path = "")
    public boolean createUser(@RequestBody @Valid String username, String password, String role, Principal principal){
        return userDao.create(username, password, role);
    }
    @DeleteMapping(path = "")
    public boolean deleteUser(@RequestBody @Valid int userid, Principal principal){
        return userDao.deleteUser(userid);
    }


}
