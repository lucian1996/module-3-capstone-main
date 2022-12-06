package com.techelevator.dao;

import com.techelevator.model.Item;

import java.util.List;

public interface ItemDao {

    Item getItemById(int listId, int itemId);

    boolean createItem(int listId);

    boolean deleteItem(int itemId);

    boolean updateItem(Item item);

    List<Item> listItems(int listId);
}
