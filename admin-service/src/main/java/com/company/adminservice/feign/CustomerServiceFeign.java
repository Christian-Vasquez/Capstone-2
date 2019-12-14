package com.company.adminservice.feign;

import com.company.adminservice.util.message.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "customer-service", path = "/customer")
public interface CustomerServiceFeign {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    Customer createCustomer(@RequestBody Customer customer);

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Customer getCustomer(@PathVariable int id);

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    List<Customer> getAllCustomer();

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteCustomer(@PathVariable int id);

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCustomer(@PathVariable int id, @RequestBody @Valid Customer customer);

}
