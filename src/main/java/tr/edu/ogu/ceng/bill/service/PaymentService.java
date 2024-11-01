package tr.edu.ogu.ceng.bill.service;

import tr.edu.ogu.ceng.bill.entity.Payment;
import tr.edu.ogu.ceng.bill.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentService(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public Optional<Payment> getPaymentById(Long paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public Payment updatePayment(Long paymentId, Payment paymentDetails) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id " + paymentId));

        payment.setPaymentDate(paymentDetails.getPaymentDate());
        payment.setPaymentAmount(paymentDetails.getPaymentAmount());
        payment.setPaymentMethod(paymentDetails.getPaymentMethod());
        payment.setPaymentStatus(paymentDetails.getPaymentStatus());
        payment.setTransactionId(paymentDetails.getTransactionId());
        payment.setPaymentGateway(paymentDetails.getPaymentGateway());

        return paymentRepository.save(payment);
    }

    public void deletePayment(Long paymentId) {
        paymentRepository.deleteById(paymentId);
    }
}

