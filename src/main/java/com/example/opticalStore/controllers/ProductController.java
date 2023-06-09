package com.example.opticalStore.controllers;

import com.example.opticalStore.models.Product;
import com.example.opticalStore.models.Role;
import com.example.opticalStore.models.User;
import com.example.opticalStore.services.ProductService;
import com.example.opticalStore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.opticalStore.models.Image;

import java.io.IOException;
import java.security.Principal;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/")
    public String products(@RequestParam(name = "category", required = false) String category,
                           Principal principal, Model model) {
        model.addAttribute("products", productService.listProducts(category));
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("category", category);
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model, Principal principal) {
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        model.addAttribute("product", productService.getProductById(id));
        return "product-info";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/product/new")
    public String formProduct() {
        return "product-form";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/product/create")
    public String createProduct(Product product, @RequestParam("file") MultipartFile file) throws IOException {
        productService.saveProduct(product, file);
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/product/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/product/list")
    public String listProduct(@RequestParam(name = "title", required = false) String title,
                              Model model) {
        model.addAttribute("products", productService.listProducts(title));
        return "product-list";
    }

    @GetMapping("/product/list/{product}")
    public String userEditForm(@PathVariable Product product, Model model) {
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping("/product/edit")
    public String userSave(@RequestParam String title,
                           @RequestParam String description,
                           @RequestParam int price,
                           @RequestParam String category,
                           @RequestParam(name="file", required = false) MultipartFile file,
                           @RequestParam("productId") Product product) throws IOException {
        productService.updateProduct(file, title, description, price, category, product);

        return "redirect:/product/list";
    }
}
