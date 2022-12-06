package com.techelevator.dao.jdbc;


import com.techelevator.dao.GroupDao;
import com.techelevator.model.Group;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcGroupDao implements GroupDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public boolean createGroup(int userId, String name) {
        String sql = "INSERT INTO groups (user_id, group_name) values (?, ?)";
        return true;
    }

    @Override
    public boolean deleteGroup(int groupId, int userId) {
        return false;
    }

    @Override
    public boolean editGroup(Group group) {
        return false;
    }

    @Override

    public Group getGroupById(int groupId) {
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
        group.setGroupId(rs.getInt("group_id"));
        users.add(rs.getInt("user_id"));
        group.setUsers(users);
        group.setGroupOwnerId(rs.getInt("group_owner_id"));
        return group;
    }
}
