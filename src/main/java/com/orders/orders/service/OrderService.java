package com.orders.orders.service;

import com.orders.orders.domain.Order;
import com.orders.orders.domain.Truck;
import com.orders.orders.repository.OrderRepository;
import com.orders.orders.repository.TruckRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final TruckRepository truckRepository;

    public OrderService(OrderRepository orderRepository, TruckRepository truckRepository) {
        this.orderRepository = orderRepository;
        this.truckRepository = truckRepository;
    }

    public Order createOrder(Order order) {

        // save order
        return orderRepository.save(order);
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }

    private Truck findAvailableTruck(Double price) {
        // logic for finding available truck
        return null;
    }



    // other methods for handling orders
}

