package tr.edu.ogu.ceng.bill.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.bill.entity.Setting;

public interface SettingRepository extends JpaRepository<Setting,Long> {

}
