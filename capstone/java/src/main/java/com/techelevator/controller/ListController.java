package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.ListDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.UtilDao;
import com.techelevator.dao.exceptions.CreateException;
import com.techelevator.dao.exceptions.DeleteException;
import com.techelevator.dao.exceptions.GetException;
import com.techelevator.dao.exceptions.UpdateException;
import com.techelevator.model.Group;
import com.techelevator.model.List;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@CrossOrigin
@RequestMapping("/group")
@PreAuthorize("isAuthenticated()")


public class ListController {
    private final ListDao listDao;
    private final GroupDao groupDao;
    private final UserDao userDao;
    private final UtilDao utilDao;

    public ListController(ListDao listDao, GroupDao groupDao, UserDao userDao, UtilDao utilDao) {
        this.listDao = listDao;
        this.groupDao = groupDao;
        this.userDao = userDao;
        this.utilDao = utilDao;
    }


    @GetMapping("/{groupId}")
    public java.util.List<List> getAllLists(@PathVariable int groupId, Principal principal) {
        if (!utilDao.isVerified(principal.getName(), groupId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you do not have permission");
        } try {
                return listDao.getAllListsForGroup(groupId);
            } catch (GetException e) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve lists");
            }
    }

    @GetMapping("/{groupId}/{listId}")
    public List getListByListId(@PathVariable int groupId, @PathVariable int listId, Principal principal) {
        if (!utilDao.isVerified(principal.getName(), groupId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you do not have permission");
        } try {
            return listDao.getList(groupId, listId);
        } catch (GetException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve list with id");
        }
    }

    @PostMapping("/{groupId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAList(@PathVariable int groupId, @RequestBody List list, Principal principal) {
        if (!utilDao.isVerified(principal.getName(), groupId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you do not have permission");
        } try {
            listDao.createList(list);
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not create list");
        }
    }

    @PutMapping("/{groupId}/{listId}")
    public void updateAList(@PathVariable int groupId, @PathVariable int listId, @RequestBody List list, Principal principal) {
        if (!utilDao.isVerified(principal.getName(), groupId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you do not have permission");
        } try {
            listDao.updateList(list);
        } catch (UpdateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not update list");
        }
    }

    //TODO: Should only be able to delete a list you have claimed(?)
    @DeleteMapping("/{groupId}/{listId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAList(@PathVariable int groupId, @PathVariable int listId, Principal principal) {
        if (!utilDao.isVerified(principal.getName(), groupId)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "you do not have permission");
        } try {
            listDao.deleteList(groupId, listId);
        } catch (DeleteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not delete list");
        }
    }
}