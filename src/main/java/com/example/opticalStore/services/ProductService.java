package com.example.opticalStore.services;

import com.example.opticalStore.models.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import com.example.opticalStore.repositories.ProductRepository;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public List<Product> listProducts(String title) {
        if (title != null) return productRepository.findByTitle(title);
        return productRepository.findAll();
    }
    public void saveProduct(Product product) {
        log.info("saving new: {}", product);
        productRepository.save(product);
    }
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

}
