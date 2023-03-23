package com.orders.orders.service;

import com.orders.orders.domain.Customer;
import com.orders.orders.domain.Item;
import com.orders.orders.domain.Order;
import com.orders.orders.domain.Truck;
import com.orders.orders.repository.ItemRepository;
import com.orders.orders.repository.OrderRepository;
import com.orders.orders.repository.TruckRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final TruckRepository truckRepository;
    private final CustomerService customerService;
    private final ItemRepository itemRepository;

    public OrderService(OrderRepository orderRepository, TruckRepository truckRepository, CustomerService customerService, ItemRepository itemRepository) {
        this.orderRepository = orderRepository;
        this.truckRepository = truckRepository;
        this.customerService = customerService;
        this.itemRepository = itemRepository;
    }

    public Order createOrder(Order order) {
        Customer customer = customerService.getCustomerById(order.getCustomer().getId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        order.setCustomer(customer);

        List<Item> items = new ArrayList<>();
        for (Item item : order.getItems()) {
            Item itemFromDb = itemRepository.findById(item.getId()).orElseThrow(() -> new RuntimeException("Item not found"));
            items.add(itemFromDb);
        }
        order.setItems(items);
        order.setTotalPrice(order.getTotalPrice());

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

