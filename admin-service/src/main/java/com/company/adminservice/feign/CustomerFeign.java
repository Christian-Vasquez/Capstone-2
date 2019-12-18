package com.company.adminservice.feign;

import com.company.adminservice.util.message.CustomerClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "customer-service", path = "/customer")
public interface CustomerFeign {

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    CustomerClient createCustomer(@RequestBody CustomerClient customerClient);

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    CustomerClient getCustomer(@PathVariable int id);

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    List<CustomerClient> getAllCustomer();

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
   void deleteCustomer(@PathVariable int id);

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateCustomer(@RequestBody @Valid CustomerClient customerClient);

}
