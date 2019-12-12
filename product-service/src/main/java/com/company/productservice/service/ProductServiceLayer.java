package com.company.productservice.service;

import com.company.productservice.dao.ProductDao;
import com.company.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductServiceLayer {

    @Autowired
    ProductDao productDao;

    @Autowired
    public ProductServiceLayer(ProductDao productDao) {
        this.productDao = productDao;
    }

    public Product fetchProduct(int id){
        return productDao.getProduct(id);
    }

    public List<Product> fetchAllProduct(){
        return productDao.getAllProducts();
    }

    public Product newProduct(Product product){
        return productDao.createProduct(product);
    }

    public void deleteProduct(int id){
        productDao.deleteProduct(id);
    }

    public void updateProduct(Product product){
        productDao.updateProduct(product);
    }
}
