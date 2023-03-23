package com.orders.orders.bootstrap;

import com.orders.orders.domain.Customer;
import com.orders.orders.domain.Item;
import com.orders.orders.domain.Order;
import com.orders.orders.domain.Truck;
import com.orders.orders.service.CustomerService;
import com.orders.orders.service.ItemService;
import com.orders.orders.service.OrderService;
import com.orders.orders.service.TruckService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class BootstrapData implements CommandLineRunner {

    private final CustomerService customerService;
    private final ItemService itemService;
    private final OrderService orderService;
    private final TruckService truckService;

    public BootstrapData(CustomerService customerService, ItemService itemService, OrderService orderService, TruckService truckService) {
        this.customerService = customerService;
        this.itemService = itemService;
        this.orderService = orderService;
        this.truckService = truckService;
    }

    @Override
    public void run(String... args) throws Exception {

        // create two items
        Item item1 = new Item();
        item1.setName("Item 1");
        item1.setPrice(10.0);

        Item item2 = new Item();
        item2.setName("Item 2");
        item2.setPrice(20.0);

        // save the items to the database
        itemService.CreateItem(item1);
        itemService.CreateItem(item2);

        Truck truck1 = new Truck();
        truck1.setName("Truck1");

        truckService.CreateTruck(truck1);

        // create a customer
        Customer customer = new Customer();
        customer.setName("John Doe");
        customer.setPhoneNumber("12345678");
        customer.setAddress("Main 123");
        customer.setType('C');
        customer.setState('A');

        // save the customer to the database
        customerService.createCustomer(customer);

        // create an order and add the two items to it
        Order order = new Order();
        Date date = new Date();
        order.setDate(date);
        order.setTruck(truck1);
        order.setCustomer(customer);
        order.getItems().add(item1);
        order.getItems().add(item2);

        // save the order to the database
        orderService.createOrder(order);


    }
}
