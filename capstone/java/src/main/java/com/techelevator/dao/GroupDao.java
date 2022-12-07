package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.GroupMember;

import java.util.List;

public interface GroupDao {
    void createGroup(String username, String groupName, String description);

    void deleteGroup(int groupId, String string);

    void editGroup(Group group, String string);

    Group getGroupById(int groupId, String name);

    List<Group> getAllGroups();

    void addUserToGroup(String username, int groupId, String groupCode);

    List<GroupMember> getAllMembers(int groupId);

    void removeUserFromGroup(String username, int groupId);

}
