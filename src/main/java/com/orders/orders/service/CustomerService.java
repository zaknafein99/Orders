package com.orders.orders.service;

import com.orders.orders.domain.Customer;
import com.orders.orders.domain.Order;
import com.orders.orders.repository.CustomerRepository;
import com.orders.orders.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public Customer createCustomer(Customer customer) {
        // logic for validating customer information
        // save customer
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    public List<Customer> getCustomerByPhone(String phone){
        return customerRepository.findByphoneNumber(phone);
    }

    public List<Customer> saveAll(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public void deleteCustomer(Long customerId) {
        // delete all orders associated with the customer
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        orderRepository.deleteAll(orders);

        // delete the customer
        customerRepository.deleteById(customerId);
    }
    // other methods for handling customers
}

