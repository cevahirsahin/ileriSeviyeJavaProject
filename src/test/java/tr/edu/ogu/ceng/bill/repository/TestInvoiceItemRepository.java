package tr.edu.ogu.ceng.bill.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tr.edu.ogu.ceng.bill.entity.Invoice;
import tr.edu.ogu.ceng.bill.entity.InvoiceItem;
import tr.edu.ogu.ceng.bill.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestInvoiceItemRepository {

    @Autowired
    private InvoiceItemRepository invoiceItemRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ProductRepository productRepository;

    private Invoice invoice;
    private Product product;

    @BeforeEach
    public void setup() {
        // Create and save a product
        product = new Product();
        product.setName("Test Product");
        product.setDescription("A product for testing");
        product.setPrice(BigDecimal.valueOf(100));
        product.setStockQuantity(50);
        product.setCreatedAt(LocalDateTime.now());
        productRepository.save(product);

        // Create and save an invoice
        invoice = new Invoice();
        invoice.setOrderId(12345L);
        invoice.setCustomer(null); // Assuming customer is set elsewhere
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setDueDate(LocalDateTime.now().plusDays(30));
        invoice.setTotalAmount(BigDecimal.valueOf(500));
        invoice.setCreatedAt(LocalDateTime.now());
        invoiceRepository.save(invoice);
    }

    @Test
    public void testFindByInvoiceId() {
        // Create and save InvoiceItem
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice(invoice);
        invoiceItem.setProduct(product);
        invoiceItem.setProductName("Test Product");
        invoiceItem.setQuantity(2);
        invoiceItem.setUnitPrice(BigDecimal.valueOf(100));
        invoiceItem.setTotalPrice(BigDecimal.valueOf(200));
        invoiceItem.setCreatedAt(LocalDateTime.now());
        invoiceItemRepository.save(invoiceItem);

        // Test the custom method to find by invoice ID
        List<InvoiceItem> invoiceItems = invoiceItemRepository.findByInvoice_Id(invoice.getInvoiceId());
        assertFalse(invoiceItems.isEmpty(), "Invoice items should not be empty");
        assertEquals(1, invoiceItems.size(), "There should be one invoice item for this invoice");
    }

    @Test
    public void testFindByProductName() {
        // Create and save InvoiceItem
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice(invoice);
        invoiceItem.setProduct(product);
        invoiceItem.setProductName("Test Product");
        invoiceItem.setQuantity(1);
        invoiceItem.setUnitPrice(BigDecimal.valueOf(100));
        invoiceItem.setTotalPrice(BigDecimal.valueOf(100));
        invoiceItem.setCreatedAt(LocalDateTime.now());
        invoiceItemRepository.save(invoiceItem);

        // Test the custom method to find by product name
        List<InvoiceItem> invoiceItems = invoiceItemRepository.findByProductName("Test Product");
        assertFalse(invoiceItems.isEmpty(), "Invoice items for 'Test Product' should not be empty");
        assertEquals(1, invoiceItems.size(), "There should be one invoice item for 'Test Product'");
    }

    @Test
    public void testFindByInvoiceIdAndProductId() {
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoice(invoice);
        invoiceItem.setProduct(product);
        invoiceItem.setProductName("Test Product");
        invoiceItem.setQuantity(1);
        invoiceItem.setUnitPrice(BigDecimal.valueOf(100));
        invoiceItem.setTotalPrice(BigDecimal.valueOf(100));
        invoiceItem.setCreatedAt(LocalDateTime.now());
        invoiceItemRepository.save(invoiceItem);

        List<InvoiceItem> invoiceItems = invoiceItemRepository.findByInvoice_InvoiceIdAndProduct_ProductId(invoice.getInvoiceId(), product.getProductId());
        assertFalse(invoiceItems.isEmpty(), "Invoice items for the given invoice and product should not be empty");
        assertEquals(1, invoiceItems.size(), "There should be one invoice item for this invoice and product");
    }

}
