package tr.edu.ogu.ceng.bill.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class InvoiceItemDto {
    private Long itemId;
    private Long invoiceId;       // Invoice ilişkisinden sadece ID alıyoruz
    private Long productId;       // Product ilişkisinden sadece ID alıyoruz
    private String productName;
    private int quantity;
    private BigDecimal unitPrice;
    private BigDecimal totalPrice;
    private BigDecimal discountAmount;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Long version;
}