package com.orders.orders.controller;

import com.orders.orders.domain.Customer;
import com.orders.orders.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PostMapping("")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerService.createCustomer(customer);
    }

    @GetMapping("/phone/{phone}")
    public ResponseEntity<List<Customer>> findByPhone(@PathVariable String phone) {
        List<Customer> customers = customerService.getCustomerByPhone(phone);
        if (customers == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(customers);
    }

    @PostMapping("/save-all")
    public ResponseEntity<List<Customer>> saveAll(@RequestBody List<Customer> customers) {
        List<Customer> savedEntities = customerService.saveAll(customers);
        return ResponseEntity.ok(savedEntities);
    }

}