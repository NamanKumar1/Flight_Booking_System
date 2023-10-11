package com.example.SearchFlights;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;

import com.example.SearchFlights.controller.FlightSearchController;
import com.example.SearchFlights.model.FlightDetails;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
class SearchFlightsApplicationTests {

	@InjectMocks
    private FlightSearchController flightSearchController;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testSearchFlights() {
        // Create sample data for testing
        String source = "Source";
        String destination = "Destination";
        LocalDate date = LocalDate.now();
        List<FlightDetails> flightDetailsList = createSampleFlightDetailsList();

        // Mock the behavior of the restTemplate's exchange method to return a ResponseEntity with the sample data
        when(restTemplate.exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                eq(new ParameterizedTypeReference<List<FlightDetails>>() {}),
                any(Object[].class)
        )).thenReturn(ResponseEntity.ok(flightDetailsList));

        // Test the searchFlights method
        ResponseEntity<List<FlightDetails>> response = flightSearchController.searchFlights(source, destination, date);

        // Assert that the response contains the sample data
        assertEquals(flightDetailsList, response.getBody());

        // Verify that restTemplate.exchange method was called with the correct parameters
        verify(restTemplate, times(1)).exchange(
                anyString(),
                eq(HttpMethod.GET),
                isNull(),
                eq(new ParameterizedTypeReference<List<FlightDetails>>() {}),
                any(Object[].class)
        );
    }

    private List<FlightDetails> createSampleFlightDetailsList() {
        // Create a list of FlightDetails for testing
        List<FlightDetails> flightList = new ArrayList<>();
        // Add sample FlightDetails to the list
        flightList.add(createSampleFlightDetails());
        return flightList;
    }

    private FlightDetails createSampleFlightDetails() {
        FlightDetails flightDetails = new FlightDetails();
        // Set properties for the sample FlightDetails
        return flightDetails;
    }
}
