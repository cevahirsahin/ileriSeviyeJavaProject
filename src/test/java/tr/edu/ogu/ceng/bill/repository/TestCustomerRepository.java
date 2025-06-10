package tr.edu.ogu.ceng.bill.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.QueryAnnotation;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import tr.edu.ogu.ceng.bill.entity.Customer;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
public class TestCustomerRepository {
    private static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("testdb")
                    .withUsername("user")
                    .withPassword("password");
    static {
        postgresContainer.start(); // Start the PostgreSQL container
    }


    @Autowired
    CustomerRepository customerRepository;

    @Test
    public void Test (){
        Customer customer= new Customer();
        customer.setFirstName("John2");
        customer.setLastName("Doe2");
        customer.setEmail("jane.doe@example.com");
        customer.setPhoneNumber("0987654321");
        customer.setBillingAddress("789 Test Ave");
        customer.setShippingAddress("101 Another Rd");


        customerRepository.save(customer);

    }




    @Test
    void Test2(){
        Customer customer= new Customer();
        customer.setFirstName("John4");
        customer.setLastName("Doe4");
        customer.setEmail("jane.doe4@example.com");
        customer.setPhoneNumber("0987654321");
        customer.setBillingAddress("789 Test Ave");
        customer.setShippingAddress("101 Another Rd");


        customerRepository.save(customer);

        Customer result=customerRepository.getByfirstName("John3");


        assertThat(result).isNotNull();
        assertThat(result.getFirstName()).isEqualTo("John3");
        assertThat(result.getLastName()).isEqualTo("Doe3");


    }
    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {



        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getPassword);


    }




}
