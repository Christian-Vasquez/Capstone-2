package com.company.retailapiservice.controller;

import com.company.retailapiservice.model.InvoiceViewModel;
import com.company.retailapiservice.model.OrderViewModel;
import com.company.retailapiservice.model.Product;
import com.company.retailapiservice.model.ProductViewModel;
import com.company.retailapiservice.service.ServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
public class RetailApiServiceController {

    @Autowired
    ServiceLayer service;

    @PostMapping("/place-order")
    @ResponseStatus(HttpStatus.CREATED)
    public InvoiceViewModel submitOrder(@RequestBody @Valid OrderViewModel order) {
        return service.processOrder(order);
    }

    @GetMapping("/products")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductViewModel> getAllProducts() {
        return service.getAllProducts();
    }

}
