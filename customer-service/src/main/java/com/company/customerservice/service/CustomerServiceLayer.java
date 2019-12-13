package com.company.customerservice.service;

import com.company.customerservice.dao.CustomerDao;
import com.company.customerservice.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerServiceLayer {
    @Autowired
    CustomerDao customerDao;

    @Autowired
    public  CustomerServiceLayer(CustomerDao customerDao){
        this.customerDao = customerDao;
    }

    public Customer fetchCustomer(int id){
        return customerDao.getCustomer(id);
    }

    public List<Customer> fetchAllCustomer(){
        return customerDao.getAllCustomers();
    }

    public Customer newCustomer(Customer customer){
        return customerDao.createCustomer(customer);
    }

    public void updateCustomer(Customer customer){
        customerDao.updateCustomer(customer);
    }

    public void deleteCustomer(int id){
        customerDao.deleteCustomer(id);
    }

}
