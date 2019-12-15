package com.company.inventoryservice.service;

import com.company.inventoryservice.dao.InventoryDao;
import com.company.inventoryservice.exception.NotFoundException;
import com.company.inventoryservice.model.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServiceLayer {

    public InventoryDao inventoryDao;

    @Autowired
    public ServiceLayer(InventoryDao inventoryDao) {
        this.inventoryDao = inventoryDao;
    }

    public Inventory saveInventoryEntry(Inventory inventory) {
        return inventoryDao.createInventoryEntry(inventory);
    }

    public Inventory getInventoryEntry(int id) {
        Inventory inventory = inventoryDao.getInventoryEntry(id);
        if (inventory == null) {
            throw new NotFoundException("no inventory entry found in");
        }
        return inventory;
    }

    public void updateInventory(Inventory inventory) {
        inventoryDao.updateInventoryEntry(inventory);
    }

    public void deleteInventory(int id) {
        inventoryDao.deleteInventoryEntry(id);
    }

    public List<Inventory> getAllInventoryEntries() {
        return inventoryDao.getAllInventoryEntries();
    }

    public Inventory getInventoryByProductId(int productId) {
        try {
            return inventoryDao.getProductInventoryByProductId(productId);
        } catch (EmptyResultDataAccessException e) {
            throw new NotFoundException("no inventory entry in db with that product id");
        }
    }

}
