package com.example.opticalStore.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long Id;
    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "price")
    private int price;
    @Column(name = "category")
    private String category;

    public Product(){}

}
