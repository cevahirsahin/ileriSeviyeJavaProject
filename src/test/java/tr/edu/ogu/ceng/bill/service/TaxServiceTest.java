package tr.edu.ogu.ceng.bill.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tr.edu.ogu.ceng.bill.entity.Tax;
import tr.edu.ogu.ceng.bill.repository.TaxRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TaxServiceTest {

    @Mock
    private TaxRepository taxRepository;

    @InjectMocks
    private TaxService taxService;

    private Tax tax;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        tax = new Tax();
        tax.setTaxId(1L);
        tax.setTaxName("VAT");
        tax.setTaxRate(new BigDecimal("20.00"));
        tax.setAppliedAmount(new BigDecimal("100.00"));
    }

    @Test
    public void testGetAllTaxes() {
        List<Tax> taxList = new ArrayList<>();
        taxList.add(tax);

        when(taxRepository.findAll()).thenReturn(taxList);

        List<Tax> result = taxService.getAllTaxes();
        assertEquals(1, result.size());
        assertEquals(tax.getTaxName(), result.get(0).getTaxName());
    }

    @Test
    public void testGetTaxById() {
        when(taxRepository.findById(tax.getTaxId())).thenReturn(Optional.of(tax));

        Optional<Tax> result = taxService.getTaxById(tax.getTaxId());
        assertTrue(result.isPresent());
        assertEquals(tax.getTaxName(), result.get().getTaxName());
    }

    @Test
    public void testCreateTax() {
        when(taxRepository.save(any(Tax.class))).thenReturn(tax);

        Tax createdTax = taxService.createTax(tax);
        assertNotNull(createdTax);
        assertEquals(tax.getTaxName(), createdTax.getTaxName());
    }

    @Test
    public void testUpdateTax() {
        Tax updatedTax = new Tax();
        updatedTax.setTaxName("Updated VAT");
        updatedTax.setTaxRate(new BigDecimal("18.00"));
        updatedTax.setAppliedAmount(new BigDecimal("90.00"));

        when(taxRepository.findById(tax.getTaxId())).thenReturn(Optional.of(tax));
        when(taxRepository.save(any(Tax.class))).thenReturn(tax);

        Tax result = taxService.updateTax(tax.getTaxId(), updatedTax);
        assertEquals(tax.getTaxId(), result.getTaxId());
        assertEquals("Updated VAT", result.getTaxName());
    }

    @Test
    public void testDeleteTax() {
        doNothing().when(taxRepository).deleteById(tax.getTaxId());

        assertDoesNotThrow(() -> taxService.deleteTax(tax.getTaxId()));
        verify(taxRepository, times(1)).deleteById(tax.getTaxId());
    }
}
