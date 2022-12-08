package com.techelevator.dao;

import com.techelevator.model.Item;

import java.util.List;

public interface ItemDao {
    boolean hasPermission(String username, int listId);
    void createItem(Item item);
    void deleteItem(Item item);

    void updateItem(Item item);

    List<Item> listItems(int listId);
}
