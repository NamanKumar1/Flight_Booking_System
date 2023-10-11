package com.example.BookTicket.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BookTicket.model.BookingSeats;
import com.example.BookTicket.model.Passenger;
import com.example.BookTicket.repository.PassengerRepository;
import com.example.BookTicket.service.BookingService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/flights")
public class BookingController {
	
    @Autowired
    private BookingService flightService;
    
    @Autowired
    private PassengerRepository passengerRepository;
    

    
    @PostMapping("/book/{flightNumber}/{date}")
    public ResponseEntity<String> bookFlight(
        @PathVariable String flightNumber,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
        @RequestBody List<Passenger> passengers // Passengers are now provided as a list of Passenger objects
    ) {
        // Check if a flight with the given flight number and date exists in the database
        BookingSeats existingFlight = flightService.findFlightByFlightNumberAndDate(flightNumber, date);

        if (existingFlight == null) {
            // Flight for the provided date doesn't exist, create a new entry
            BookingSeats newFlight = new BookingSeats(flightNumber, date, 150); // Assuming totalSeats is initially 150
            flightService.addFlight(newFlight);
        }
        

        // Calculate the number of passengers based on the list size
        int numberOfPassengers = passengers.size();

        // Attempt to book the tickets
        boolean bookingSuccessful = flightService.bookTickets(flightNumber, date, numberOfPassengers);

        if (bookingSuccessful) {
            // Set the flight number and date in each passenger object
            passengers.forEach(passenger -> {
                passenger.setFlightNumber(flightNumber);
                passenger.setDate(date);
            });

            // Save passenger details to the database
            passengerRepository.saveAll(passengers);

            return ResponseEntity.ok("Booking successful!");
        } else {
            return ResponseEntity.badRequest().body("Booking failed. Insufficient seats available.");
        }

}

}
