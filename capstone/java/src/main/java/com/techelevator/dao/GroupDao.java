package com.techelevator.dao;

import com.techelevator.model.Group;
import com.techelevator.model.GroupMember;

import java.util.List;

public interface GroupDao {

    void createGroup(Group group);

    void deleteGroup(int groupId);

    void editGroup(Group group);

    Group getGroupById(int groupId);

    List<Group> getAllGroups();

    void addUserToGroup(GroupMember groupMember);

    List<GroupMember> getAllMembers(int groupId);

    boolean isMemberInGroupByUsername(int groupId, String username);

    void removeUserFromGroup(int userId, int groupId);

}
