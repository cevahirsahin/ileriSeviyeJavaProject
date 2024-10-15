package tr.edu.ogu.ceng.Bill.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "tax")
@Data
@NoArgsConstructor
public class Tax {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID taxId;

    @Column(name = "tax_name", nullable = false, length = 255)
    private String taxName;

    @Column(name = "tax_rate", nullable = false, precision = 5, scale = 2)
    private BigDecimal taxRate;

    @ManyToOne
    @JoinColumn(name = "invoice_id", nullable = false)
    private Invoice invoice;

    @Column(name = "applied_amount")
    private BigDecimal appliedAmount;
}