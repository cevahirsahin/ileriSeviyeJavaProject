package tr.edu.ogu.ceng.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.ogu.ceng.bill.entity.InvoiceItem;
import tr.edu.ogu.ceng.bill.repository.InvoiceItemRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceItemService {

    private final InvoiceItemRepository invoiceItemRepository;

    @Autowired
    public InvoiceItemService(InvoiceItemRepository invoiceItemRepository) {
        this.invoiceItemRepository = invoiceItemRepository;
    }

    public List<InvoiceItem> getAllInvoiceItems() {
        return invoiceItemRepository.findAll();
    }

    public Optional<InvoiceItem> getInvoiceItemById(Long id) {
        return invoiceItemRepository.findById(id);
    }

    public InvoiceItem createInvoiceItem(InvoiceItem invoiceItem) {
        return invoiceItemRepository.save(invoiceItem);
    }

    public InvoiceItem updateInvoiceItem(Long id, InvoiceItem invoiceItemDetails) {
        InvoiceItem invoiceItem = invoiceItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("InvoiceItem not found"));
        invoiceItem.setInvoice(invoiceItemDetails.getInvoice());
        invoiceItem.setProduct(invoiceItemDetails.getProduct());
        invoiceItem.setProductName(invoiceItemDetails.getProductName());
        invoiceItem.setQuantity(invoiceItemDetails.getQuantity());
        invoiceItem.setUnitPrice(invoiceItemDetails.getUnitPrice());
        invoiceItem.setTotalPrice(invoiceItemDetails.getTotalPrice());
        invoiceItem.setDiscountAmount(invoiceItemDetails.getDiscountAmount());
        return invoiceItemRepository.save(invoiceItem);
    }

    public void deleteInvoiceItem(Long id) {
        invoiceItemRepository.deleteById(id);
    }
}