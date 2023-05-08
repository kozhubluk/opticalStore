package com.example.opticalStore.services;

import com.example.opticalStore.models.CartItem;
import com.example.opticalStore.models.Product;
import com.example.opticalStore.models.User;
import com.example.opticalStore.repositories.CartItemRepository;
import com.example.opticalStore.repositories.ProductRepository;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ShoppingCartServiceTest {
    @Mock
    private CartItemRepository cartItemRepositoryMock;

    @Mock
    private ProductRepository productRepositoryMock;

    private ShoppingCartService shoppingCartService;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        shoppingCartService = new ShoppingCartService(cartItemRepositoryMock, productRepositoryMock);
    }

    @Test
    public void testListCartItems() {
        User user = new User();
        List<CartItem> expectedCartItemList = new ArrayList<>();
        expectedCartItemList.add(new CartItem());
        expectedCartItemList.add(new CartItem());

        when(cartItemRepositoryMock.findByUser(user)).thenReturn(expectedCartItemList);

        List<CartItem> actualCartItemList = shoppingCartService.listCartItems(user);

        assertEquals(expectedCartItemList, actualCartItemList);
    }

    @Test
    public void testAddProduct() {
        // создание тестовых данных
        User user = new User();
        Product product = new Product();
        int quantity = 2;
        CartItem expectedCartItem = new CartItem();
        expectedCartItem.setQuantity(quantity);
        expectedCartItem.setProduct(product);
        expectedCartItem.setUser(user);

        when(cartItemRepositoryMock.findByUserAndProduct(user, product)).thenReturn(null);

        int actualAddedQuantity = shoppingCartService.addProduct(product, quantity, user);

        assertEquals(quantity, actualAddedQuantity);
        verify(cartItemRepositoryMock, times(1)).save(expectedCartItem);
    }

    @Test
    public void testDeleteItem() {
        // создание тестовых данных
        User user = new User();
        Product product = new Product();
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setUser(user);

        when(cartItemRepositoryMock.findByUserAndProduct(user, product)).thenReturn(cartItem);

        shoppingCartService.deleteItem(user, product);

        verify(cartItemRepositoryMock, times(1)).delete(cartItem);
    }

    @Test
    public void testSetNewQuantity() {
        // создание тестовых данных
        User user = new User();
        Product product = new Product();
        int newQuantity = 3;
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setUser(user);

        when(cartItemRepositoryMock.findByUserAndProduct(user, product)).thenReturn(cartItem);

        shoppingCartService.setNewQuantity(user, product, newQuantity);

        verify(cartItemRepositoryMock, times(1)).save(cartItem);
        assertEquals(newQuantity, cartItem.getQuantity());
    }

    @Test
    public void testClearCart() {
        User user = new User();
        List<CartItem> cartItemList = new ArrayList<>();
        cartItemList.add(new CartItem());
        cartItemList.add(new CartItem());

        when(cartItemRepositoryMock.findByUser(user)).thenReturn(cartItemList);

        shoppingCartService.clearCart(user);

        verify(cartItemRepositoryMock, times(2)).delete(any(CartItem.class));
    }

}