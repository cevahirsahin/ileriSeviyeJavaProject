package tr.edu.ogu.ceng.Bill.Entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Entity
@Table(name = "category")
@Data
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private UUID categoryId;

    @Column(name = "category_name", nullable = false, length=255)
    private String categoryName;

    @ManyToOne
    @JoinColumn(name = "parent_category_id")
    private Category parentCategory;
}