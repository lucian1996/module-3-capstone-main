package com.techelevator.controller;

import com.techelevator.dao.ItemDao;
import com.techelevator.dao.exceptions.CreateException;
import com.techelevator.dao.exceptions.DeleteException;
import com.techelevator.dao.exceptions.GetException;
import com.techelevator.dao.exceptions.UpdateException;
import com.techelevator.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/list/item")
@PreAuthorize("isAuthenticated()")
public class ItemController {
    private final ItemDao itemDao;

    public ItemController(ItemDao itemDao) {

        this.itemDao = itemDao;
    }
    //TODO chqange to pathvar
    @GetMapping("/")
    public List<Item> getAllItems(@RequestParam int listId, Principal principal) {
        //TODO validate principal
        System.out.println("here");
        try {
            return itemDao.listItems(listId);
        } catch (GetException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not retrieve all items");
        }
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public void createItem(@RequestBody Item item, Principal principal) {
        // here we could do the principal validation
        item.setDateModified("test");
        try {
            itemDao.createItem(item);
        } catch (CreateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not create item");
        }
    }

    @DeleteMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void deleteItem(@RequestBody Item item, Principal principal) {
        try {
             itemDao.deleteItem(item);
        } catch (DeleteException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not delete item");
        }
    }

    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public void updateItem(@RequestBody Item item, Principal principal) {
        try {
            itemDao.updateItem(item);
        } catch (UpdateException e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "could not update item");
        }
    }
}
