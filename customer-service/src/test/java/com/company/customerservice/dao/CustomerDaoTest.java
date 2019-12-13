package com.company.customerservice.dao;

import com.company.customerservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@Transactional
public class CustomerDaoTest {
    @Autowired
    CustomerDao customerDao;

    @Before
    public void setUp(){
        List<Customer> customerList = customerDao.getAllCustomers();

        for(Customer customer : customerList){
            customerDao.deleteCustomer(customer.getId());
        }
    }

    @Test
    public void shouldCreateGetGetAllDeleteCustomer(){
        Customer customer = new Customer();

        customer.setFirstName("fName");
        customer.setLastName("lName");
        customer.setStreet("Street");
        customer.setCity("City");
        customer.setZip("Zip");
        customer.setEmail("Email");
        customer.setPhone("Phone");

        customerDao.createCustomer(customer);

        System.out.println("all size: " + customerDao.getAllCustomers().size());
        System.out.println("Id: " + customer.getId());

        assertEquals(customer, customerDao.getCustomer(customer.getId()));
        customerDao.deleteCustomer(customer.getId());
        assertEquals(0, customerDao.getAllCustomers().size());
    }

    @Test
    public void shouldUpdateCustomer(){
        Customer customer = new Customer();

        customer.setFirstName("fName");
        customer.setLastName("lName");
        customer.setStreet("Street");
        customer.setCity("City");
        customer.setZip("Zip");
        customer.setEmail("Email");
        customer.setPhone("Phone");

        customerDao.createCustomer(customer);

        customer.setFirstName("UpdateName");

        customerDao.updateCustomer(customer);

        assertEquals( customer, customerDao.getCustomer(customer.getId()));
    }

}
