package tr.edu.ogu.ceng.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.bill.entity.Tax;

public interface TaxRepository extends JpaRepository<Tax, Long> {
}
