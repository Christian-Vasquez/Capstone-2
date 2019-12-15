package com.company.inventoryservice.dao;

import com.company.inventoryservice.model.Inventory;

import java.util.List;

public interface InventoryDao {

    public Inventory createInventoryEntry(Inventory inventory);

    public Inventory getInventoryEntry(int id);

    public void updateInventoryEntry(Inventory inventory);

    public void deleteInventoryEntry(int id);

    public List<Inventory> getAllInventoryEntries();

    public Inventory getProductInventoryByProductId(int productId);



}
