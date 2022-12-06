package com.techelevator.dao;

import com.techelevator.model.Group;

import java.util.List;

public interface GroupDao {
    void createGroup(int userId, String name);

    void deleteGroup(int groupId, int userId);

    void editGroup(Group group);

    Group getGroupById(int groupId);

    List<Group> getAllGroups();


}
