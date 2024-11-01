package tr.edu.ogu.ceng.Bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Bill.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice,Long>{
        }
