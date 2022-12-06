package com.techelevator.dao;

import com.techelevator.model.Item;

import java.util.List;

public interface ItemDao {

    Item getItemById(int itemId);

    void createItem(int listId);

    void deleteItem(int itemId);

    void updateItem(Item item);

    List<Item> listItems(int listId);
}
