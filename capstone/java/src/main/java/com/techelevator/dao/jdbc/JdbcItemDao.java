package com.techelevator.dao.jdbc;

import com.techelevator.dao.ItemDao;
import com.techelevator.model.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class JdbcItemDao implements ItemDao {
    private JdbcTemplate jdbcTemplate;

    @Override
    public Item getItem(int listId, int itemId) {
        return null;
    }

    @Override
    public boolean createItem(int listId) {
        return false;
    }

    @Override
    public boolean deleteItem(int itemId) {
        return false;
    }

    @Override
    public boolean updateItem(Item item) {
        return false;
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