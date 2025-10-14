package com.example.trainticketbooking.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	private String trainName;
	private String trainNumber;
	private String source;
	private String destination;
	private String dateOfJourney;
	private String timeOfJourney;
	private String coach;
	private String seatNumber;
	private String passengerName;
	private Integer price;
	private Integer PnrNumber;
	private String status;
}
