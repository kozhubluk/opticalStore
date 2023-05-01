package com.example.opticalStore.repositories;

import com.example.opticalStore.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
