package tr.edu.ogu.ceng.Bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Bill.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
        }
