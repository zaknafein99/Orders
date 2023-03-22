package com.orders.orders.controller;

import com.orders.orders.domain.Order;
import com.orders.orders.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("")
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping("")
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }
}
