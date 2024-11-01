package tr.edu.ogu.ceng.bill.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tr.edu.ogu.ceng.bill.entity.Product;
import tr.edu.ogu.ceng.bill.repository.ProductRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        product = new Product();
        product.setProductId(1L);
        product.setName("Sample Product");
        product.setDescription("This is a sample product.");
        product.setPrice(new BigDecimal("99.99"));
        product.setStockQuantity(50);
        product.setSku("SP123");
        product.setWeight(new BigDecimal("1.5"));
        product.setDimensions("10x10x10");
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
    }

    @Test
    public void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(product);

        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.getAllProducts();
        assertEquals(1, result.size());
        assertEquals(product.getName(), result.get(0).getName());
    }

    @Test
    public void testGetProductById() {
        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));

        Optional<Product> result = productService.getProductById(product.getProductId());
        assertTrue(result.isPresent());
        assertEquals(product.getName(), result.get().getName());
    }

    @Test
    public void testCreateProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product createdProduct = productService.createProduct(product);
        assertNotNull(createdProduct);
        assertEquals(product.getName(), createdProduct.getName());
    }

    @Test
    public void testUpdateProduct() {
        Product updatedProduct = new Product();
        updatedProduct.setName("Updated Product");
        updatedProduct.setDescription("This is an updated product.");
        updatedProduct.setPrice(new BigDecimal("89.99"));
        updatedProduct.setStockQuantity(30);
        updatedProduct.setSku("UP123");
        updatedProduct.setWeight(new BigDecimal("1.2"));
        updatedProduct.setDimensions("8x8x8");

        when(productRepository.findById(product.getProductId())).thenReturn(Optional.of(product));
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product result = productService.updateProduct(product.getProductId(), updatedProduct);
        assertEquals(product.getProductId(), result.getProductId());
        assertEquals("Updated Product", result.getName());
    }

    @Test
    public void testDeleteProduct() {
        doNothing().when(productRepository).deleteById(product.getProductId());

        assertDoesNotThrow(() -> productService.deleteProduct(product.getProductId()));
        verify(productRepository, times(1)).deleteById(product.getProductId());
    }
}
