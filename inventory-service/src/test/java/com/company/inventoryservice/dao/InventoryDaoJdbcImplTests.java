package com.company.inventoryservice.dao;

import com.company.inventoryservice.model.Inventory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InventoryDaoJdbcImplTests {

    @Autowired
    InventoryDao inventoryDao;

    private Inventory inventory;

    @Before
    public void setUp() {
        clearDatabase();
        setUpTestObjects();
    }

    public void clearDatabase() {
        List<Inventory> inventoryList = inventoryDao.getAllInventoryEntries();
        inventoryList.stream().forEach(inventory1 -> inventoryDao.deleteInventoryEntry(inventory1.getInventoryId()));

    }

    public void setUpTestObjects() {
        inventory = new Inventory();
        inventory.setProductId(12);
        inventory.setQuantity(88);
    }

    @Test
    public void shouldAddFindAndDeleteInventoryId() {
        inventory = inventoryDao.createInventoryEntry(inventory);

        Inventory inventoryFromDb = inventoryDao.getInventoryEntry(inventory.getInventoryId());

        assertEquals(inventory, inventoryFromDb);

        inventoryDao.deleteInventoryEntry(inventory.getInventoryId());

        assertNull(inventoryDao.getInventoryEntry(inventory.getInventoryId()));
    }

    @Test
    public void shouldUpdateInventoryEntry() {
        inventory = inventoryDao.createInventoryEntry(inventory);

        inventory.setQuantity(80);
        inventoryDao.updateInventoryEntry(inventory);

        Inventory updatedInventoryFromDb = inventoryDao.getInventoryEntry(inventory.getInventoryId());

        assertEquals(inventory, updatedInventoryFromDb);
    }

    @Test
    public void shouldGetAllInventoryEntriesInDb() {
        inventory = inventoryDao.createInventoryEntry(inventory);

        List<Inventory> inventoryList = new ArrayList<>(Collections.singletonList(inventory));

        assertEquals(inventoryList, inventoryDao.getAllInventoryEntries());
    }

    @Test
    public void shouldGetQuantityByProduction() {
        inventory = inventoryDao.createInventoryEntry(inventory);

        assertEquals(inventory, inventoryDao.getProductInventoryByProductId(12));
    }

    @Test
    public void shouldReturnNullIfNoInventoryWithProductId() {
        assertNull(inventoryDao.getInventoryEntry(100));
    }

}
