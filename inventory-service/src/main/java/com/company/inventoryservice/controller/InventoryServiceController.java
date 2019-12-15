package com.company.inventoryservice.controller;

import com.company.inventoryservice.model.Inventory;
import com.company.inventoryservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/inventory")
public class InventoryServiceController {

    @Autowired
    ServiceLayer service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Inventory createInventoryEntry(@RequestBody Inventory inventory) {
        return service.saveInventoryEntry(inventory);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Inventory getInventory(@PathVariable("id") int id) {
        return service.getInventoryEntry(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateInventoryEntry(Inventory inventory) {
        service.updateInventory(inventory);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteInventoryEntry(@PathVariable("id") int id) {
        service.deleteInventory(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Inventory> getAllInventoryEntries() {
        return service.getAllInventoryEntries();
    }

    @GetMapping("/product/{product-id}")
    @ResponseStatus(HttpStatus.OK)
    public Inventory getInventoryEntryByProductId(@PathVariable("product-id") int productId) {
        return service.getInventoryByProductId(productId);
    }
}
