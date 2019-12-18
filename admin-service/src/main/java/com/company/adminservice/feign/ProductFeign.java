package com.company.adminservice.feign;

import com.company.adminservice.util.message.ProductClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "product-service", path = "/product")
public interface ProductFeign {

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    ProductClient createProduct(@RequestBody ProductClient productClient);

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    ProductClient getProduct(@PathVariable int id);

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    List<ProductClient> getAllProducts();

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteProduct(@PathVariable int id);

    @PutMapping(value = "")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void updateProduct(@RequestBody @Valid ProductClient productClient);

}
