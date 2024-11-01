package tr.edu.ogu.ceng.bill.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.bill.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
