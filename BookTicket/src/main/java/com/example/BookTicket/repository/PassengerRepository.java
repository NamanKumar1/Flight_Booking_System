package com.example.BookTicket.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.BookTicket.model.Passenger;

public interface PassengerRepository extends MongoRepository<Passenger, String> {

}
