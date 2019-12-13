package com.company.customerservice.service;

import com.company.customerservice.dao.CustomerDao;
import com.company.customerservice.dao.CustomerDaoImpl;
import com.company.customerservice.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class CustomerServiceLayerTest {

    CustomerDao customerDao;

    CustomerServiceLayer customerServiceLayer;

    @Before
    public void setUp(){

        setUpConsumerDao();

        customerServiceLayer = new CustomerServiceLayer(customerDao);
    }

    public void setUpConsumerDao(){
        customerDao = mock(CustomerDaoImpl.class);

        Customer customer = new Customer();
        customer.setId(1);
        customer.setFirstName("fName");
        customer.setLastName("lName");
        customer.setStreet("Street");
        customer.setCity("City");
        customer.setZip("Zip");
        customer.setEmail("Email");
        customer.setPhone("Phone");

        Customer customer2 = new Customer();
        customer2.setFirstName("fName");
        customer2.setLastName("lName");
        customer2.setStreet("Street");
        customer2.setCity("City");
        customer2.setZip("Zip");
        customer2.setEmail("Email");
        customer2.setPhone("Phone");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);

        doReturn(customer).when(customerDao).createCustomer(customer2);
        doReturn(customer).when(customerDao).getCustomer(1);
        doReturn(customerList).when(customerDao).getAllCustomers();
    }

    @Test
    public void shouldSaveFindAndGetAllCustomers(){
        Customer customer = new Customer();

        customer.setFirstName("fName");
        customer.setLastName("lName");
        customer.setStreet("Street");
        customer.setCity("City");
        customer.setZip("Zip");
        customer.setEmail("Email");
        customer.setPhone("Phone");

        System.out.println("Customer: " + customer);

        customer = customerServiceLayer.newCustomer(customer);

        System.out.println("New Customer: " + customer);

        Customer service = customerServiceLayer.fetchCustomer(customer.getId());

        assertEquals(customer, service);
    }

}
