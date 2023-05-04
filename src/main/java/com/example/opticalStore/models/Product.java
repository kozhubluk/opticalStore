package com.example.opticalStore.models;

import jakarta.persistence.*;
import lombok.*;
import lombok.extern.apachecommons.CommonsLog;

import java.util.List;

@Entity
@Table(name = "products")
@Data
@AllArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "product")
    private Image image;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<CartItem> cartItemList;

    public Product() {
    }

    public Product(String title, String description, int price, String category) {
        this.title = title;
        this.description = description;
        this.price = price;
        this.category = category;
    }

    public void addImageToProduct(Image image) {
        image.setProduct(this);
        this.image = image;
    }

    public void updateImage(Image image) {

    }
}
