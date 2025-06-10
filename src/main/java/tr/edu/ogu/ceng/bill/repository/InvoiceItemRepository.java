package tr.edu.ogu.ceng.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.bill.entity.InvoiceItem;

import java.util.List;

public interface InvoiceItemRepository extends JpaRepository<InvoiceItem, Long> {

    // Finds InvoiceItems by the invoiceId
    List<InvoiceItem> findByInvoice_Id(Long invoiceId);

    // Custom method: Get all invoice items by product name
    List<InvoiceItem> findByProductName(String productName);

    // Custom method: Get all invoice items by invoice ID and product ID
    List<InvoiceItem> findByInvoice_InvoiceIdAndProduct_ProductId(Long invoiceId, Long productId);
}
