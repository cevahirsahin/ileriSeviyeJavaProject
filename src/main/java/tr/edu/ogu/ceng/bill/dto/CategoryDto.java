package tr.edu.ogu.ceng.bill.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class CategoryDto {
    private Long categoryId;
    private String categoryName;
    private Long parentCategoryId; // Parent Category'nin sadece ID'sini taşıyoruz
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
    private LocalDateTime updatedAt;
    private String deletedBy;
    private LocalDateTime deletedAt;
    private Long version;
}