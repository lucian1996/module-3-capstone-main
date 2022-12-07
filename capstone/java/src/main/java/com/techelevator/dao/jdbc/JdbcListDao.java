package com.techelevator.dao.jdbc;

import com.techelevator.dao.ListDao;
import com.techelevator.model.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class JdbcListDao implements ListDao {
    private JdbcTemplate jdbcTemplate;
    public JdbcListDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate((dataSource));
    }

    @Override
    public List getList(int groupId, int listId, String username) {
        String sql = "SELECT * FROM list WHERE group_id";
        return null;
    }

    @Override
    public void createList(int groupId, int userId, String name) {
        //return false;
    }

    @Override
    public void deleteList(int groupId, int userId, String name) {
        //return false;
    }

    @Override
    public void updateList(List list, String name) {
        //return false;
    }

    private List mapRowToList(SqlRowSet rs) {
        List list = new List();
        list.setListId(rs.getInt("list_id"));
        list.setGroupId(rs.getInt("group_id"));
        list.setDescription(rs.getString("description"));
        list.setListName(rs.getString("list_name"));
        return list;
    }
}