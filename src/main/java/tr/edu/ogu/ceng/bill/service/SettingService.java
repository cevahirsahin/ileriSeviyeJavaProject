package tr.edu.ogu.ceng.bill.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tr.edu.ogu.ceng.bill.entity.Setting;
import tr.edu.ogu.ceng.bill.repository.SettingRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SettingService {
    private final SettingRepository settingRepository;

    public Setting getSetting(Long id) {
        return settingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setting not found with id: " + id));
    }

    public List<Setting> getAllSettings() {
        return settingRepository.findAll();
    }

    public Setting createSetting(Setting setting) {
        return settingRepository.save(setting);
    }

    public Setting updateSetting(Long id, Setting settingDetails) {
        Setting setting = settingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Setting not found with id: " + id));
        setting.setKey(settingDetails.getKey());
        setting.setValue(settingDetails.getValue());
        setting.setUpdatedBy(settingDetails.getUpdatedBy());
        return settingRepository.save(setting);
    }

    public void deleteSetting(Long id) {
        settingRepository.deleteById(id);
    }
}
