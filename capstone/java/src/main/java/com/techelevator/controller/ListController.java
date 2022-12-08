package com.techelevator.controller;

import com.techelevator.dao.GroupDao;
import com.techelevator.dao.ListDao;
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
    private ListDao listDao;

    public ListController(ListDao listDao) {
        this.listDao = listDao;
    }


    @GetMapping("")
    public java.util.List<List> getAllLists() {
        try {
            return listDao.getAllLists();
        } catch (GetException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve lists");
        }
    }

    @GetMapping("/{groupId}/{listId}")
    public List getListByListId(@PathVariable("groupId") int groupId, @PathVariable("listId") int listId, Principal principal) {
        try {
            return listDao.getList(groupId, listId, principal.getName());
        } catch (GetException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve list with id");
        }
    }

    @PostMapping("/{groupId}")
    @ResponseStatus(HttpStatus.CREATED)
    public void createAList(@PathVariable("groupId") int groupId, @RequestBody @Valid int userId, Principal principal) {
        try {
            listDao.createList(groupId, userId, principal.getName());
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not create list");
        }
    }

    @PutMapping("/{groupId}/{listId}")
    public void updateAList(@RequestBody @Valid List list, Principal principal) {
        try {
            listDao.updateList(list, principal.getName());
        } catch (UpdateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not update list");
        }
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteAList(@PathVariable("groupId") int groupId, @RequestBody @Valid String username, Principal principal) {
        try {
            listDao.deleteList(groupId, username, principal.getName());
        } catch (DeleteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not delete list");
        }
    }
}
