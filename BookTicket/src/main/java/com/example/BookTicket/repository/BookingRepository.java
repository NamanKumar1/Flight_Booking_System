package com.example.BookTicket.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.BookTicket.model.BookingSeats;

public interface BookingRepository extends MongoRepository<BookingSeats, String> {
	Optional<BookingSeats> findByFlightNumberAndDate(String flightNumber, LocalDate date);

}
