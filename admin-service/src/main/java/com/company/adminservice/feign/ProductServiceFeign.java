package com.company.adminservice.feign;

import com.company.adminservice.util.message.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "product-service", path = "/product")
public interface ProductServiceFeign {

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    Product createProduct(@RequestBody Product product);

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    Product getProduct(@PathVariable int id);

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    List<Product> getAllProducts();

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProduct(@PathVariable int id);

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateProduct(@PathVariable int id, @RequestBody @Valid Product product);

}
