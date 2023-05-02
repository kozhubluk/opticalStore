package com.example.opticalStore.controllers;

import com.example.opticalStore.models.CartItem;
import com.example.opticalStore.models.User;
import com.example.opticalStore.services.ShoppingCartService;
import com.example.opticalStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService cartService;
    @Autowired
    private UserService userService;

    @GetMapping("/cart")
    public String showShoppingCart(Model model,
                                   Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        List<CartItem> cartItems = cartService.listCartItems(user);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("pageTitle", "ShoppingCart");

        return "/shopping_cart";
    }
}
