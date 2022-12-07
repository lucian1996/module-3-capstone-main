package com.techelevator.dao.jdbc;


import com.techelevator.dao.GroupDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.exceptions.CreateException;
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
import java.util.Random;

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
        int creatorId = userDao.findIdByUsername(username);
        String sql = "INSERT INTO groups (group_owner, group_name, group_code) values (?, ?, ?)";
        try {
            jdbcTemplate.update(sql, creatorId, groupName, getGroupCode());
        } catch (DataAccessException e) {
            throw new CreateException(e);
        }
    }

    @Override
    public void deleteGroup(int groupId, String username) {
        // get user id of principal and owner id and make sure they are equal
        // if so preform delete
        int userId =userDao.findIdByUsername(username);
        int ownerId = getGroupById(groupId, username).getGroupOwnerId();

        if (ownerId == userId) {
            String sql = "DELETE FROM groups WHERE group_id = ?;";
            try {
                jdbcTemplate.update(sql, groupId);
            } catch (DataAccessException e) {
                throw new DeleteException(e);
            }
        }
    }
    //TODO only allow group to be edited by someone in it
    @Override
    public void editGroup(Group group, String username) {
        String sql = "UPDATE groups set group_owner = ?, group_name = ? WHERE group_id = ?";
       try {
            jdbcTemplate.update(sql);
       } catch (DataAccessException e) {
           throw new GetException(e);
       }
    }

    //TODO add validation so only user that has access to the group can access a group
    public Group getGroupById(int groupId, String name) {
        String sql = "SELECT * FROM groups WHERE group_id = ?";
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
        Group group = new Group();
        group.setGroupName(rs.getString("group_name"));
        group.setGroupId(rs.getInt("group_id"));
        group.setGroupOwnerId(rs.getInt("group_owner"));
        group.setGroupCode(rs.getString("group_code"));
        return group;
    }

    private String getGroupCode () {
        char[] chars = new char[] {'a', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o',
                'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String groupCode = "";
        Random r = new Random();
        for (int i =0; i<10; i++) {
            int randomInt = r.nextInt(35);
            groupCode += chars[randomInt];
        }
        return groupCode;

    }
}
