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
    public void createGroup(String username, String groupName) {
        String sql = "INSERT INTO groups (user_id, group_name) values (1, ?)";
        //TODO: do not forget about the hardcoded '1'.
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

        List<Group> groups = new ArrayList<>();
        String sql = "SELECT * FROM groups";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Group group = mapRowToGroup(results);
            groups.add(group);
        }
        return groups;
    }

    //TODO: mapRowToGroup is not complete
    private Group mapRowToGroup(SqlRowSet rs) {
        System.out.println(rs);
        Group group = new Group();
        group.setGroupName(rs.getString("group_name"));
        group.setGroupId(rs.getInt("group_id"));
        group.setGroupOwnerId(rs.getInt("group_owner_id"));
        return group;
    }
}
