package com.example.opticalStore.repositories;

import com.example.opticalStore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static org.springframework.http.HttpHeaders.FROM;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByTitle(String title);
    List<Product> findAllByCategory(String category);

    Boolean existsByTitle(String title);
}
