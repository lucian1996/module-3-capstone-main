package com.techelevator.dao;

import com.techelevator.model.List;

public interface ListDao {

    List getList(int groupId, int userId, int listId);

    boolean createList(int groupId, int userId);

    boolean deleteList(int groupId, int userId);

    boolean updateList(List list);

}
