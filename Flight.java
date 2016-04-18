package assignment13;

import java.util.HashSet;

public class Flight {
	String source;
	String destination;
	double cost;
	double delay;
	double distance;
	double canceled;
	double time;
	int flightCount;
	
	HashSet<String> carriers = new HashSet<String>();
	
	public Flight(String source, String destination, String carrier, double cost, double delay, double distance, double canceled, double time) {
		this.source = source;
		this.destination = destination;
		this.cost = cost;
		this.delay = delay;
		this.distance = distance;
		this.canceled = canceled;
		this.time = time;
		this.carriers.add(carrier);
		this.flightCount = 1;
	}
}
