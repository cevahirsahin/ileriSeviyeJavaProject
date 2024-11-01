package tr.edu.ogu.ceng.bill.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tr.edu.ogu.ceng.bill.entity.Payment;
import tr.edu.ogu.ceng.bill.repository.PaymentRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    private Payment payment;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        payment = new Payment();
        payment.setPaymentId(1L);
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentAmount(new BigDecimal("99.99"));
        payment.setPaymentMethod("Credit Card");
        payment.setPaymentStatus("Completed");
        payment.setTransactionId("TX123456");
        payment.setPaymentGateway("Stripe");
    }

    @Test
    public void testGetAllPayments() {
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(payment);

        when(paymentRepository.findAll()).thenReturn(paymentList);

        List<Payment> result = paymentService.getAllPayments();
        assertEquals(1, result.size());
        assertEquals(payment.getPaymentMethod(), result.get(0).getPaymentMethod());
    }

    @Test
    public void testGetPaymentById() {
        when(paymentRepository.findById(payment.getPaymentId())).thenReturn(Optional.of(payment));

        Optional<Payment> result = paymentService.getPaymentById(payment.getPaymentId());
        assertTrue(result.isPresent());
        assertEquals(payment.getPaymentMethod(), result.get().getPaymentMethod());
    }

    @Test
    public void testCreatePayment() {
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment createdPayment = paymentService.createPayment(payment);
        assertNotNull(createdPayment);
        assertEquals(payment.getPaymentMethod(), createdPayment.getPaymentMethod());
    }

    @Test
    public void testUpdatePayment() {
        Payment updatedPayment = new Payment();
        updatedPayment.setPaymentDate(LocalDateTime.now());
        updatedPayment.setPaymentAmount(new BigDecimal("79.99"));
        updatedPayment.setPaymentMethod("PayPal");
        updatedPayment.setPaymentStatus("Pending");
        updatedPayment.setTransactionId("TX654321");
        updatedPayment.setPaymentGateway("PayPal");

        when(paymentRepository.findById(payment.getPaymentId())).thenReturn(Optional.of(payment));
        when(paymentRepository.save(any(Payment.class))).thenReturn(payment);

        Payment result = paymentService.updatePayment(payment.getPaymentId(), updatedPayment);
        assertEquals(payment.getPaymentId(), result.getPaymentId());
        assertEquals("PayPal", result.getPaymentMethod());
    }

    @Test
    public void testDeletePayment() {
        doNothing().when(paymentRepository).deleteById(payment.getPaymentId());

        assertDoesNotThrow(() -> paymentService.deletePayment(payment.getPaymentId()));
        verify(paymentRepository, times(1)).deleteById(payment.getPaymentId());
    }
}
