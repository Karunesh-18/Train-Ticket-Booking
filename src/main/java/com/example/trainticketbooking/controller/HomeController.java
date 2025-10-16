package com.example.trainticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.trainticketbooking.service.TicketService;
import com.example.trainticketbooking.service.TrainService;
import com.example.trainticketbooking.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private TrainService trainService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("title", "TicketFlare — Book Tickets Easily");
        model.addAttribute("description", "TicketFlare - simple local demo page");

        // Add statistics for the home page
        model.addAttribute("totalTrains", trainService.getAllTrains().size());
        model.addAttribute("totalTickets", ticketService.getAllTickets().size());
        model.addAttribute("totalUsers", userService.getAllUsers().size());

        // Add recent trains (limit to 5 for display)
        var allTrains = trainService.getAllTrains();
        var recentTrains = allTrains.size() > 5 ? allTrains.subList(0, 5) : allTrains;
        model.addAttribute("recentTrains", recentTrains);

        return "home/index";
    }

    @PostMapping("/search")
    public String searchTrains(@RequestParam("from") String from,
                              @RequestParam("to") String to,
                              @RequestParam(value = "date", required = false) String date,
                              Model model) {
        // Search for trains based on source and destination
        var searchResults = trainService.searchTrains(from, to);

        model.addAttribute("trains", searchResults);
        model.addAttribute("searchFrom", from);
        model.addAttribute("searchTo", to);
        model.addAttribute("searchDate", date);
        model.addAttribute("isSearchResult", true);

        return "trains/list";
    }
}
