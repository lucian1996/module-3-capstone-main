package com.techelevator.dao.jdbc;


import com.techelevator.dao.GroupDao;
import com.techelevator.model.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGroupDao implements GroupDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public void createGroup(int userId, String name) {
        String sql = "INSERT INTO groups (user_id, group_name) values (?, ?)";
        //return true;
    }

    @Override
    public void deleteGroup(int groupId, String username) {
        //return false;
    }

    @Override
    public void editGroup(Group group, String username) {
        //return false;
    }

    @Override

    public Group getGroupById(int groupId, String name) {
        return null;
    }

    @Override
    public List<Group> getAllGroups() {
        String sql = "SELECT * FROM groups";
        return null;
    }

    //TODO: mapRowToGroup is not complete
    private Group mapRowToGroup(SqlRowSet rs) {
        Group group = new Group();
        List<Integer> users = new ArrayList<>();
       // group.set add name
        group.setGroupId(rs.getInt("group_id"));
        users.add(rs.getInt("user_id"));
        group.setUsers(users);
        group.setGroupOwnerId(rs.getInt("group_owner_id"));
        return group;
    }
}
