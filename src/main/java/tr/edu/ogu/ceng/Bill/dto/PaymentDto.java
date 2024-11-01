package tr.edu.ogu.ceng.Bill.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class PaymentDto {
    private Long paymentId;
    private Long invoiceId;       // Invoice ilişkisinden sadece ID alıyoruz
    private LocalDateTime paymentDate;
    private BigDecimal paymentAmount;
    private String paymentMethod;
    private String paymentStatus;
    private String transactionId;
    private String paymentGateway;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Long version;
}