package tr.edu.ogu.ceng.Bill.service;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;
import tr.edu.ogu.ceng.Bill.entity.Category;
import tr.edu.ogu.ceng.Bill.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class CategoryServiceIntegrationTest {

    private static final PostgreSQLContainer<?> postgresContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:16-alpine"));

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private CategoryRepository categoryRepository;

    @BeforeAll
    static void setUp() {
        postgresContainer.start();
        System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", postgresContainer.getUsername());
        System.setProperty("spring.datasource.password", postgresContainer.getPassword());
    }

    @Test
    void testCreateCategory() {
        // Arrange
        Category category = new Category();
        category.setCategoryName("Electronics");
        category.setCreatedBy("admin");
        category.setCreatedAt(LocalDateTime.now());

        // Act
        Category savedCategory = categoryService.createCategory(category);

        // Assert
        assertNotNull(savedCategory);
        assertNotNull(savedCategory.getCategoryId());
        assertEquals("Electronics", savedCategory.getCategoryName());
    }

    @Test
    void testGetCategoryById() {
        // Arrange
        Category category = new Category();
        category.setCategoryName("Books");
        category.setCreatedBy("admin");
        category.setCreatedAt(LocalDateTime.now());
        Category savedCategory = categoryRepository.save(category);

        // Act
        Category retrievedCategory = categoryService.getCategoryById(savedCategory.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Assert
        assertNotNull(retrievedCategory);
        assertEquals(savedCategory.getCategoryId(), retrievedCategory.getCategoryId());
        assertEquals("Books", retrievedCategory.getCategoryName());
    }

    @Test
    void testUpdateCategory() {
        // Arrange
        Category category = new Category();
        category.setCategoryName("Clothing");
        category.setCreatedBy("admin");
        category.setCreatedAt(LocalDateTime.now());
        Category savedCategory = categoryRepository.save(category);

        // Prepare update details
        Category updatedCategoryDetails = new Category();
        updatedCategoryDetails.setCategoryName("Apparel");

        // Act
        Category updatedCategory = categoryService.updateCategory(savedCategory.getCategoryId(), updatedCategoryDetails);

        // Assert
        assertNotNull(updatedCategory);
        assertEquals("Apparel", updatedCategory.getCategoryName());
    }

    @Test
    void testDeleteCategory() {
        // Arrange
        Category category = new Category();
        category.setCategoryName("Toys");
        category.setCreatedBy("admin");
        category.setCreatedAt(LocalDateTime.now());
        Category savedCategory = categoryRepository.save(category);

        // Act
        categoryService.deleteCategory(savedCategory.getCategoryId());

        // Assert: Check that the category is deleted
        assertFalse(categoryRepository.existsById(savedCategory.getCategoryId()));
    }

    @Test
    void testGetAllCategories() {
        // Arrange
        Category category1 = new Category();
        category1.setCategoryName("Furniture");
        category1.setCreatedBy("admin");
        category1.setCreatedAt(LocalDateTime.now());
        categoryRepository.save(category1);

        Category category2 = new Category();
        category2.setCategoryName("Groceries");
        category2.setCreatedBy("admin");
        category2.setCreatedAt(LocalDateTime.now());
        categoryRepository.save(category2);

        // Act
        List<Category> categories = categoryService.getAllCategories();

        // Assert
        assertNotNull(categories);
        assertEquals(2, categories.size()); // Ensure both categories are returned
    }
}
