package com.company.retailapiservice.util.feign;

import com.company.retailapiservice.model.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient("customer-service")
public interface CustomerClient {

    @GetMapping("/customer/{id}")
    public Customer getCustomerById(@PathVariable("id") int id);

    @GetMapping("/customer")
    public List<Customer> getAllCustomers();
}
