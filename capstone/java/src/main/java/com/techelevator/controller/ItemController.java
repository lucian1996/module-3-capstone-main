package com.techelevator.controller;

import com.techelevator.dao.ItemDao;
import com.techelevator.model.Item;
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

    @GetMapping(path = "/{itemId}")
    public Item getItem(@RequestBody @Valid int listId, @PathVariable int itemId, Principal principal){
        return itemDao.getItemById(listId, itemId);
    }
    @GetMapping(path = "")
    public List<Item> getAllItems(@RequestParam int listId, Principal principal){
        return itemDao.listItems(listId);
    }
    @PostMapping(path="")
    public boolean createItem(@RequestParam int listId, Principal principal){
        return itemDao.createItem(listId);
    }
    @DeleteMapping(path = "")
    public boolean deleteItem(@RequestParam int itemId, Principal principal){
        return itemDao.deleteItem(itemId);
    }
    @PutMapping(path = "")
    public boolean updateItem(@RequestParam Item item, Principal principal){
        return itemDao.updateItem(item);
    }
}
