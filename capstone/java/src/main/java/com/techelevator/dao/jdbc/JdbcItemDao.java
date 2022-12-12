package com.techelevator.dao.jdbc;

import com.techelevator.dao.ItemDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.UtilDao;
import com.techelevator.dao.exceptions.CreateException;
import com.techelevator.dao.exceptions.DeleteException;
import com.techelevator.dao.exceptions.UpdateException;
import com.techelevator.model.Item;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcItemDao implements ItemDao {
    private final JdbcTemplate jdbcTemplate;
    private final UserDao userDao;
    private final UtilDao utilDao;
    public JdbcItemDao(DataSource dataSource, UserDao userDao, UtilDao utilDao) {
        this.jdbcTemplate = new JdbcTemplate((dataSource));
        this.userDao = userDao;
        this.utilDao = utilDao;
    }

    @Override
    public void createItem(Item item) {
        String sql = "INSERT INTO list_item(date_modified, quantity, last_modifier, list_id, group_id, item_name) VALUES (?, ?, ?, ?, ?, ?)";
        System.out.println(jdbcTemplate.update(sql, utilDao.currentDay(), item.getQuantity(), item.getLastModifier(), item.getListId(), item.getGroupId(), item.getItemName()));
        try {
            jdbcTemplate.update(sql, utilDao.currentDay(), item.getQuantity(), item.getLastModifier(), item.getListId(), item.getGroupId(), item.getItemName()) ;
        } catch (DataAccessException e) {
            throw new CreateException(e);
        }
    }
    //TODO: make sure implementation works currently does not
    @Override
    public void deleteItem(Item item) {
        System.out.println(item.toString());
        String sql = "DELETE FROM list_item WHERE list_item_id = 3001;";
        try {
            jdbcTemplate.update(sql);
        } catch (DataAccessException e) {
            throw new DeleteException (e);
        }
    }

    @Override
    public void updateItem(Item item) {
        String sql = "UPDATE list_item SET date_modified = ?, quantity = ?, last_modifier = ?, item_name = ? WHERE list_item_id = ?;";
        try {
            jdbcTemplate.update(sql, utilDao.currentDay(), item.getQuantity(), item.getLastModifier(), item.getItemName(), item.getItemId());
        } catch (DataAccessException e) {
            throw new UpdateException(e);
        }
    }


    //TODO have some verification that list id is valid
    @Override
    public List<Item> listItems(int listId) {
    List<Item> items = new ArrayList<>();
    String sql = "SELECT * FROM list_item WHERE list_id = ?;";
    SqlRowSet results = jdbcTemplate.queryForRowSet(sql, listId);
        while (results.next()) {
        Item item = mapRowToItem(results);
        items.add(item);
    }
        return items;
}

    private Item mapRowToItem(SqlRowSet rs) {
        Item item = new Item();
        item.setItemId(rs.getInt("list_item_id"));
        item.setListId(rs.getInt("list_id"));
//       item.setClaimedId(rs.getInt("claimed_id"));
        item.setDateModified(rs.getString("date_modified"));
        item.setLastModifier(rs.getInt("last_modifier"));
        item.setQuantity(rs.getInt("quantity"));
        item.setGroupId(rs.getInt("group_id"));
        item.setItemName(rs.getString("item_name"));
        return item;
    }
}