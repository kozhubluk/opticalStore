package com.example.opticalStore.repositories;

import com.example.opticalStore.models.CartItem;
import com.example.opticalStore.models.Product;
import com.example.opticalStore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    public List<CartItem> findByUser(User user);
    public CartItem findByUserAndProduct(User user, Product product);
    public void deleteAllByUser(User user);

}
