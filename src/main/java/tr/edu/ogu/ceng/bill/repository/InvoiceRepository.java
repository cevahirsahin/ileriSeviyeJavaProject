package tr.edu.ogu.ceng.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.edu.ogu.ceng.bill.entity.Invoice;

import java.math.BigDecimal;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice,Long>{
        @Query("SELECT i FROM Invoice i WHERE i.totalAmount= :totalAmount")
        List<Invoice> findByTotalAmount(@Param("totalAmount") BigDecimal totalAmount);

}
