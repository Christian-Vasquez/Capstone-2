package com.company.retailapiservice.util.feign;

import com.company.retailapiservice.model.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient("inventory-service")
public interface InventoryClient {

    @GetMapping("/inventory/product/{product-id}")
    public Inventory getInventoryEntryByProductId(@PathVariable("product-id") int productId);

    @GetMapping("/inventory")
    public List<Inventory> getAllInventoryEntries();

    @PutMapping("/inventory")
    public void updateInventory(@RequestBody Inventory inventory);
}
