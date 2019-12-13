package com.company.productservice.controller;

import com.company.productservice.exception.NotFoundException;
import com.company.productservice.model.Product;
import com.company.productservice.service.ProductServiceLayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RefreshScope
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductServiceLayer productServiceLayer;

    @PostMapping(value = "")
    @ResponseStatus(HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product){
        return productServiceLayer.newProduct(product);
    }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProduct(@PathVariable int id) {
        Product product = productServiceLayer.fetchProduct(id);

        if (product == null){
            throw new NotFoundException("Product could not be retrieved for ID: " + id);
        }

        return product;
    }

    @GetMapping(value = "")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts(){
        return productServiceLayer.fetchAllProduct();
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable int id) {
        productServiceLayer.deleteProduct(id);
    }

    @PutMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProduct(@PathVariable int id, @RequestBody @Valid Product product) {
        if (product.getId() == 0){
            product.setId(id);
        }
        if (id != product.getId()) {
            throw new IllegalArgumentException("Product ID on path must match the ID in the Product object");
        }

        productServiceLayer.updateProduct(product);
    }
}
