package com.techelevator.dao;

import com.techelevator.model.List;

public interface ListDao {

    java.util.List<List> getAllListsForGroup(int groupId);

    List getList(int groupId, int listId);

    void createList(List list);

    void deleteList(int groupId, int listId);

    void updateList(List list);

}
