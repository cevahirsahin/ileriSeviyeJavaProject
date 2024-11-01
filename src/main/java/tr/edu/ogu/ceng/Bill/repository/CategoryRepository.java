package tr.edu.ogu.ceng.Bill.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Bill.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
