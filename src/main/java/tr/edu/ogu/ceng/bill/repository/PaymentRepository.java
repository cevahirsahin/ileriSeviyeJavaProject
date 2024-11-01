package tr.edu.ogu.ceng.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.bill.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
