package com.orders.orders.controller;

import com.orders.orders.domain.Customer;
import com.orders.orders.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;
    private static final Logger log = Logger.getLogger(CustomerController.class.getName());

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            Customer createdCustomer = customerService.createCustomer(customer);
            log.info(String.format("Customer created with ID %s", createdCustomer.getId()));
            return ResponseEntity.ok(createdCustomer);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Failed to create customer", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<Customer>> findByPhone(@PathVariable String phone) {
        List<Customer> customers = customerService.getCustomerByPhone(phone);
        if (customers == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customerService.getCustomerByPhone(phone));
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<Customer>> saveAll(@RequestBody List<Customer> customers) {
        if (customers.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            customerService.saveAll(customers);
            return ResponseEntity.ok(customers);
        }
    }

}