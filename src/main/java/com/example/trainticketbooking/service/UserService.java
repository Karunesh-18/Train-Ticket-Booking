package com.example.trainticketbooking.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trainticketbooking.entity.User;
import com.example.trainticketbooking.exception.ResourceNotFoundException;
import com.example.trainticketbooking.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User CreateUser(User user) {
        return userRepository.save(user);
    }

    public User getUserById(Long Id) {
        return userRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("User not found with ID: " + Id));
    }

}