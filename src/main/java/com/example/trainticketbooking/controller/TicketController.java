package com.example.trainticketbooking.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trainticketbooking.entity.Ticket;
import com.example.trainticketbooking.service.TicketService;

@RestController
@RequestMapping("api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket){
        return ResponseEntity.ok(ticketService.CreateTicket(ticket));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTrainById(@PathVariable Long id){
        return ResponseEntity.ok(ticketService.getTrainById(id));
    }

    @GetMapping("/discount/{ticketId}")
    public ResponseEntity<Integer> getDiscountedPrice(@PathVariable Long ticketId) {
        int discountedPrice = ticketService.getDiscountedPrice(ticketId);
        return ResponseEntity.ok(discountedPrice);
    }
}
