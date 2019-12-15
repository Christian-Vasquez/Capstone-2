package com.company.inventoryservice.service;

import com.company.inventoryservice.dao.InventoryDao;
import com.company.inventoryservice.dao.InventoryDaoJdbcImpl;
import com.company.inventoryservice.exception.NotFoundException;
import com.company.inventoryservice.model.Inventory;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ServiceLayerTests {

    InventoryDao inventoryDao;

    ServiceLayer service;

    @Before
    public void setUp() throws Exception {
        setUpInventoryDaoMock();

        service = new ServiceLayer(inventoryDao);
    }

    @Test
    public void shouldAddAndFindInventoryEntry() {
        Inventory inventory = new Inventory();
        inventory.setProductId(18);
        inventory.setQuantity(88);

        inventory = inventoryDao.createInventoryEntry(inventory);

        Inventory inventoryFromService = inventoryDao.getInventoryEntry(inventory.getInventoryId());

        assertEquals(inventory, inventoryFromService);
    }

    @Test
    public void shouldFindInventoryEntryByProductId() {
        Inventory inventory = new Inventory();
        inventory.setProductId(18);
        inventory.setQuantity(88);

        inventory = inventoryDao.createInventoryEntry(inventory);

        Inventory productInventory = inventoryDao.getProductInventoryByProductId(inventory.getProductId());

        assertEquals(inventory, productInventory);

    }


    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundExceptionIfInventoryNotFoundById() {
        service.getInventoryEntry(77);
    }

    @Test(expected = NotFoundException.class)
    public void shouldThrowNotFoundExceptionIfProductNotFound() {
        service.getInventoryByProductId(99);
    }


    public void setUpInventoryDaoMock() {
        inventoryDao = mock(InventoryDaoJdbcImpl.class);

        Inventory inventory = new Inventory();
        inventory.setProductId(18);
        inventory.setQuantity(88);

        Inventory inventory1 = new Inventory();
        inventory1.setInventoryId(1);
        inventory1.setProductId(18);
        inventory1.setQuantity(88);

        when(inventoryDao.createInventoryEntry(inventory)).thenReturn(inventory1);
        when(inventoryDao.getInventoryEntry(1)).thenReturn(inventory1);
        when(inventoryDao.getProductInventoryByProductId(18)).thenReturn(inventory1);
        when(inventoryDao.getProductInventoryByProductId(99)).thenThrow(new NotFoundException("no inventory entry in db with that product id"));
        when(inventoryDao.getInventoryEntry(77)).thenThrow(new NotFoundException("no inventory entry in db with that id"));
    }

}
