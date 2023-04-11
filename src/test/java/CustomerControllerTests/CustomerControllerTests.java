package CustomerControllerTests;

import com.orders.orders.controller.CustomerController;
import com.orders.orders.domain.Customer;
import com.orders.orders.service.CustomerService;
import org.hibernate.service.spi.ServiceException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerControllerTests {

    @Test
    public void testGetAllCustomers() {

        CustomerService customerService = mock(CustomerService.class);
        CustomerController customerController = new CustomerController(customerService);
        // create pageable object
        Pageable pageable = PageRequest.of(0, 10);

        // create list of customers
        List<Customer> customers = new ArrayList<>();

        Customer c1 = new Customer();
        c1.setAddress("Address");
        c1.setName("John");
        c1.setPhoneNumber("123456");
        c1.setType('C');
        c1.setState('A');
        customers.add(c1);

        Customer c2 = new Customer();
        c2.setAddress("Address2");
        c2.setName("Jane");
        c2.setPhoneNumber("567890");
        c2.setType('C');
        c2.setState('A');
        customers.add(c2);

        // create a page of customers
        Page<Customer> customerPage = new PageImpl<>(customers);

        // mock the customer service to return the page of customers
        when(customerService.getAllCustomers(pageable)).thenReturn(customerPage);

        // call the customer controller method to get the page of customers
        ResponseEntity<Page<Customer>> responseEntity = customerController.getAllCustomers(pageable);

        // assert that the response entity has a status of OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // assert that the response entity contains the expected page of customers
        assertEquals(customerPage, responseEntity.getBody());
    }
    @Test
    public void testCreateCustomer() {

        CustomerService customerService = mock(CustomerService.class);
        CustomerController customerController = new CustomerController(customerService);

        // create a customer object
        Customer customer = new Customer();
        customer.setAddress("Address");
        customer.setName("John");
        customer.setPhoneNumber("123456");
        customer.setType('C');
        customer.setState('A');

        // mock the customer service to return the created customer
        when(customerService.createCustomer(customer)).thenReturn(customer);

        // call the customer controller method to create the customer
        ResponseEntity<Customer> responseEntity = customerController.createCustomer(customer);

        // assert that the response entity has a status of OK
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // assert that the response entity contains the created customer
        assertEquals(customer, responseEntity.getBody());
    }

    @Test
    public void testCreateCustomerWithException() throws ServiceException {

        CustomerService customerService = mock(CustomerService.class);
        CustomerController customerController = new CustomerController(customerService);

        // create a customer object
        Customer customer = new Customer();
        customer.setAddress("Address");
        customer.setName("John");
        customer.setPhoneNumber("123456");
        customer.setType('C');
        customer.setState('A');

        // mock the customer service to throw an exception
        Mockito.doThrow(new ServiceException("Unable to create customer.")).when(customerService).createCustomer(customer);

        // call the customer controller method to create the customer
        ResponseEntity<Customer> responseEntity = customerController.createCustomer(customer);

        // assert that the response entity has a status of Bad Request
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        // verify that the customer service's createCustomer method was called with the correct customer object
        Mockito.verify(customerService).createCustomer(customer);
    }
}
