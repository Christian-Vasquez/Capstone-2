package com.company.adminservice.feign;

import com.company.adminservice.util.message.InventoryClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "inventory-service", path = "/inventory")
public interface InventoryFeign {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    InventoryClient createInventoryEntry(@RequestBody InventoryClient inventoryClient);

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    InventoryClient getInventory(@PathVariable("id") int id);

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateInventoryEntry(InventoryClient inventoryClient);

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteInventoryEntry(@PathVariable("id") int id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<InventoryClient> getAllInventoryEntries();

    @GetMapping("/product/{product-id}")
    @ResponseStatus(HttpStatus.OK)
    InventoryClient getInventoryEntryByProductId(@PathVariable("product-id") int productId);
}
