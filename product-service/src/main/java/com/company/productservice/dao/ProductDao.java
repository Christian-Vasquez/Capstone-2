package com.company.productservice.dao;

import com.company.productservice.model.Product;

import java.util.List;


public interface ProductDao {

    Product createProduct(Product product);

    Product getProduct(int id);

    List<Product> getAllProducts();

    void updateProduct(Product product);

    void deleteProduct(int id);

}
