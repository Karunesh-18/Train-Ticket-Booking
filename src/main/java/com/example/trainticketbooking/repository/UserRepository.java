package com.example.trainticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trainticketbooking.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    java.util.Optional<User> findByEmail(String email);
}