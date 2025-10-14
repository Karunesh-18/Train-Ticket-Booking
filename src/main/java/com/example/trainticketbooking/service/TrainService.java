package com.example.trainticketbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trainticketbooking.entity.Train;
import com.example.trainticketbooking.exception.ResourceNotFoundException;
import com.example.trainticketbooking.repository.TrainRepository;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    public Train CreateTrain(Train train) {
        return trainRepository.save(train);
    }

    public Train getTrainById(Long Id) {
        return trainRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Train not found with ID: " + Id));
    }

    
}
