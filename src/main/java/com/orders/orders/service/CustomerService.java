package com.orders.orders.service;

import com.orders.orders.domain.Customer;
import com.orders.orders.repository.CustomerRepository;
import org.springframework.stereotype.Service;

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

    // other methods for handling customers
}

