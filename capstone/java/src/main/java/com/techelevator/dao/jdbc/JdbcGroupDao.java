package com.techelevator.dao.jdbc;


import com.techelevator.dao.GroupDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.exceptions.CreateException;
import com.techelevator.dao.exceptions.DeleteException;
import com.techelevator.dao.exceptions.GetException;
import com.techelevator.model.Group;
import com.techelevator.model.GroupMember;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.objenesis.ObjenesisException;
import org.springframework.stereotype.Component;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;

import javax.sql.DataSource;
import java.security.Principal;
import java.sql.Date;
import java.sql.Timestamp;
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
    public void createGroup(String username, String groupName, String description) {
        int creatorId = userDao.findIdByUsername(username);
        String groupCode = getGroupCode();
        String sql = "INSERT INTO groups (group_owner, group_name, group_code, description) values (?, ?, ?, ?) RETURNING group_id";
        try {
            Integer groupId = jdbcTemplate.queryForObject(sql, Integer.class, creatorId, groupName, groupCode, description);
            addUserToGroup(username, groupId, groupCode);
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
            sql = "DELETE FROM group_member WHERE group_id = ?";
            jdbcTemplate.update(sql, groupId);
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
    @Override
    public void addUserToGroup(String username, int groupId, String groupCode) {
        if( !verifyGroupCode(groupCode, groupId, username)) {
            throw new CreateException("invalid code");
        } else {
            String sql = "INSERT INTO group_member (group_id, user_id, date_joined) values (?, ?, ?)";
            try {
                jdbcTemplate.update(sql, groupId, userDao.findIdByUsername(username), currentDay());
            } catch (DataAccessException e) {
                throw new CreateException(e);
            }
        }
    }
    @Override
    public List<GroupMember> getAllMembers(int groupId) {
        List<GroupMember> allMembers = new ArrayList<>();
        String sql = "SELECT * FROM group_member WHERE group_id = ?";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, groupId);
        while (results.next()) {
            GroupMember groupMember = mapRowToMemberGroup(results);
            allMembers.add(groupMember);
        }
        return allMembers;
    }
    //TODO: implement
    @Override
    public void removeUserFromGroup(String username, int groupId) {
        String sql = "DELETE FROM group_member WHERE user_id = ?;";
        int userId = userDao.findIdByUsername(username);
        // verify that user is not the owner
        if (getGroupById(groupId, username).getGroupOwnerId() != userId) {
            throw new DeleteException("you don't have permission");
        }
        try {
            jdbcTemplate.update(sql, userDao.findIdByUsername(username));
        } catch (DataAccessException e) {
            throw new DeleteException(e);
        }
    }


    private Group mapRowToGroup(SqlRowSet rs) {
        Group group = new Group();
        group.setGroupName(rs.getString("group_name"));
        group.setGroupId(rs.getInt("group_id"));
        group.setGroupOwnerId(rs.getInt("group_owner"));
        group.setGroupCode(rs.getString("group_code"));
        group.setGroupDescription(rs.getString("description"));
        return group;
    }
    private GroupMember mapRowToMemberGroup(SqlRowSet rs){
        GroupMember groupMember = new GroupMember();
        groupMember.setGroupId(rs.getInt("group_id"));
        groupMember.setMemberId(rs.getInt("user_id"));
        groupMember.setDateJoined(rs.getString("date_joined"));
        return groupMember;
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
    private boolean verifyGroupCode(String groupCode, int groupId, String username){
        return getGroupById(groupId, username).getGroupCode().equals(groupCode);
    }
    private String currentDay(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String date = now.toString();
        return date;
    }
}
