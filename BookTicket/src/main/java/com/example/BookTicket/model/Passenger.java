package com.example.BookTicket.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "passengers")
public class Passenger {
    @Id
    private String id; // MongoDB document ID
    private String name;
    private String email;
    private String flightNumber;
    private LocalDate date;
    // Add other passenger details as needed

    public Passenger() {
        // Default constructor required by Spring Data MongoDB
    }

    public Passenger(String name, String email, String flightNumber,LocalDate date) {
        this.name = name;
        this.email = email;
        this.flightNumber=flightNumber;
        this.date=date;
        // Initialize other passenger details here
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	} 

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
    
}

