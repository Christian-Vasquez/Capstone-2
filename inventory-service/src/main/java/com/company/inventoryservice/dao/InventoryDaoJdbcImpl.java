package com.company.inventoryservice.dao;

import com.company.inventoryservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InventoryDaoJdbcImpl implements InventoryDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_INVENTORY_SQL =
            "insert into inventory (product_id, quantity) values (?, ?)";

    private static final String SELECT_INVENTORY_SQL =
            "select * from inventory where inventory_id = ?";

    private static final String UPDATE_INVENTORY_SQL =
            "update inventory set product_id = ?, quantity = ? where inventory_id = ?";

    private static final String GET_ALL_INVENTORY_ENTRIES_SQL =
            "select * from inventory";

    private static final String DELETE_INVENTORY_SQL =
            "delete from inventory where inventory_id = ?";

    private static final String GET_INVENTORY_BY_PRODUCT_SQL =
            "select * from inventory where product_id = ?";

    private static final String LAST_INSERT_ID =
            "select last_insert_id()";

    @Autowired
    public InventoryDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Inventory createInventoryEntry(Inventory inventory) {
        jdbcTemplate.update(
                INSERT_INVENTORY_SQL,
                inventory.getProductId(),
                inventory.getQuantity()
        );

        int id = jdbcTemplate.queryForObject(LAST_INSERT_ID, Integer.class);

        inventory.setInventoryId(id);

        return inventory;
    }

    @Override
    public Inventory getInventoryEntry(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVENTORY_SQL, this::mapRowToInventory, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateInventoryEntry(Inventory inventory) {
        jdbcTemplate.update(
                UPDATE_INVENTORY_SQL,
                inventory.getProductId(),
                inventory.getQuantity(),
                inventory.getInventoryId()
        );
    }

    @Override
    public void deleteInventoryEntry(int id) {
        jdbcTemplate.update(DELETE_INVENTORY_SQL, id);
    }

    @Override
    public List<Inventory> getAllInventoryEntries() {
        return jdbcTemplate.query(GET_ALL_INVENTORY_ENTRIES_SQL, this::mapRowToInventory);
    }

    @Override
    public Inventory getProductInventoryByProductId(int productId) {
        try {
            return jdbcTemplate.queryForObject(GET_INVENTORY_BY_PRODUCT_SQL, this::mapRowToInventory, productId);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

//    @Override
//    public void updateInventoryByProductId(int productId) {
//        jdbcTemplate.update(UPDATE_INVENTORY_By_PRODUCT_SQL, productId)
//    }

    private Inventory mapRowToInventory(ResultSet rs, int rowNum) throws SQLException {
        Inventory inventory = new Inventory();
        inventory.setInventoryId(rs.getInt("inventory_id"));
        inventory.setProductId(rs.getInt("product_id"));
        inventory.setQuantity(rs.getInt("quantity"));

        return inventory;
    }
}
