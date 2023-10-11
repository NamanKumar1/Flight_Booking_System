package com.example.AdminFlightDetails;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.AdminFlightDetails.model.FlightDetails;
import com.example.AdminFlightDetails.repository.FlightRepo;
import com.example.AdminFlightDetails.service.FlightService;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@SpringBootTest
class AdminFlightDetailsApplicationTests {

	@InjectMocks
    private FlightService flightService;

    @Mock
    private FlightRepo flightRepo;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddFlightDetail() {
        // Create a sample FlightDetails object for testing
        FlightDetails flightDetails = new FlightDetails();

        // Mock the save method of the flightRepo
        Mockito.when(flightRepo.save(flightDetails)).thenReturn(flightDetails);

        FlightDetails savedFlight = flightService.addFlightDetail(flightDetails);

        // Assert that the saved flight matches the input flight
        assertEquals(flightDetails, savedFlight);
    }

    @Test
    public void testGetdata() {
        // Create a sample list of FlightDetails for testing
        List<FlightDetails> flightList = createSampleFlightDetailsList();

        // Mock the behavior of the flightRepo to return the sample list
        Mockito.when(flightRepo.findAll()).thenReturn(flightList);

        // Test getdata
        List<FlightDetails> allFlights = flightService.getdata();

        // Assert that the result is not null
        assertNotNull(allFlights);
        // You can add more specific assertions based on your business logic.
    }

    // Helper methods to create sample data for testing
    private FlightDetails createSampleFlightDetails() {
        FlightDetails flightDetails = new FlightDetails();
        flightDetails.setId("1");
        // Set other properties
        return flightDetails;
    }

    private List<FlightDetails> createSampleFlightDetailsList() {
        // Create a list of FlightDetails for testing
        // Add sample FlightDetails to the list
        List<FlightDetails> flightList = new ArrayList<>();
        flightList.add(createSampleFlightDetails());
        return flightList;
    }

}
