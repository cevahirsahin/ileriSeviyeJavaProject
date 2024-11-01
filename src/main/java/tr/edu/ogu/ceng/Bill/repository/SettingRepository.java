package tr.edu.ogu.ceng.Bill.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import tr.edu.ogu.ceng.Bill.entity.Setting;

public interface SettingRepository extends JpaRepository<Setting,Long> {

}
