package tr.edu.ogu.ceng.bill.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tr.edu.ogu.ceng.bill.entity.Invoice;
import tr.edu.ogu.ceng.bill.entity.InvoiceItem;
import tr.edu.ogu.ceng.bill.entity.Product;
import tr.edu.ogu.ceng.bill.repository.InvoiceItemRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InvoiceItemServiceTest {

    @Mock
    private InvoiceItemRepository invoiceItemRepository;

    @InjectMocks
    private InvoiceItemService invoiceItemService;

    private InvoiceItem invoiceItem;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // Mocking dependencies
        Invoice invoice = new Invoice(); // Create an invoice instance as needed
        Product product = new Product(); // Create a product instance as needed

        invoiceItem = new InvoiceItem();
        invoiceItem.setItemId(1L);
        invoiceItem.setInvoice(invoice);
        invoiceItem.setProduct(product);
        invoiceItem.setProductName("Sample Product");
        invoiceItem.setQuantity(2);
        invoiceItem.setUnitPrice(new BigDecimal("50.00"));
        invoiceItem.setTotalPrice(new BigDecimal("100.00"));
        invoiceItem.setDiscountAmount(new BigDecimal("5.00"));
    }

    @Test
    public void testGetAllInvoiceItems() {
        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);

        when(invoiceItemRepository.findAll()).thenReturn(invoiceItemList);

        List<InvoiceItem> result = invoiceItemService.getAllInvoiceItems();
        assertEquals(1, result.size());
        assertEquals(invoiceItem.getItemId(), result.get(0).getItemId());
    }

    @Test
    public void testGetInvoiceItemById() {
        when(invoiceItemRepository.findById(invoiceItem.getItemId())).thenReturn(Optional.of(invoiceItem));

        Optional<InvoiceItem> result = invoiceItemService.getInvoiceItemById(invoiceItem.getItemId());
        assertTrue(result.isPresent());
        assertEquals(invoiceItem.getItemId(), result.get().getItemId());
    }

    @Test
    public void testCreateInvoiceItem() {
        when(invoiceItemRepository.save(any(InvoiceItem.class))).thenReturn(invoiceItem);

        InvoiceItem createdInvoiceItem = invoiceItemService.createInvoiceItem(invoiceItem);
        assertNotNull(createdInvoiceItem);
        assertEquals(invoiceItem.getItemId(), createdInvoiceItem.getItemId());
    }

    @Test
    public void testUpdateInvoiceItem() {
        InvoiceItem updatedInvoiceItem = new InvoiceItem();
        updatedInvoiceItem.setInvoice(invoiceItem.getInvoice());
        updatedInvoiceItem.setProduct(invoiceItem.getProduct());
        updatedInvoiceItem.setProductName("Updated Product");
        updatedInvoiceItem.setQuantity(3);
        updatedInvoiceItem.setUnitPrice(new BigDecimal("60.00"));
        updatedInvoiceItem.setTotalPrice(new BigDecimal("180.00"));
        updatedInvoiceItem.setDiscountAmount(new BigDecimal("10.00"));

        when(invoiceItemRepository.findById(invoiceItem.getItemId())).thenReturn(Optional.of(invoiceItem));
        when(invoiceItemRepository.save(any(InvoiceItem.class))).thenReturn(invoiceItem);

        InvoiceItem result = invoiceItemService.updateInvoiceItem(invoiceItem.getItemId(), updatedInvoiceItem);
        assertEquals(invoiceItem.getItemId(), result.getItemId());
        assertEquals("Updated Product", result.getProductName());
    }

    @Test
    public void testDeleteInvoiceItem() {
        doNothing().when(invoiceItemRepository).deleteById(invoiceItem.getItemId());

        assertDoesNotThrow(() -> invoiceItemService.deleteInvoiceItem(invoiceItem.getItemId()));
        verify(invoiceItemRepository, times(1)).deleteById(invoiceItem.getItemId());
    }
}
