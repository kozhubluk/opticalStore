package com.example.opticalStore.services;

import com.example.opticalStore.models.CartItem;
import com.example.opticalStore.models.Product;
import com.example.opticalStore.models.User;
import com.example.opticalStore.repositories.CartItemRepository;
import com.example.opticalStore.repositories.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartService {
    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    public ShoppingCartService(CartItemRepository cartItemRepository,
                               ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public List<CartItem> listCartItems(User user) {
        return cartItemRepository.findByUser(user);
    }

    public int addProduct(Product productId, int quantity, User user) {
        int addedQuantity = quantity;
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, productId);
        if (cartItem != null) {
            addedQuantity = cartItem.getQuantity() + quantity;
            cartItem.setQuantity(addedQuantity);
        } else {
            cartItem = new CartItem();
            cartItem.setQuantity(quantity);
        }
        cartItem.setProduct(productId);
        cartItem.setUser(user);
        cartItemRepository.save(cartItem);
        return addedQuantity;
    }

    public void deleteItem(User user,  Product product) {
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
        cartItemRepository.delete(cartItem);
    }

    public void setNewQuantity(User user, Product product, int quantity) {
        CartItem cartItem = cartItemRepository.findByUserAndProduct(user, product);
        cartItem.setQuantity(quantity);
        cartItemRepository.save(cartItem);
    }

    public void clearCart(User user) {

        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        for (CartItem cartItem : cartItems) {
            cartItemRepository.delete(cartItem);
        }

      /*  cartItemRepository.deleteAllByUser(user);*/
    }

}
