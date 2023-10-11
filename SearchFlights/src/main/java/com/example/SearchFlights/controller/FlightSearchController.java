package com.example.SearchFlights.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.SearchFlights.model.FlightDetails;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/flights")
public class FlightSearchController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	
	@GetMapping("/search/{source}/{destination}/{date}")
    public ResponseEntity<List<FlightDetails>> searchFlights(
        @PathVariable String source,
        @PathVariable String destination,
        @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date
    ) {
	

        ResponseEntity<List<FlightDetails>> response = restTemplate.exchange(
        		"http://localhost:8081/flights/search/{source}/{destination}/{date}", 
        		HttpMethod.GET,
    	        null, 
    	        new ParameterizedTypeReference<List<FlightDetails>>() {},
    	        source, destination, date
        );

        return response;
    }

}
