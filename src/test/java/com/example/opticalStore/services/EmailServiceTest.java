package com.example.opticalStore.services;

import com.example.opticalStore.models.CartItem;
import com.example.opticalStore.models.Order;
import com.example.opticalStore.models.Product;
import com.example.opticalStore.models.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmailServiceTest
{
    @Mock
    private JavaMailSender mailSender;

    private EmailService emailService;

    public EmailServiceTest() {
        MockitoAnnotations.openMocks(this);
        emailService = new EmailService(mailSender);
    }

    @Test
    void sendMessage() {
        Product product = new Product("Product1",
                "Description1",
                10, "Sunglasses");
        User user = new User();
        CartItem cartItem = new CartItem(product, user, 2);
        List<CartItem> cartItems = new ArrayList<>();
        cartItems.add(cartItem);
        Order order = new Order(cartItems, "test@example.com", "address", "12:00-13:00");

        emailService.sendNotification(order);

        verify(mailSender, times(1)).send(any(SimpleMailMessage.class));
    }
}