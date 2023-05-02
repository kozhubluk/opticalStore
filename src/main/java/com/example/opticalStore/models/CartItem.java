package com.example.opticalStore.models;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "cart_item")
@Data
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    private User user;
    private int quantity;

}
