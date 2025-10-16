package com.example.trainticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.trainticketbooking.entity.User;
import com.example.trainticketbooking.service.UserService;

@Controller
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/account")
    public String signInForm(Model model) {
        model.addAttribute("user", new User());
        return "account/signin";
    }

    @PostMapping("/account/signin")
    public String handleSignIn(@ModelAttribute User userForm, Model model) {
        var existingOpt = userService.getUserByEmail(userForm.getEmail());
        User user;
        String message;
        if (existingOpt.isPresent()) {
            user = existingOpt.get();
            message = "Logged in as " + user.getName() + " (" + user.getEmail() + ")";
        } else {
            user = new User();
            user.setName(userForm.getName());
            user.setEmail(userForm.getEmail());
            user = userService.createUser(user);
            message = "New user created. Logged in as " + user.getName() + " (" + user.getEmail() + ")";
        }

        model.addAttribute("message", message);
        model.addAttribute("user", user);
        return "account/signin";
    }
}