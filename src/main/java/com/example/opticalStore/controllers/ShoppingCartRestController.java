package com.example.opticalStore.controllers;

import com.example.opticalStore.models.Product;
import com.example.opticalStore.models.User;
import com.example.opticalStore.services.ShoppingCartService;
import com.example.opticalStore.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@AllArgsConstructor
public class ShoppingCartRestController {
    @Autowired
    private final ShoppingCartService shoppingCartService;
    @Autowired
    private final UserService userService;

    @PostMapping("/cart/add/")
    public void addProductToCart(@RequestParam("productId") Product productId,
                                   @RequestParam("quantity") int quantity,
                                   Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        shoppingCartService.addProduct(productId, quantity, user);
    }


    @PostMapping("/cart/delete/{id}")
    public String deleteProductFromCart(@PathVariable Long id) {
        shoppingCartService.deleteItem(id);
        return "redirect:/cart";
    }
}
