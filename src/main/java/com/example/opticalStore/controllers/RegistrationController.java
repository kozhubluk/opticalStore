package com.example.opticalStore.controllers;

import com.example.opticalStore.models.Role;
import com.example.opticalStore.models.User;
import com.example.opticalStore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;

@Controller
public class RegistrationController {
    @Autowired
    private UserRepository userRepository;
    @GetMapping("/registration")
    public String registration(){
        return "registration";
    }

    @PostMapping("/registration")
    public String addUser(User user, Model model) {
        User userFromDb = userRepository.findByUsername(user.getUsername());
        if (userFromDb != null) {
            model.addAttribute("message", "User exist!");
            return "/registration";
        }
        String encodedPassword = new BCryptPasswordEncoder().encode(user.getPassword());
        user.setPassword(encodedPassword);
        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }


}
