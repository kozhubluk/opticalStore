package com.example.opticalStore.services;

import com.example.opticalStore.models.Image;
import com.example.opticalStore.models.Product;
import com.example.opticalStore.repositories.ImageRepository;
import com.example.opticalStore.repositories.ProductRepository;
import com.example.opticalStore.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ImageRepository imageRepository;
    @Mock
    private UserRepository userRepository;
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productService = new ProductService(productRepository, userRepository, imageRepository);
    }

    @Test
    void getProductById() {
        Long id = 1L;
        Product product = new Product("Product1",
                "Description1",
                10, "Sunglasses");
        Optional<Product> optionalProduct = Optional.of(product);
        when(productRepository.findById(id)).thenReturn(optionalProduct);

        Product result = productService.getProductById(id);

        assertEquals("Product1", result.getTitle());
        assertEquals("Description1", result.getDescription());
        assertEquals("Sunglasses", result.getCategory());
        assertEquals(10, result.getPrice());
    }

    @Test
    void listProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Product1", "Description1", 10,"Computer glasses"));
        productList.add(new Product("Product2", "Description2", 20,"Lenses"));
        when(productRepository.findAll()).thenReturn(productList);

        List<Product> result = productService.listProducts("");

        assertEquals(2, result.size());
        assertEquals("Product1", result.get(0).getTitle());
        assertEquals("Computer glasses", result.get(0).getCategory());
        assertEquals("Description2", result.get(1).getDescription());
        assertEquals(20.0, result.get(1).getPrice());
    }

    @Test
    void deleteProduct() {
        Long id = 1L;

        productService.deleteProduct(id);

        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void saveProduct() throws IOException {
        // given
        Product product = new Product("Product4", "Description4", 30,"Sunglasses");
        MockMultipartFile file = new MockMultipartFile("image", "image.jpg", "image/jpeg", "test image".getBytes());
        when(productRepository.existsByTitle(product.getTitle())).thenReturn(false);

        productService.saveProduct(product, file);

        verify(productRepository, times(1)).save(any(Product.class));
    }

    @Test
    void updateProductTest() throws IOException {
        MockMultipartFile file = new MockMultipartFile("image", "image.jpg", "image/jpeg", "test image".getBytes());
        Product product = new Product("Product", "Description", 30,"Sunglasses");
        String title = "New Title";
        String description = "New Description";
        int price = 100;
        String category = "Eyeglasses";

        productService.saveProduct(product, file);
        productService.updateProduct(null, title, description, price, category, product);

        verify(productRepository, times(2)).save(product);
    }
}