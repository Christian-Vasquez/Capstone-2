package com.company.productservice.dao;

import com.company.productservice.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class ProductDaoImpl implements  ProductDao {

    private static final String INSERT_PRODUCT =
            "INSERT INTO product (product_name, product_description, list_price, unit_cost) VALUES (?, ?, ?, ?)";

    private static final String SELECT_PRODUCT_BY_ID =
            "SELECT * FROM product WHERE product_id = ?";

    private static final String SELECT_ALL_PRODUCTS =
            "SELECT * FROM product";

    private static final String UPDATE_PRODUCT =
            "UPDATE product SET product_name = ?, product_description = ?, list_price = ?, unit_cost = ? WHERE product_id = ?";

    private static final String DELETE_PRODUCT =
            "DELETE FROM product WHERE product_id = ?";

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public ProductDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Product createProduct(Product product) {
        jdbcTemplate.update(INSERT_PRODUCT,
                product.getName(),
                product.getDescription(),
                product.getList_price(),
                product.getUnit_cost()
        );

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        product.setId(id);

        return product;
    }

    @Override
    public Product getProduct(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_PRODUCT_BY_ID, this::mapToProduct, id);
        }catch (EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return jdbcTemplate.query(SELECT_ALL_PRODUCTS, this::mapToProduct);
    }

    @Override
    public void updateProduct(Product product) {
        jdbcTemplate.update(UPDATE_PRODUCT,
                product.getName(),
                product.getDescription(),
                product.getList_price(),
                product.getUnit_cost(),
                product.getId()
        );
    }

    @Override
    public void deleteProduct(int id) {
        jdbcTemplate.update(DELETE_PRODUCT, id);
    }

    private Product mapToProduct (ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();

        product.setId(rs.getInt("product_id"));
        product.setName(rs.getString("product_name"));
        product.setDescription(rs.getString("product_description"));
        product.setList_price(rs.getBigDecimal("list_price"));
        product.setUnit_cost(rs.getBigDecimal("unit_cost"));

        return product;
    }
}
