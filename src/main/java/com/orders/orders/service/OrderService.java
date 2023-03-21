package com.orders.orders.service;

import com.orders.orders.domain.Order;
import com.orders.orders.domain.Truck;
import com.orders.orders.repository.OrderRepository;
import com.orders.orders.repository.TruckRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final TruckRepository truckRepository;

    public OrderService(OrderRepository orderRepository, TruckRepository truckRepository) {
        this.orderRepository = orderRepository;
        this.truckRepository = truckRepository;
    }

    public Order createOrder(Order order) {
        // logic for assigning order to truck
        Truck truck = findAvailableTruck(order.getPrice());
        order.setTruck(truck);
        // save order
        return orderRepository.save(order);
    }

    private Truck findAvailableTruck(Double price) {
        // logic for finding available truck
        return null;
    }

    // other methods for handling orders
}

