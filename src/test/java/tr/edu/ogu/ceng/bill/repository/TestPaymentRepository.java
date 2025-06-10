package tr.edu.ogu.ceng.bill.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import tr.edu.ogu.ceng.bill.entity.Payment;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class TestPaymentRepository {

    @Autowired
    private PaymentRepository paymentRepository;

    private Payment payment;

    @BeforeEach
    public void setUp() {
        payment = new Payment();
        payment.setInvoice(null); // Set the associated Invoice (you can set a mock if needed)
        payment.setPaymentDate(LocalDateTime.now());
        payment.setPaymentAmount(BigDecimal.valueOf(100.00));
        payment.setPaymentMethod("Credit Card");
        payment.setPaymentStatus("Completed");
        payment.setTransactionId("12345");
        payment.setPaymentGateway("Stripe");
    }

    @Test
    public void testSavePayment() {
        Payment savedPayment = paymentRepository.save(payment);
        assertNotNull(savedPayment.getPaymentId());
        assertEquals(payment.getPaymentAmount(), savedPayment.getPaymentAmount());
        assertEquals(payment.getPaymentMethod(), savedPayment.getPaymentMethod());
    }

    @Test
    public void testFindPaymentById() {
        Payment savedPayment = paymentRepository.save(payment);
        Payment foundPayment = paymentRepository.findById(savedPayment.getPaymentId()).orElse(null);
        assertNotNull(foundPayment);
        assertEquals(savedPayment.getPaymentId(), foundPayment.getPaymentId());
    }

    @Test
    public void testDeletePayment() {
        Payment savedPayment = paymentRepository.save(payment);
        Long paymentId = savedPayment.getPaymentId();
        paymentRepository.delete(savedPayment);
        assertFalse(paymentRepository.findById(paymentId).isPresent());
    }

    @Test
    public void testUpdatePayment() {
        Payment savedPayment = paymentRepository.save(payment);
        savedPayment.setPaymentStatus("Failed");
        Payment updatedPayment = paymentRepository.save(savedPayment);
        assertEquals("Failed", updatedPayment.getPaymentStatus());
    }
}
