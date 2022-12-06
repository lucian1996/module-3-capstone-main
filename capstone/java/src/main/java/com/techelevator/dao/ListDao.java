package com.techelevator.dao;

import com.techelevator.model.List;

public interface ListDao {

    List getList(int groupId, int userId, int listId);

    List getList(int userId, int listId);

    void createList(int groupId, int userId);

    void deleteList(int groupId, int userId);

    void updateList(List list);

}
