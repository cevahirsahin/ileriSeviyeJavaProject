package tr.edu.ogu.ceng.Bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Bill.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
