package com.example.SearchFlights.model;

public class FlightDetails {

    private String flightNumber;
    private String source;
    private String destination;
    private String departureTime;
    private String arrivalTime;
    
    public FlightDetails() {}

	public FlightDetails(String flightNumber, String source, String destination, String departureTime,
			String arrivalTime) {
		super();
		this.flightNumber = flightNumber;
		this.source = source;
		this.destination = destination;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}


	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}





	public String getDepartureTime() {
		return departureTime;
	}





	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}





	public String getArrivalTime() {
		return arrivalTime;
	}





	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	
    
}
