package com.techelevator.dao;

import com.techelevator.model.Group;

import java.util.List;

public interface GroupDao {
    void createGroup(int userId, String name);

    void deleteGroup(int groupId, String string);

    void editGroup(Group group, String string);

    Group getGroupById(int groupId, String name);

    List<Group> getAllGroups();


}
