package tr.edu.ogu.ceng.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.bill.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
        }
