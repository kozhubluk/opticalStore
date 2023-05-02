package com.example.opticalStore.controllers;

import com.example.opticalStore.models.Product;
import com.example.opticalStore.services.ProductService;
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

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title, Model model) {
        model.addAttribute("products", productService.listProducts(title));
        return "products";
    }

    @GetMapping("/product/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        return "product-info";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/product/new")
    public String formProduct(){
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
}
