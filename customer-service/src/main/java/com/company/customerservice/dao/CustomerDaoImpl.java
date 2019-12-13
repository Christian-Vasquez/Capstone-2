package com.company.customerservice.dao;

import com.company.customerservice.model.Customer;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDaoImpl implements CustomerDao {

    private static final String INSERT_CUSTOMER =
            "INSERT INTO customer (first_name, last_name, street, city, zip, email, phone) VALUES (?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_CUSTOMER_BY_ID =
            "SELECT * FROM customer WHERE customer_id = ?";

    private static final String SELECT_ALL_CUSTOMERS =
            "SELECT * FROM customer";

    private static final String UPDATE_CUSTOMER =
            "UPDATE customer SET first_name = ?, last_name = ?, street = ?, city = ?, zip = ?, email = ?, phone = ? WHERE customer_id = ?";

    private static final String DELETE_CUSTOMER =
            "DELETE FROM customer WHERE customer_id = ?";


    private JdbcTemplate jdbcTemplate;

    public CustomerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        jdbcTemplate.update(INSERT_CUSTOMER,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getStreet(),
                customer.getCity(),
                customer.getZip(),
                customer.getEmail(),
                customer.getPhone()
        );

        int id = jdbcTemplate.queryForObject("SELECT LAST_INSERT_ID()", Integer.class);

        customer.setId(id);

        return customer;
    }

    @Override
    public Customer getCustomer(int id) {
        try{
            return jdbcTemplate.queryForObject(SELECT_CUSTOMER_BY_ID, this::mapToCustomer, id);
        }catch (EmptyResultDataAccessException e){
            System.out.println("ERROR: ");
            return null;
        }
    }

    @Override
    public List<Customer> getAllCustomers() {
        return jdbcTemplate.query(SELECT_ALL_CUSTOMERS, this::mapToCustomer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        jdbcTemplate.update(UPDATE_CUSTOMER,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getStreet(),
                customer.getCity(),
                customer.getZip(),
                customer.getEmail(),
                customer.getPhone(),
                customer.getId()
        );
    }

    @Override
    public void deleteCustomer(int id) {
        jdbcTemplate.update(DELETE_CUSTOMER, id);
    }

    private Customer mapToCustomer(ResultSet rs, int rowNum) throws SQLException{
        Customer customer = new Customer();

        customer.setId(rs.getInt("customer_id"));
        customer.setFirstName(rs.getString("first_name"));
        customer.setLastName(rs.getString("last_name"));
        customer.setStreet(rs.getString("street"));
        customer.setCity(rs.getString("city"));
        customer.setZip(rs.getString("zip"));
        customer.setEmail(rs.getString("email"));
        customer.setPhone(rs.getString("phone"));

        return customer;
    }
}
