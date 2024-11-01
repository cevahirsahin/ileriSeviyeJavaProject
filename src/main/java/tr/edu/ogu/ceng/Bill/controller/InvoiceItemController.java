package tr.edu.ogu.ceng.Bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.ogu.ceng.Bill.entity.InvoiceItem;
import tr.edu.ogu.ceng.Bill.service.InvoiceItemService;

import java.util.List;

@RestController
@RequestMapping("/api/invoice-items")
public class InvoiceItemController {

    private final InvoiceItemService invoiceItemService;

    @Autowired
    public InvoiceItemController(InvoiceItemService invoiceItemService) {
        this.invoiceItemService = invoiceItemService;
    }

    @GetMapping
    public ResponseEntity<List<InvoiceItem>> getAllInvoiceItems() {
        List<InvoiceItem> invoiceItems = invoiceItemService.getAllInvoiceItems();
        return ResponseEntity.ok(invoiceItems);
    }

    @GetMapping("/{id}")
    public ResponseEntity<InvoiceItem> getInvoiceItemById(@PathVariable Long id) {
        return invoiceItemService.getInvoiceItemById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<InvoiceItem> createInvoiceItem(@RequestBody InvoiceItem invoiceItem) {
        InvoiceItem createdInvoiceItem = invoiceItemService.createInvoiceItem(invoiceItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoiceItem);
    }

    @PutMapping("/{id}")
    public ResponseEntity<InvoiceItem> updateInvoiceItem(@PathVariable Long id, @RequestBody InvoiceItem invoiceItem) {
        InvoiceItem updatedInvoiceItem = invoiceItemService.updateInvoiceItem(id, invoiceItem);
        return ResponseEntity.ok(updatedInvoiceItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoiceItem(@PathVariable Long id) {
        invoiceItemService.deleteInvoiceItem(id);
        return ResponseEntity.noContent().build();
    }
}