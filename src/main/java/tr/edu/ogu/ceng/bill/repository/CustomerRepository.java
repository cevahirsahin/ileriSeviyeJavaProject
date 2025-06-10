package tr.edu.ogu.ceng.bill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tr.edu.ogu.ceng.bill.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Long>{
        @Query(value = "SELECT u FROM Customer u WHERE u.firstName= :firstName",nativeQuery = true)

        Customer getByfirstName(@Param("firstName") String firstName);
        }
