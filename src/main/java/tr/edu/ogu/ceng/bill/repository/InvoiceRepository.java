package tr.edu.ogu.ceng.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.bill.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice,Long>{
        }
