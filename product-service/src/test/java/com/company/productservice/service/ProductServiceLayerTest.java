package com.company.productservice.service;

import com.company.productservice.dao.ProductDao;
import com.company.productservice.dao.ProductDaoImpl;
import com.company.productservice.model.Product;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;


@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceLayerTest {

    ProductDao productDao;

    ProductServiceLayer productServiceLayer;

    @Before
    public void setUp() throws Exception{

        setUpProductDao();

        productServiceLayer = new ProductServiceLayer(productDao);
    }

    public void setUpProductDao(){
       productDao = mock(ProductDaoImpl.class);

       Product product = new Product();

        product.setId(1);
        product.setName("Name");
        product.setDescription("Description");
        product.setUnit_cost(BigDecimal.valueOf(1.00).setScale(2, RoundingMode.HALF_UP));
        product.setList_price(BigDecimal.valueOf(1.00).setScale(2, RoundingMode.HALF_UP));

        Product product2 = new Product();
        product2.setName("Name");
        product2.setDescription("Description");
        product2.setUnit_cost(BigDecimal.valueOf(1.00).setScale(2, RoundingMode.HALF_UP));
        product2.setList_price(BigDecimal.valueOf(1.00).setScale(2, RoundingMode.HALF_UP));

        List<Product> productList = new ArrayList<>();
        productList.add(product);

        doReturn(product).when(productDao).createProduct(product2);
        doReturn(product).when(productDao).getProduct(1);
        doReturn(productList).when(productDao).getAllProducts();

    }

    @Test
    public void shouldSaveFindAndGetAllProduct(){
        Product product = new Product();

        product.setName("Name");
        product.setDescription("Description");
        product.setUnit_cost(BigDecimal.valueOf(1.00).setScale(2, RoundingMode.HALF_UP));
        product.setList_price(BigDecimal.valueOf(1.00).setScale(2, RoundingMode.HALF_UP));

        System.out.println("Product: " + product);

        product = productServiceLayer.newProduct(product);

        System.out.println("New Product: " + product);

        Product service = productServiceLayer.fetchProduct(product.getId());

        assertEquals(product, service);
    }
}
