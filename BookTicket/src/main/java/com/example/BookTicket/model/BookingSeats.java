package com.example.BookTicket.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Bookings")
public class BookingSeats {
	 @Id
	    private String id; // MongoDB document ID
	    private String flightNumber;
	    private LocalDate date;
	    private int totalSeats;

	    public BookingSeats() {
	        // Default constructor required by Spring Data MongoDB
	    }

	    public BookingSeats(String flightNumber, LocalDate date, int totalSeats) {
	        this.flightNumber = flightNumber;
	        this.date = date;
	        this.totalSeats = totalSeats;
	    }

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
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

		public int getTotalSeats() {
			return totalSeats;
		}

		public void setTotalSeats(int totalSeats) {
			this.totalSeats = totalSeats;
		}
	    

}
