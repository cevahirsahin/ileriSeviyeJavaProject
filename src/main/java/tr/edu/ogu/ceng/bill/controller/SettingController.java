package tr.edu.ogu.ceng.bill.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tr.edu.ogu.ceng.bill.entity.Setting;
import tr.edu.ogu.ceng.bill.service.SettingService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/settings")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;

    @GetMapping("/{id}")
    public ResponseEntity<Setting> getSetting(@PathVariable Long id) {
        Setting setting = settingService.getSetting(id);
        return ResponseEntity.ok(setting);
    }

    @GetMapping
    public ResponseEntity<List<Setting>> getAllSettings() {
        List<Setting> settings = settingService.getAllSettings();
        return ResponseEntity.ok(settings);
    }

    @PostMapping
    public ResponseEntity<Setting> createSetting(@RequestBody Setting setting) {
        Setting createdSetting = settingService.createSetting(setting);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSetting);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Setting> updateSetting(@PathVariable Long id, @RequestBody Setting setting) {
        Setting updatedSetting = settingService.updateSetting(id, setting);
        return ResponseEntity.ok(updatedSetting);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSetting(@PathVariable Long id) {
        settingService.deleteSetting(id);
        return ResponseEntity.noContent().build();
    }
}

