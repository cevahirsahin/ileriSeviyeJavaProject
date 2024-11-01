package tr.edu.ogu.ceng.Bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Bill.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
        }
