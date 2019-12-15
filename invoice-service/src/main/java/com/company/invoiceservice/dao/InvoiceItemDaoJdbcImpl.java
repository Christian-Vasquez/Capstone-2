package com.company.invoiceservice.dao;

import com.company.invoiceservice.model.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoJdbcImpl implements InvoiceItemDao {

    private JdbcTemplate jdbcTemplate;

    private static final String INSERT_ITEM_SQL =
            "insert into invoice_item (invoice_id, inventory_id, quantity, unit_price) values (?, ?, ?, ?)";

    private static final String SELECT_ITEM_SQL =
            "select * from invoice_item where invoice_item_id = ?";

    private static final String UPDATE_ITEM_SQL =
            "update invoice_item set invoice_id = ?, inventory_id = ?, quantity = ?, unit_price = ? where invoice_item_id = ?";

    private static final String DELETE_ITEM_SQL =
            "delete from invoice_item where invoice_item_id = ?";

    private static final String SELECT_ALL_ITEM_SQL =
            "select * from invoice_item";

    private static final String GET_ITEMS_BY_INVOICE_SQL =
            "select * from invoice_item where invoice_id = ?";

    private static final String LAST_INSERT_ID_SQL =
            "select last_insert_id()";

    @Autowired
    public InvoiceItemDaoJdbcImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public InvoiceItem createInvoiceItem(InvoiceItem item) {
        jdbcTemplate.update(
                INSERT_ITEM_SQL,
                item.getInvoiceId(),
                item.getInventoryId(),
                item.getQuantity(),
                item.getUnitPrice()
        );

        int id = jdbcTemplate.queryForObject(LAST_INSERT_ID_SQL, Integer.class);

        item.setInvoiceItemId(id);

        return item;
    }

    @Override
    public InvoiceItem getInvoiceItemById(int id) {
        try {
            return jdbcTemplate.queryForObject(SELECT_ITEM_SQL, this::mapRowToInvoiceItem, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(
                UPDATE_ITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getInventoryId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitPrice(),
                invoiceItem.getInvoiceItemId()
        );
    }

    @Override
    public void deleteInvoiceItem(int id) {
        jdbcTemplate.update(DELETE_ITEM_SQL, id);
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return jdbcTemplate.query(SELECT_ALL_ITEM_SQL, this::mapRowToInvoiceItem);
    }

    @Override
    public List<InvoiceItem> getInvoiceItemsByInvoiceId(int invoiceId) {
        List<InvoiceItem> invoiceItems = jdbcTemplate.query(GET_ITEMS_BY_INVOICE_SQL, this::mapRowToInvoiceItem, invoiceId);
        if (invoiceItems.isEmpty()) {
            return null;
        }
        return invoiceItems;
    }

    private InvoiceItem mapRowToInvoiceItem(ResultSet rs, int rowNum) throws SQLException {
        InvoiceItem item = new InvoiceItem();
        item.setInvoiceItemId(rs.getInt("invoice_item_id"));
        item.setInvoiceId(rs.getInt("invoice_id"));
        item.setInventoryId(rs.getInt("inventory_id"));
        item.setQuantity(rs.getInt("quantity"));
        item.setUnitPrice(rs.getBigDecimal("unit_price"));

        return item;
    }
}
