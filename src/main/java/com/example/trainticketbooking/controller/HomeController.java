package com.example.trainticketbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to serve the application home page which uses the
 * template at src/main/resources/templates/home/index.html
 */
@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        // Provide a couple of attributes the template might use.
        model.addAttribute("title", "TicketFlare — Book Tickets Easily");
        model.addAttribute("description", "TicketFlare - simple local demo page");
        // Return the Thymeleaf template located at templates/home/index.html
        return "home/index";
    }
}
