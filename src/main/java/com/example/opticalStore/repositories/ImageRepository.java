package com.example.opticalStore.repositories;

import com.example.opticalStore.models.Image;
import com.example.opticalStore.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    Image findImageByProduct(Product product);
}
