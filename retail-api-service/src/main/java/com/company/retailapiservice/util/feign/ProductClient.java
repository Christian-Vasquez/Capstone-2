package com.company.retailapiservice.util.feign;

import com.company.retailapiservice.model.Product;
import com.company.retailapiservice.model.ProductViewModel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("product-service")
public interface ProductClient {

    @GetMapping("/product")
    public List<ProductViewModel> getAllProducts();

    @GetMapping("/product/{id}")
    public ProductViewModel getProductById(@PathVariable int id);
}
