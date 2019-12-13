package com.company.customerservice.controller;

import com.company.customerservice.exception.NotFoundException;
import com.company.customerservice.model.Customer;
import com.company.customerservice.service.CustomerServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerServiceLayer customerServiceLayer;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Customer createCustomer(@RequestBody Customer customer){
        return customerServiceLayer.newCustomer(customer);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id) {
        Customer customer = customerServiceLayer.fetchCustomer(id);

        if (customer == null){
            throw new NotFoundException("Customer could not be retrieved for ID: " + id);
        }

        return customer;
    }

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomer(){
        return customerServiceLayer.fetchAllCustomer();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@PathVariable int id) {
        customerServiceLayer.deleteCustomer(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCustomer(@PathVariable int id, @RequestBody @Valid Customer customer) {
        if (customer.getId() == 0){
            customer.setId(id);
        }
        if (id != customer.getId()) {
            throw new IllegalArgumentException("Customer ID on path must match the ID in the Customer object");
        }

        customerServiceLayer.updateCustomer(customer);
    }
}
