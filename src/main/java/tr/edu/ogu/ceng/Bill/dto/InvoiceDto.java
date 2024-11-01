package tr.edu.ogu.ceng.Bill.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class InvoiceDto {
    private Long invoiceId;
    private Long orderId;
    private Long customerId;  // Customer yerine sadece customerId taşıyoruz
    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;
    private BigDecimal totalAmount;
    private BigDecimal taxAmount;
    private BigDecimal discount;
    private String status;
    private String paymentMethod;
    private String currency;
    private String notes;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Long version;
}