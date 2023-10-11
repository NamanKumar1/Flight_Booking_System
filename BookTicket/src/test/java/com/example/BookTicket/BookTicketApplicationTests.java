package com.example.BookTicket;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.BookTicket.model.BookingSeats;
import com.example.BookTicket.repository.BookingRepository;
import com.example.BookTicket.service.BookingService;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.time.LocalDate;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BookTicketApplicationTests {

	@InjectMocks
    private BookingService bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindFlightByFlightNumberAndDate() {
        // Create a sample BookingSeats object for testing
        BookingSeats bookingSeats = new BookingSeats();
        bookingSeats.setFlightNumber("ABC123");
        bookingSeats.setDate(LocalDate.now());

        // Mock the behavior of the bookingRepository to return the sample BookingSeats object
        Mockito.when(bookingRepository.findByFlightNumberAndDate("ABC123", LocalDate.now())).thenReturn(Optional.of(bookingSeats));

        // Test the findFlightByFlightNumberAndDate method
        BookingSeats foundBooking = bookingService.findFlightByFlightNumberAndDate("ABC123", LocalDate.now());

        // Assert that the result is not null
        assertEquals(bookingSeats, foundBooking);
    }

    @Test
    public void testAddFlight() {
        // Create a sample BookingSeats object for testing
        BookingSeats bookingSeats = new BookingSeats();

        // Test the addFlight method
        bookingService.addFlight(bookingSeats);

        // Verify that the save method of the bookingRepository was called
        Mockito.verify(bookingRepository).save(bookingSeats);
    }

    @Test
    public void testBookTicketsSufficientSeatsAvailable() {
        // Create a sample BookingSeats object for testing
        BookingSeats bookingSeats = new BookingSeats();
        bookingSeats.setFlightNumber("XYZ789");
        bookingSeats.setDate(LocalDate.now());
        bookingSeats.setTotalSeats(10);

        // Mock the behavior of the bookingRepository to return the sample BookingSeats object
        Mockito.when(bookingRepository.findByFlightNumberAndDate("XYZ789", LocalDate.now())).thenReturn(Optional.of(bookingSeats));

        // Test the bookTickets method with a sufficient number of passengers
        boolean result = bookingService.bookTickets("XYZ789", LocalDate.now(), 5);

        // Assert that booking was successful
        assertTrue(result);
        // Verify that the save method of the bookingRepository was called to update the totalSeats
        Mockito.verify(bookingRepository).save(bookingSeats);
    }

    @Test
    public void testBookTicketsInsufficientSeatsAvailable() {
        // Create a sample BookingSeats object for testing
        BookingSeats bookingSeats = new BookingSeats();
        bookingSeats.setFlightNumber("PQR456");
        bookingSeats.setDate(LocalDate.now());
        bookingSeats.setTotalSeats(5);

        // Mock the behavior of the bookingRepository to return the sample BookingSeats object
        Mockito.when(bookingRepository.findByFlightNumberAndDate("PQR456", LocalDate.now())).thenReturn(Optional.of(bookingSeats));

        // Test the bookTickets method with an insufficient number of passengers
        boolean result = bookingService.bookTickets("PQR456", LocalDate.now(), 10);

        // Assert that booking was not successful
        assertFalse(result);
        // Verify that the totalSeats were not updated
        Mockito.verify(bookingRepository, Mockito.never()).save(bookingSeats);
    }

    @Test
    public void testBookTicketsFlightDoesNotExist() {
        // Mock the behavior of the bookingRepository to return an empty Optional (flight does not exist)
        Mockito.when(bookingRepository.findByFlightNumberAndDate("LMN123", LocalDate.now())).thenReturn(Optional.empty());

        // Test the bookTickets method for a non-existent flight
        boolean result = bookingService.bookTickets("LMN123", LocalDate.now(), 2);

        // Assert that booking was not successful
        assertFalse(result);
        // Verify that the totalSeats were not updated
        Mockito.verify(bookingRepository, Mockito.never()).save(Mockito.any());
    }

}
