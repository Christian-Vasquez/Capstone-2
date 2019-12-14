package com.company.adminservice.feign;


import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "inventory-service")
public interface InventoryServiceFeign {
}
