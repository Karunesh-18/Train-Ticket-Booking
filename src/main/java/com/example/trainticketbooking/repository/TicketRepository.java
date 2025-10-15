package com.example.trainticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trainticketbooking.entity.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}