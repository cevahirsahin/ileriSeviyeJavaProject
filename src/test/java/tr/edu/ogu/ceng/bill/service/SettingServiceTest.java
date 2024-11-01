package tr.edu.ogu.ceng.bill.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tr.edu.ogu.ceng.bill.entity.Setting;
import tr.edu.ogu.ceng.bill.repository.SettingRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SettingServiceTest {

    @Mock
    private SettingRepository settingRepository;

    @InjectMocks
    private SettingService settingService;

    private Setting setting;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        setting = new Setting();
        setting.setId(1L);
        setting.setKey("exampleKey");
        setting.setValue("exampleValue");
        setting.setCreatedBy("admin");
        setting.setUpdatedBy("admin");
    }

    @Test
    public void testGetSetting() {
        when(settingRepository.findById(setting.getId())).thenReturn(Optional.of(setting));

        Setting result = settingService.getSetting(setting.getId());
        assertNotNull(result);
        assertEquals(setting.getKey(), result.getKey());
    }

    @Test
    public void testGetAllSettings() {
        List<Setting> settingsList = new ArrayList<>();
        settingsList.add(setting);

        when(settingRepository.findAll()).thenReturn(settingsList);

        List<Setting> result = settingService.getAllSettings();
        assertEquals(1, result.size());
        assertEquals(setting.getId(), result.get(0).getId());
    }

    @Test
    public void testCreateSetting() {
        when(settingRepository.save(any(Setting.class))).thenReturn(setting);

        Setting createdSetting = settingService.createSetting(setting);
        assertNotNull(createdSetting);
        assertEquals(setting.getId(), createdSetting.getId());
    }

    @Test
    public void testUpdateSetting() {
        Setting updatedSetting = new Setting();
        updatedSetting.setKey("updatedKey");
        updatedSetting.setValue("updatedValue");
        updatedSetting.setUpdatedBy("admin");

        when(settingRepository.findById(setting.getId())).thenReturn(Optional.of(setting));
        when(settingRepository.save(any(Setting.class))).thenReturn(setting);

        Setting result = settingService.updateSetting(setting.getId(), updatedSetting);
        assertEquals(setting.getId(), result.getId());
        assertEquals("updatedKey", result.getKey());
    }

    @Test
    public void testDeleteSetting() {
        doNothing().when(settingRepository).deleteById(setting.getId());

        assertDoesNotThrow(() -> settingService.deleteSetting(setting.getId()));
        verify(settingRepository, times(1)).deleteById(setting.getId());
    }
}
