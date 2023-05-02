package com.example.opticalStore.controllers;

import com.example.opticalStore.models.Role;
import com.example.opticalStore.models.User;
import com.example.opticalStore.repositories.UserRepository;
import com.example.opticalStore.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    private final UserService userService;

    @GetMapping
    public String admin(Model model) {
        return "admin";
    }

    @GetMapping("/users")
    public String userList(Model model) {
        model.addAttribute("users", userService.list());
        return "user-list";
    }

    @GetMapping("/users/{user}")
    public String userEditForm(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());
        return "user-edit";
    }

    @PostMapping("/users/edit")
    public String userSave(
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        userService.userEdit(username, form, user);

        return "redirect:/admin/users";
    }



}
