package com.techelevator.dao.jdbc;


import com.techelevator.dao.GroupDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.exceptions.DeleteException;
import com.techelevator.dao.exceptions.GetException;
import com.techelevator.model.Group;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcGroupDao implements GroupDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    public JdbcGroupDao(DataSource dataSource, UserDao userDao) {
        this.jdbcTemplate = new JdbcTemplate((dataSource));
        this.userDao = userDao;
    }

    @Override
    public void createGroup(String username, String groupName) {
        String sql = "INSERT INTO groups (group_owner, group_name) values (1, ?)";
        //TODO: do not forget about the hardcoded '1'.
        //return true;
    }

    //TODO only allow for owner to delete a group
    @Override
    public void deleteGroup(int groupId, String username) {
        String sql = "DELETE FROM groups WHERE groupId = ?";
        try {
            jdbcTemplate.update(sql, groupId);
        } catch (DataAccessException e) {
            throw new DeleteException(e);
        }
    }
    //TODO only allow group to be edited by someone in it
    @Override
    public void editGroup(Group group, String username) {
        String sql = "UPDATE groups set group_owner = ?, group_name = ?, description = ? WHERE group_id = ?";
       try {
            jdbcTemplate.update(sql, group.getGroupOwnerId(), group.getGroupName(), group.getGroupDescription(), group.getGroupId());
       } catch (DataAccessException e) {
           throw new GetException(e);
       }
    }

    //TODO add validation so only user that has access to the group can access a group
    public Group getGroupById(int groupId, String name) {
        String sql = "SELECT group_id, group_owner, group_name, description FROM groups WHERE group_id = ?";
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
        String sql = "SELECT group_id, group_owner, group_name, description FROM groups";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while (results.next()) {
            Group group = mapRowToGroup(results);
            groups.add(group);
        }
        return groups;
    }

    //TODO: mapRowToGroup is not complete
    private Group mapRowToGroup(SqlRowSet rs) {
        Group group = new Group();
        group.setGroupName(rs.getString("group_name"));
        group.setGroupId(rs.getInt("group_id"));
        group.setGroupOwnerId(rs.getInt("group_owner"));
        group.setGroupDescription(rs.getString("description"));
        return group;
    }
}
