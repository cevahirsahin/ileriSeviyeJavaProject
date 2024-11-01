package tr.edu.ogu.ceng.bill.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import tr.edu.ogu.ceng.bill.controller.CustomerController;
import tr.edu.ogu.ceng.bill.entity.Customer;
import tr.edu.ogu.ceng.bill.repository.CustomerRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test") // Ensure you have a test profile for in-memory DB
@ExtendWith(SpringExtension.class)
public class CustomerServiceTest {

    private static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("testdb")
                    .withUsername("user")
                    .withPassword("password");

    static {
        postgresContainer.start(); // Start the PostgreSQL container
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerController customerController;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        customer = new Customer();
        customer.setFirstName("John");
        customer.setLastName("Doe");
        customer.setEmail("john.doe@example.com");
        customer.setPhoneNumber("1234567890");
        customer.setBillingAddress("123 Test St");
        customer.setShippingAddress("456 Another St");
        customerRepository.save(customer); // Persist to the database for tests
    }

    @Test
    public void testGetAllCustomers() {
        ResponseEntity<List<Customer>> response = customerController.getAllCustomers();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetCustomerById() {
        ResponseEntity<Customer> response = customerController.getCustomerById(customer.getCustomerId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("John", response.getBody().getFirstName());
        assertEquals("Doe", response.getBody().getLastName());
    }

    @Test
    public void testCreateCustomer() {
        Customer newCustomer = new Customer();
        newCustomer.setFirstName("Jane");
        newCustomer.setLastName("Doe");
        newCustomer.setEmail("jane.doe@example.com");
        newCustomer.setPhoneNumber("0987654321");
        newCustomer.setBillingAddress("789 Test Ave");
        newCustomer.setShippingAddress("101 Another Rd");

        ResponseEntity<Customer> response = customerController.createCustomer(newCustomer);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Jane", response.getBody().getFirstName());
    }

    @Test
    public void testUpdateCustomer() {
        customer.setFirstName("Johnny");
        customer.setLastName("Doey");
        ResponseEntity<Customer> response = customerController.updateCustomer(customer.getCustomerId(), customer);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Johnny", response.getBody().getFirstName());
        assertEquals("Doey", response.getBody().getLastName());
    }

    @Test
    public void testDeleteCustomer() {
        ResponseEntity<Void> response = customerController.deleteCustomer(customer.getCustomerId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
