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
    public Item getItemById(int listId, int itemId) {
        String sql = "SELECT item FROM list WHERE list_id = ? AND item_id = ?";
        Item item;
        item = jdbcTemplate.queryForObject(sql, Item.class, listId, itemId);
        return item;
    }

    //TODO
    @Override
    public boolean createItem(int listId) {
        String sql = "INSERT INTO items (item_id, list_id, claimed_id) VALUES" +
                " (DEFAULT, ?, ?) RETURNING user_id";
        return true;
    }

    //TODO
    @Override
    public boolean deleteItem(int itemId) {
        return false;
    }

    //TODO
    @Override
    public boolean updateItem(Item item) {
        return false;
    }

    @Override
    public List<Item> listItems(int listId) {
    List<Item> items = new ArrayList<>();
    String sql = "SELECT * FROM items WHERE list_id = ?";
    SqlRowSet results = jdbcTemplate.queryForRowSet(sql, listId);
        while (results.next()) {
        Item item = mapRowToItem(results);
        items.add(item);
    }
        return items;
}

    private Item mapRowToItem(SqlRowSet rs) {
        Item item = new Item();
        item.setItemId(rs.getInt("item_id"));
        item.setListId(rs.getInt("list_id"));
        item.setClaimedId(rs.getInt("claimed_id"));
        item.setDateModified(rs.getString("date_modified"));
        item.setLastModifier(rs.getInt("last_modifier"));
        item.setQuantity(rs.getInt("quantity"));
        item.setDescription("description");
        return item;
    }
}