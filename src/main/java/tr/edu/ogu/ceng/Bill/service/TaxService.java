package tr.edu.ogu.ceng.Bill.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.edu.ogu.ceng.Bill.entity.Tax;
import tr.edu.ogu.ceng.Bill.repository.TaxRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaxService {

    private final TaxRepository taxRepository;

    @Autowired
    public TaxService(TaxRepository taxRepository) {
        this.taxRepository = taxRepository;
    }

    public List<Tax> getAllTaxes() {
        return taxRepository.findAll();
    }

    public Optional<Tax> getTaxById(Long id) {
        return taxRepository.findById(id);
    }

    public Tax createTax(Tax tax) {
        return taxRepository.save(tax);
    }

    public Tax updateTax(Long id, Tax taxDetails) {
        Tax tax = taxRepository.findById(id).orElseThrow(() -> new RuntimeException("Tax not found"));
        tax.setTaxName(taxDetails.getTaxName());
        tax.setTaxRate(taxDetails.getTaxRate());
        tax.setAppliedAmount(taxDetails.getAppliedAmount());
        // Diğer alanları güncelleyebilirsiniz
        return taxRepository.save(tax);
    }

    public void deleteTax(Long id) {
        taxRepository.deleteById(id);
    }
}
