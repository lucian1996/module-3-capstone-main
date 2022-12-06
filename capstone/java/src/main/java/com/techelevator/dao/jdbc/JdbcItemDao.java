package com.techelevator.dao.jdbc;

import com.techelevator.dao.ItemDao;
import com.techelevator.model.Item;
import com.techelevator.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcItemDao implements ItemDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public Item getItemById(int itemId) {
        String sql = "SELECT item FROM list WHERE item_id = ?";
        Item item;
        item = jdbcTemplate.queryForObject(sql, Item.class, itemId);
        return item;
    }

    //TODO: address null exception
    @Override
    public boolean createItem(int listId) {
        String sql = "INSERT INTO list_item (list_item_id, list_id, date_modified, quantity, last_modifier, description)" +
                "VALUES (DEFAULT, ?, GETDATE(), 0, 0, NULL) RETURNING list_item_id;";
        Integer itemId = jdbcTemplate.queryForObject(sql, Integer.class, listId);
        return getItemById(listId, itemId) != null;
    }

    //TODO: make sure implementation works
    @Override
    public boolean deleteItem(int itemId) {
        String sql = "DELETE FROM list_item WHERE list_item_id = ?;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,itemId);
        return results.wasNull();
    }

    //TODO: boolean implementation
    @Override
    public boolean updateItem(Item item) {
        String sql = "UPDATE list_item SET date_modified = GETDATE(), " +
                "last_modifier = ?, quantity = ?, description = ?; " +
                "COMMIT;";
        jdbcTemplate.update(sql, item.getLastModifier(), item.getQuantity(), item.getDescription());
        return false;
    }

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
//        item.setClaimedId(rs.getInt("claimed_id"));
        item.setDateModified(rs.getString("date_modified"));
        item.setLastModifier(rs.getInt("last_modifier"));
        item.setQuantity(rs.getInt("quantity"));
        item.setDescription(rs.getString("description"));
        return item;
    }
}