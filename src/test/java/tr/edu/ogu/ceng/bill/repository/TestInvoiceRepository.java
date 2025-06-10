package tr.edu.ogu.ceng.bill.service;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.containers.PostgreSQLContainer;
import tr.edu.ogu.ceng.bill.entity.Customer;
import tr.edu.ogu.ceng.bill.entity.Invoice;
import tr.edu.ogu.ceng.bill.repository.InvoiceRepository;
import tr.edu.ogu.ceng.bill.repository.CustomerRepository;  // Eğer CustomerRepository'e ihtiyacınız varsa ekleyin

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class TestInvoiceRepository {

    private static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("testdb")
                    .withUsername("user")
                    .withPassword("password");

    static {
        postgresContainer.start(); // PostgreSQL konteynerini başlatıyoruz
    }

    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    CustomerRepository customerRepository; // Customer repository'sini ekleyin

    @Test
    public void testFindByTotalAmount() {
        // Yeni bir Customer oluştur
        Customer customer1 = new Customer();
        customer1.setFirstName("John");
        customer1.setLastName("Doe");
        customer1.setBillingAddress("Some Address");
        customer1.setEmail("john.doe@example.com");

        // Customer'ı kaydet
        customer1 = customerRepository.save(customer1);

        // Yeni bir Invoice oluştur
        Invoice invoice1 = new Invoice();
        invoice1.setOrderId(1L); // Order ID'yi belirleyebilirsiniz
        invoice1.setCustomer(customer1); // Invoice'a müşteri ekle
        invoice1.setTotalAmount(new BigDecimal("150.75")); // Farklı bir totalAmount

        // Farklı bir müşteri ile ikinci bir invoice oluştur
        Customer customer2 = new Customer();
        customer2.setFirstName("Jane");
        customer2.setLastName("Doe");
        customer2.setBillingAddress("Another Address");
        customer2.setEmail("jane.doe@example.com");

        // Customer'ı kaydet
        customer2 = customerRepository.save(customer2);

        Invoice invoice2 = new Invoice();
        invoice2.setOrderId(2L); // Farklı bir Order ID
        invoice2.setCustomer(customer2); // Invoice'a farklı müşteri ekle
        invoice2.setTotalAmount(new BigDecimal("150.75")); // Aynı totalAmount

        // Invoice'ları kaydet
        invoiceRepository.save(invoice1);
        invoiceRepository.save(invoice2);

        // totalAmount ile Invoice sorgula
        List<Invoice> invoices = invoiceRepository.findByTotalAmount(new BigDecimal("150.75"));

        // Sonuçları kontrol et
        assertThat(invoices).isNotNull();
        assertThat(invoices.size()).isEqualTo(2); // İki sonuç döndürülmeli
        assertThat(invoices.get(0).getTotalAmount()).isEqualTo(new BigDecimal("150.75"));
        assertThat(invoices.get(1).getTotalAmount()).isEqualTo(new BigDecimal("150.75"));
        assertThat(invoices.get(0).getCustomer()).isNotNull(); // İlk invoice'un müşteri nesnesinin null olmaması gerektiğini kontrol et
        assertThat(invoices.get(1).getCustomer()).isNotNull(); // İkinci invoice'un müşteri nesnesinin null olmaması gerektiğini kontrol et
    }
}