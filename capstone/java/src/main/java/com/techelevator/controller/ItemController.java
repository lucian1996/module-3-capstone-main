package com.techelevator.controller;

import com.techelevator.dao.ItemDao;
import com.techelevator.model.Item;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/list/item")
@PreAuthorize("isAuthenticated()")
public class ItemController {
    private ItemDao itemDao;

    @GetMapping("/{itemId}")
    public Item getItem(@RequestBody @Valid int listId, @PathVariable int itemId, Principal principal){
        return itemDao.getItemById(listId, itemId);
    }
    @GetMapping("")
    public List<Item> getAllItems(@RequestParam int listId, Principal principal){
        return itemDao.listItems(listId);
    }
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createItem(@RequestParam int listId, Principal principal){
        return itemDao.createItem(listId);
    }
    @DeleteMapping("")
    @ResponseStatus(HttpStatus.OK)
    public boolean deleteItem(@RequestParam int itemId, Principal principal){
        return itemDao.deleteItem(itemId);
    }
    @PutMapping("")
    @ResponseStatus(HttpStatus.OK)
    public boolean updateItem(@RequestParam Item item, Principal principal){
        return itemDao.updateItem(item);
    }
}
