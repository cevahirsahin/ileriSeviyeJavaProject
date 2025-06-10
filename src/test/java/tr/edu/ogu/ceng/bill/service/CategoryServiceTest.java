package tr.edu.ogu.ceng.bill.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import tr.edu.ogu.ceng.bill.controller.CategoryController;
import tr.edu.ogu.ceng.bill.entity.Category;
import tr.edu.ogu.ceng.bill.repository.CategoryRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ActiveProfiles("test") // Test profili
@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

    private static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>("postgres:16-alpine")
                    .withDatabaseName("testdb")
                    .withUsername("user")
                    .withPassword("password");

    static {
        postgresContainer.start(); // PostgreSQL konteyneri başlat
    }

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryController categoryController;



    private Category category;

    @BeforeEach
    public void setUp() {
        categoryRepository.deleteAll(); // Mevcut verileri temizle
        category = new Category();
        category.setCategoryName("Test Category");
        categoryRepository.save(category); // Veritabanına kaydet
    }

    @Test
    public void testGetAllCategories() {
        ResponseEntity<List<Category>> response = categoryController.getAllCategories();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1, response.getBody().size());
    }

    @Test
    public void testGetCategoryById() {
        ResponseEntity<Category> response = categoryController.getCategoryById(category.getCategoryId());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Test Category", response.getBody().getCategoryName());
    }

    @Test
    public void testCreateCategory() {
        Category newCategory = new Category();
        newCategory.setCategoryName("New Category");

        ResponseEntity<Category> response = categoryController.createCategory(newCategory);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("New Category", response.getBody().getCategoryName());
    }

    @Test
    public void testUpdateCategory() {
        Category existingCategory = categoryRepository.findById(category.getCategoryId()).orElseThrow();
        existingCategory.setCategoryName("Updated Category");
        ResponseEntity<Category> response = categoryController.updateCategory(existingCategory.getCategoryId(), existingCategory);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Updated Category", response.getBody().getCategoryName());
    }

    @Test
    public void testDeleteCategory() {
        ResponseEntity<Void> response = categoryController.deleteCategory(category.getCategoryId());
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}