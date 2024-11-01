package tr.edu.ogu.ceng.Bill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.edu.ogu.ceng.Bill.entity.Setting;
import tr.edu.ogu.ceng.Bill.repository.SettingRepository;


@Service
@RequiredArgsConstructor
public class SettingService {
    private final SettingRepository settingRepository;

    public Setting getSetting(Long id) {
        return settingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setting not found with id: " + id));
    }
}
