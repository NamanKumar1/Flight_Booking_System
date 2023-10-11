package com.example.BookTicket.service;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BookTicket.model.BookingSeats;
import com.example.BookTicket.repository.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
    private BookingRepository bRepo;
	
	public BookingSeats findFlightByFlightNumberAndDate(String flightNumber, LocalDate date) {
        Optional<BookingSeats> optionalFlight = bRepo.findByFlightNumberAndDate(flightNumber, date);
        return optionalFlight.orElse(null);
    }

    public void addFlight(BookingSeats flight) {
        bRepo.save(flight);
    }

    public boolean bookTickets(String flightNumber, LocalDate date, int numberOfPassengers) {
        Optional<BookingSeats> optionalFlight = bRepo.findByFlightNumberAndDate(flightNumber, date);
        if (optionalFlight.isPresent()) {
            BookingSeats flight = optionalFlight.get();
            int availableSeats = flight.getTotalSeats();

            if (availableSeats >= numberOfPassengers) {
                // Sufficient seats are available, update the totalSeats
                flight.setTotalSeats(availableSeats - numberOfPassengers);
                bRepo.save(flight);
                return true;
            } else {
                // Insufficient seats available
                return false;
            }
        } else {
            // Flight does not exist for the provided date
            return false;
        }
    }

}
