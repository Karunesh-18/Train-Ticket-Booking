package com.example.trainticketbooking.entity;

import jakarta.persistence.Entity;     //Used to mark a class as an entity bean
import jakarta.persistence.GeneratedValue; //Used to generate unique IDs for entities
import jakarta.persistence.GenerationType; //Used to specify the generation strategy for IDs
import jakarta.persistence.Id; //Used to mark a field as the primary key
import lombok.Data; //Used to automatically generate getters, setters, equals, hashCode, and toString methods

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    
    private Long Id;
    private String firstName;
    private String lastName;
    private String email;
}
