package com.techelevator.dao;

import com.techelevator.model.Item;

public interface ItemDao {

    Item getItem(int listId, int itemId);

    boolean createItem(int listId);

    boolean deleteItem(int itemId);

    boolean updateItem(Item item);
}
