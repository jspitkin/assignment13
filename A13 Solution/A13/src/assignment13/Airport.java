package assignment13;

import java.util.HashMap;

public class Airport implements Comparable<Airport> {
	HashMap<String, Flight> flights;
	
	String airportCode;
	
	double costFromStart;
	Airport previous;
	boolean visited;
	
	public Airport(String airportCode) {
		this.airportCode = airportCode;
		this.flights = new HashMap<String, Flight>();
		reset();
	}
	
	public void reset() {
		costFromStart = Integer.MAX_VALUE;
		previous = null;
		visited = false;
	}
	
	public boolean containsFlight(String destination) {
		return flights.containsKey(destination);
	}
	
	public Flight getFlight(String destination) {
		return flights.get(destination);
	}
	
	public void addFlight(String destination, Flight flight){
		flights.put(destination, flight);
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
