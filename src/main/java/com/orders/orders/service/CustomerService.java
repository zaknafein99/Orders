package com.orders.orders.service;

import com.orders.orders.domain.Customer;
import com.orders.orders.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer createCustomer(Customer customer) {
        // logic for validating customer information
        // save customer
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerByPhone(String phone){
        return customerRepository.findByphoneNumber(phone);
    }

    // other methods for handling customers
}

