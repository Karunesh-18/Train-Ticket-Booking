package com.example.trainticketbooking.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "TicketFlare — Book Tickets Easily");
        model.addAttribute("description", "TicketFlare - simple local demo page");
        return "home/index";
    }
}
