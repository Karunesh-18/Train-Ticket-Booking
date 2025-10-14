package com.example.trainticketbooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trainticketbooking.entity.Train;

public interface TrainRepository extends JpaRepository<Train, Long>{
    
}
