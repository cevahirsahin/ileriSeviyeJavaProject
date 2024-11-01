package tr.edu.ogu.ceng.bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.ogu.ceng.bill.entity.Invoice;
import tr.edu.ogu.ceng.bill.repository.InvoiceRepository;

import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Optional<Invoice> getInvoiceById(Long id) {
        return invoiceRepository.findById(id);
    }

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Invoice updateInvoice(Long id, Invoice invoiceDetails) {
        Invoice invoice = invoiceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Invoice not found"));
        invoice.setOrderId(invoiceDetails.getOrderId());
        invoice.setCustomer(invoiceDetails.getCustomer());
        invoice.setInvoiceDate(invoiceDetails.getInvoiceDate());
        invoice.setDueDate(invoiceDetails.getDueDate());
        invoice.setTotalAmount(invoiceDetails.getTotalAmount());
        invoice.setTaxAmount(invoiceDetails.getTaxAmount());
        invoice.setDiscount(invoiceDetails.getDiscount());
        invoice.setStatus(invoiceDetails.getStatus());
        invoice.setPaymentMethod(invoiceDetails.getPaymentMethod());
        invoice.setCurrency(invoiceDetails.getCurrency());
        invoice.setNotes(invoiceDetails.getNotes());
        return invoiceRepository.save(invoice);
    }

    public void deleteInvoice(Long id) {
        invoiceRepository.deleteById(id);
    }
}