package com.example.AdminFlightDetails.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.AdminFlightDetails.model.FlightDetails;
import com.example.AdminFlightDetails.repository.FlightRepo;
import com.example.AdminFlightDetails.service.FlightService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/flights")
public class FlightController {
    @Autowired
    private FlightService flightService;
    
    @Autowired
    private FlightRepo repo;
    
    // Add the flight details
    
    @PostMapping("/add")
    public ResponseEntity<FlightDetails> addFlightDetail(@RequestBody FlightDetails flightDetail) {
        FlightDetails addedFlight = flightService.addFlightDetail(flightDetail);
        return ResponseEntity.ok(addedFlight);
    }
    
    //Get All Flights
    
    @GetMapping("/All")
    public List<FlightDetails> getAll() {
    	return flightService.getdata();
    }
    
    //GetFlightById
   @GetMapping("/get/{id}")
   public FlightDetails get(@PathVariable String id) {
	   FlightDetails flight =repo.findById(id).get();
	   return flight;
   }


    
    //Update Flight
    
   @PutMapping("/update/{id}")
   public FlightDetails updateFlightDetails(@PathVariable String id, @RequestBody FlightDetails updatedFlightDetails) {
       
           FlightDetails updatedFlight = flightService.updateFlightDetails(id, updatedFlightDetails);
           return updatedFlight;
        
   }
   
   

    
    

    //Search flight 
    @GetMapping("/search/{source}/{destination}/{date}")
    public ResponseEntity<List<FlightDetails>> searchFlights(
        @PathVariable String source,
        @PathVariable String destination,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
List<FlightDetails> flights = flightService.searchFlights(source, destination, date);
        
        if (flights.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(flights);
        }
    }
    
    
    }
