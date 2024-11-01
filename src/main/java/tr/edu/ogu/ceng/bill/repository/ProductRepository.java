package tr.edu.ogu.ceng.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.bill.entity.Product;

public interface ProductRepository extends JpaRepository<Product,Long>{
        }
