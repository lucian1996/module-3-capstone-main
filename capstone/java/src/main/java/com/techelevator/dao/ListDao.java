package com.techelevator.dao;

import com.techelevator.model.List;

public interface ListDao {

    java.util.List<List> getAllListsForGroup(int groupId, String username);

    List getList(int groupId, int listId, String username);

    void createList(int groupId, int userId, String name);

    void deleteList(int groupId, String username, String name);

    void updateList(List list, String name);

}
