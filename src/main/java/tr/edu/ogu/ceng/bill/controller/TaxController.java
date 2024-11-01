package tr.edu.ogu.ceng.bill.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.ogu.ceng.bill.entity.Tax;
import tr.edu.ogu.ceng.bill.service.TaxService;

import java.util.List;

@RestController
@RequestMapping("/api/taxes")
public class TaxController {

    private final TaxService taxService;

    @Autowired
    public TaxController(TaxService taxService) {
        this.taxService = taxService;
    }

    @GetMapping
    public ResponseEntity<List<Tax>> getAllTaxes() {
        List<Tax> taxes = taxService.getAllTaxes();
        return ResponseEntity.ok(taxes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tax> getTaxById(@PathVariable Long id) {
        return taxService.getTaxById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Tax> createTax(@RequestBody Tax tax) {
        Tax createdTax = taxService.createTax(tax);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTax);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tax> updateTax(@PathVariable Long id, @RequestBody Tax tax) {
        Tax updatedTax = taxService.updateTax(id, tax);
        return ResponseEntity.ok(updatedTax);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTax(@PathVariable Long id) {
        taxService.deleteTax(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Long> getTaxId(@PathVariable Long id) {
        return taxService.getTaxById(id)
                .map(tax -> ResponseEntity.ok(tax.getTaxId()))
                .orElse(ResponseEntity.notFound().build());
    }
}