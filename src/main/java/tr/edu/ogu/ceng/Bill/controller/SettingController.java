package tr.edu.ogu.ceng.Bill.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tr.edu.ogu.ceng.Bill.entity.Setting;
import tr.edu.ogu.ceng.Bill.service.SettingService;

@RestController
@RequestMapping("/api/v1/Setting")
@RequiredArgsConstructor
public class SettingController {

    private final SettingService settingService;

    @GetMapping("/{id}")
    public Setting getSetting(@PathVariable Long id) {
        return settingService.getSetting(id);
    }

}

