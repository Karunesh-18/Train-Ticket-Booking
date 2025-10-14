package com.example.trainticketbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trainticketbooking.entity.Ticket;
import com.example.trainticketbooking.exception.ResourceNotFoundException;
import com.example.trainticketbooking.repository.TicketRepository;

@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    public Ticket CreateTicket(Ticket ticket) {
        return ticketRepository.save(ticket);
    }

    public Ticket getTrainById(Long Id) {
        return ticketRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Ticket not found with ID: " + Id));
    }

    public int getDiscountedPrice(Long Id) {
        Ticket ticket = ticketRepository.findById(Id).orElseThrow(() -> new RuntimeException("Ticket + Id+ not found"));
        int originalPrice = ticket.getPrice();
        int discount = originalPrice * 40 / 100;
        return originalPrice - discount;
    }


    
}
