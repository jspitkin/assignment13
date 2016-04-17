package assignment13;

import java.util.ArrayList;

public class Airport implements Comparable<Airport> {
	ArrayList<Flight> flights;
	String airportCode;
	
	double costFromStart;
	Airport previous;
	boolean visited;
	
	public Airport(String airportCode) {
		this.airportCode = airportCode;
		this.flights = new ArrayList<Flight>();
		costFromStart = Integer.MAX_VALUE;
		previous = null;
		visited = false;
	}
	
	public boolean containsFlight(String destination) {
		for (Flight flight: flights) {
			if (flight.destination == destination) {
				return true;
			}
		}
		return false;
	}
	
	public Flight getFlight(String destination) {
		for (Flight flight: flights) {
			if (flight.destination == destination) {
				return flight;
			}
		}
		return null;
	}

	@Override
	public int compareTo(Airport rhs) {
		if (this.costFromStart > rhs.costFromStart) {
			return 1;
		}
		else if (this.costFromStart < rhs.costFromStart) {
			return -1;
		}
		return 0;
	}
}
