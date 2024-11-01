package tr.edu.ogu.ceng.Bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Bill.entity.Tax;

public interface TaxRepository extends JpaRepository<Tax, Long> {
}
