package com.company.productservice.dao;

import com.company.productservice.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class ProductDaoTest {

    @Autowired
    ProductDao productDao;

    @Before
    public void setUp(){
        List<Product> productList = productDao.getAllProducts();

        for(Product product: productList){
            productDao.deleteProduct(product.getId());
        }
    }

    @Test
    public void shouldCreateGetGetAllDeleteProduct(){
        Product product = new Product();

        product.setName("Name");
        product.setDescription("Description");
        product.setUnit_cost(BigDecimal.valueOf(1.00).setScale(2, RoundingMode.HALF_UP));
        product.setList_price(BigDecimal.valueOf(1.00).setScale(2, RoundingMode.HALF_UP));

        productDao.createProduct(product);

        System.out.println("all size: " + productDao.getAllProducts().size());
        System.out.println("id: " + product.getId());

        assertEquals(product, productDao.getProduct(product.getId()));
        productDao.deleteProduct(product.getId());
        assertEquals(0, productDao.getAllProducts().size());
    }

    @Test
    public void updateProduct(){
        Product product = new Product();

        product.setName("Name");
        product.setDescription("Description");
        product.setUnit_cost(BigDecimal.valueOf(1.00).setScale(2, RoundingMode.HALF_UP));
        product.setList_price(BigDecimal.valueOf(1.00).setScale(2, RoundingMode.HALF_UP));

        productDao.createProduct(product);

        product.setName("Name UPDATE!");

        productDao.updateProduct(product);

        assertEquals(product, productDao.getProduct(product.getId()));
    }
}
