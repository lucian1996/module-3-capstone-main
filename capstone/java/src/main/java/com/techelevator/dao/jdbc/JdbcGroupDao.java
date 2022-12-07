package com.techelevator.dao.jdbc;


import com.techelevator.dao.GroupDao;
import com.techelevator.dao.exceptions.GetException;
import com.techelevator.model.Group;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGroupDao implements GroupDao {
    private JdbcTemplate jdbcTemplate;
    public JdbcGroupDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate((dataSource));
    }

    @Override
    public void createGroup(String username, String groupName) {
        String sql = "INSERT INTO groups (group_owner, group_name) values (1, ?)";
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

    //TODO add validation so only user that has access to the group can join it
    public Group getGroupById(int groupId, String name) {
        System.out.println(groupId + "hi");
        String sql = "SELECT group_id, group_owner, group_name FROM groups WHERE group_id = ?";
       SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, groupId);
        try {
            if (rowSet.next()) {
               return mapRowToGroup(rowSet);
            }
        }
        catch (DataAccessException e) {
            throw new GetException(e);
        }
        return new Group();
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
        System.out.println("hello dude");
        Group group = new Group();
        group.setGroupName(rs.getString("group_name"));
        group.setGroupId(rs.getInt("group_id"));
        group.setGroupOwnerId(rs.getInt("group_owner"));
        return group;
    }
}
