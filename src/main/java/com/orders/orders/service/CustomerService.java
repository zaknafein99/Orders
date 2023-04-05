package com.orders.orders.service;

import com.orders.orders.controller.CustomerController;
import com.orders.orders.domain.Customer;
import com.orders.orders.domain.Order;
import com.orders.orders.repository.CustomerRepository;
import com.orders.orders.repository.OrderRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    private static final Logger log = Logger.getLogger(CustomerController.class.getName());


    public CustomerService(CustomerRepository customerRepository, OrderRepository orderRepository) {
        this.customerRepository = customerRepository;
        this.orderRepository = orderRepository;
    }

    public Customer createCustomer(Customer customer) {
        // logic for validating customer information
        // save customer
        return customerRepository.save(customer);
    }

    public Page<Customer> getAllCustomers(Pageable pageable){
        return customerRepository.findAll(pageable);
    }

    public List<Customer> getCustomerByPhone(String phone){
        return customerRepository.findByphoneNumber(phone);
    }

    public List<Customer> saveAll(List<Customer> customers) {
        return customerRepository.saveAll(customers);
    }

    public Optional<Customer> getCustomerById(Long id) {
        try {
            return customerRepository.findById(id);
        } catch (Exception e) {
            // handle exception here
            log.info("Cliente con id  " + id + " not found.");
        }
        return Optional.empty();
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

