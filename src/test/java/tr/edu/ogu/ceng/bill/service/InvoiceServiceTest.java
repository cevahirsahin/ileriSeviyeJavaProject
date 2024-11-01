package tr.edu.ogu.ceng.bill.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tr.edu.ogu.ceng.bill.entity.Invoice;
import tr.edu.ogu.ceng.bill.repository.InvoiceRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    private Invoice invoice;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        invoice = new Invoice();
        invoice.setInvoiceId(1L);
        invoice.setOrderId(1001L);
        invoice.setCustomer(null); // Assuming customer is set correctly in actual tests
        invoice.setInvoiceDate(LocalDateTime.now());
        invoice.setDueDate(LocalDateTime.now().plusDays(30));
        invoice.setTotalAmount(new BigDecimal("200.00"));
        invoice.setTaxAmount(new BigDecimal("20.00"));
        invoice.setDiscount(new BigDecimal("10.00"));
        invoice.setStatus("Pending");
        invoice.setPaymentMethod("Credit Card");
        invoice.setCurrency("USD");
        invoice.setNotes("Test invoice");
    }

    @Test
    public void testGetAllInvoices() {
        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);

        when(invoiceRepository.findAll()).thenReturn(invoiceList);

        List<Invoice> result = invoiceService.getAllInvoices();
        assertEquals(1, result.size());
        assertEquals(invoice.getInvoiceId(), result.get(0).getInvoiceId());
    }

    @Test
    public void testGetInvoiceById() {
        when(invoiceRepository.findById(invoice.getInvoiceId())).thenReturn(Optional.of(invoice));

        Optional<Invoice> result = invoiceService.getInvoiceById(invoice.getInvoiceId());
        assertTrue(result.isPresent());
        assertEquals(invoice.getInvoiceId(), result.get().getInvoiceId());
    }

    @Test
    public void testCreateInvoice() {
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);

        Invoice createdInvoice = invoiceService.createInvoice(invoice);
        assertNotNull(createdInvoice);
        assertEquals(invoice.getInvoiceId(), createdInvoice.getInvoiceId());
    }

    @Test
    public void testUpdateInvoice() {
        Invoice updatedInvoice = new Invoice();
        updatedInvoice.setOrderId(1002L);
        updatedInvoice.setCustomer(null); // Update as needed
        updatedInvoice.setInvoiceDate(LocalDateTime.now());
        updatedInvoice.setDueDate(LocalDateTime.now().plusDays(45));
        updatedInvoice.setTotalAmount(new BigDecimal("250.00"));
        updatedInvoice.setTaxAmount(new BigDecimal("25.00"));
        updatedInvoice.setDiscount(new BigDecimal("15.00"));
        updatedInvoice.setStatus("Paid");
        updatedInvoice.setPaymentMethod("PayPal");
        updatedInvoice.setCurrency("USD");
        updatedInvoice.setNotes("Updated invoice");

        when(invoiceRepository.findById(invoice.getInvoiceId())).thenReturn(Optional.of(invoice));
        when(invoiceRepository.save(any(Invoice.class))).thenReturn(invoice);

        Invoice result = invoiceService.updateInvoice(invoice.getInvoiceId(), updatedInvoice);
        assertEquals(invoice.getInvoiceId(), result.getInvoiceId());
        assertEquals("Paid", result.getStatus());
    }

    @Test
    public void testDeleteInvoice() {
        doNothing().when(invoiceRepository).deleteById(invoice.getInvoiceId());

        assertDoesNotThrow(() -> invoiceService.deleteInvoice(invoice.getInvoiceId()));
        verify(invoiceRepository, times(1)).deleteById(invoice.getInvoiceId());
    }
}
