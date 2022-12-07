package com.techelevator.dao.jdbc;

import com.techelevator.dao.ItemDao;
import com.techelevator.dao.UserDao;
import com.techelevator.dao.exceptions.CreateException;
import com.techelevator.dao.exceptions.DeleteException;
import com.techelevator.dao.exceptions.UpdateException;
import com.techelevator.model.Item;
import com.techelevator.model.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcItemDao implements ItemDao {
    private JdbcTemplate jdbcTemplate;
    public JdbcItemDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate((dataSource));
    }

    //TODO: address null exception
    @Override
    public void createItem(Item item) {
        String sql = "INSERT INTO list_item (date_modified, quantity, last_modifier, description) VALUES (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(item.getDateModified(), item.getQuantity(), item.getLastModifier(), item.getDescription());
        } catch (DataAccessException e) {
            throw new CreateException(e);
        }
    }

    //TODO: make sure implementation works
    @Override
    public void deleteItem(Item item) {
        String sql = "DELETE FROM list_item WHERE list_item_id = ?;";
        try {
            jdbcTemplate.queryForRowSet(sql, item.getItemId());
        } catch (DataAccessException e) {
            throw new DeleteException (e);
        }
    }

    //TODO: boolean implementation
    @Override
    public void updateItem(Item item) {
        String sql = "UPDATE item SET date_modified, quantity, last_modifier, description VALUES (?, ?, ?, ?)";
        try {
            jdbcTemplate.update(sql, item.getDescription(), item.getQuantity(), item.getLastModifier(), item.getDescription());
        } catch (DataAccessException e) {
            throw new UpdateException(e);
        }
    }

//    CREATE TABLE list_item (
//            list_item_id int NOT NULL DEFAULT nextval('seq_list_item_id'),
//    list_id int NOT NULL,
//    date_modified varchar(50) NULL,
//    quantity int NOT NULL,
//    last_modifier int NOT NULL,
//    description varchar(500),
//    CONSTRAINT PK_list_item PRIMARY KEY (list_item_id),
//    CONSTRAINT FK_list_item_list FOREIGN KEY (list_id) REFERENCES list (list_id),
//    CONSTRAINT chk_quantity CHECK (quantity > 0)
//    );


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
//        item.setClaimedId(rs.getInt("claimed_id"));
        item.setDateModified(rs.getString("date_modified"));
        item.setLastModifier(rs.getInt("last_modifier"));
        item.setQuantity(rs.getInt("quantity"));
        item.setDescription(rs.getString("description"));
        return item;
    }
}