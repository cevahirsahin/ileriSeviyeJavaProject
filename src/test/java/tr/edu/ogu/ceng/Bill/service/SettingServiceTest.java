package tr.edu.ogu.ceng.Bill.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import tr.edu.ogu.ceng.Bill.entity.Setting;
import tr.edu.ogu.ceng.Bill.repository.SettingRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class SettingServiceIntegrationTest {

    private static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));

    @Autowired
    private SettingService settingService;

    @Autowired
    private SettingRepository settingRepository;

    @BeforeAll
    static void setUp() {
        postgresContainer.start();
        System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresContainer.getPassword());
    }

    @Test
    void testGetSetting() {
        // Arrange: Yeni bir Setting kaydı oluştur ve veritabanına kaydet
        Setting setting = new Setting();
        setting.setKey("language");
        setting.setValue("EN");
        setting.setCreatedBy("admin");
        setting.setCreatedAt(LocalDateTime.now());

        // Veritabanına kaydet
        Setting savedSetting = settingRepository.save(setting);

        // Act: SettingService üzerinden kaydı al
        Setting retrievedSetting = settingService.getSetting(savedSetting.getId());

        // Assert: Kayıt doğrulamalarını yap
        assertNotNull(retrievedSetting);
        assertEquals(savedSetting.getId(), retrievedSetting.getId());
        assertEquals("language", retrievedSetting.getKey());
        assertEquals("EN", retrievedSetting.getValue());
    }

    @Test
    void testGetSetting_NotFound() {
        // Act & Assert: Veritabanında bulunmayan bir ID ile getSetting çağrıldığında hata fırlatıldığını doğrula
        Long nonExistentId = 999L;
        Exception exception = assertThrows(RuntimeException.class, () -> {
            settingService.getSetting(nonExistentId);
        });

        String expectedMessage = "Setting not found with id: " + nonExistentId;
        assertEquals(expectedMessage, exception.getMessage());
    }
}
