package com.techelevator.dao;

import com.techelevator.model.Group;

import java.util.List;

public interface GroupDao {
    boolean createGroup(int userId, String name);

    boolean deleteGroup(int groupId, int userId);

    boolean editGroup(Group group);

    Group getGroupById(int groupId);

    List<Group> getAllGroups();


}
