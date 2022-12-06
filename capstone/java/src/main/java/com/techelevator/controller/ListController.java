package com.techelevator.controller;

import com.techelevator.dao.ListDao;
import com.techelevator.dao.exceptions.CreateException;
import com.techelevator.dao.exceptions.DeleteException;
import com.techelevator.dao.exceptions.GetException;
import com.techelevator.dao.exceptions.UpdateException;
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

    @GetMapping("/{groupId}/{listId}")
    public List getListByListId(@PathVariable("groupId") int groupId, @PathVariable("listId") int listId, @RequestBody @Valid int userId, Principal principal) {
        try {
            return listDao.getList(groupId, listId, userId);
        } catch (GetException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve list with id");
        }
    }

    @PostMapping("/{groupId}")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createAList(@PathVariable("groupId") int groupId, @RequestBody @Valid int userId, Principal principal) {
        try {
            return listDao.createList(groupId, userId);
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not create list");
        }
    }

    @PutMapping("/updateList")
    public boolean updateAList(@RequestBody @Valid List list) {
        try {
            return listDao.updateList(list);
        } catch (UpdateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not update list");
        }
    }

    @DeleteMapping("/{groupId}")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteAList(@PathVariable("groupId") int groupId, @RequestBody @Valid int userId, Principal principal) {
        try {
            return listDao.deleteList(groupId, userId);
        } catch (DeleteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not delete list");
        }
    }
}
