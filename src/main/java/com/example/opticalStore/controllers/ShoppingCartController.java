package com.example.opticalStore.controllers;

import com.example.opticalStore.models.CartItem;
import com.example.opticalStore.models.Order;
import com.example.opticalStore.models.Product;
import com.example.opticalStore.models.User;
import com.example.opticalStore.services.EmailService;
import com.example.opticalStore.services.ShoppingCartService;
import com.example.opticalStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.List;

@Controller
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private UserService userService;
    @Autowired
    private EmailService emailService;

    @GetMapping("/cart")
    public String showShoppingCart(Model model,
                                   Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        List<CartItem> cartItems = shoppingCartService.listCartItems(user);
        model.addAttribute("cartItems", cartItems);
        model.addAttribute("user", user);

        return "/shopping_cart";
    }

    @PostMapping("/cart/add/")
    public String addProductToCart(@RequestParam("productId") Product productId,
                                   @RequestParam("quantity") int quantity,
                                   Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        shoppingCartService.addProduct(productId, quantity, user);
        return "redirect:/";
    }

    @PostMapping("/cart/set-quantity")
    public String setItemQuantity(@RequestParam("productId") Product productId,
                                  @RequestParam("quantity") int quantity,
                                  Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        shoppingCartService.setNewQuantity(user, productId, quantity);
        return "redirect:/cart";
    }

    @PostMapping("/cart/delete/{product}")
    public String deleteProductFromCart(@PathVariable Product product,
                                        Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        shoppingCartService.deleteItem(user, product);

        return "redirect:/cart";
    }

    @PostMapping("/cart/clear")
    public String clearCart(Principal principal) {
        User user = userService.getUserByPrincipal(principal);
        shoppingCartService.clearCart(user);
        return "redirect:/";
    }

    @PostMapping("cart/order")
    public String createOrder(Principal principal, @RequestParam(name="email") String email,
                              @RequestParam(name="address") String address,
                              @RequestParam(name="time") String time) {
        User user = userService.getUserByPrincipal(principal);
        List<CartItem> items = shoppingCartService.listCartItems(user);
        System.out.println(email+address+ time);
        Order order = new Order(items, email, address, time);
        emailService.sendNotification(order);
        shoppingCartService.clearCart(user);
        return "redirect:/cart";
    }
}
