/**
 * 
 */
package assignment13;

import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * <p>This class represents a graph of flights and airports along with specific
 * data about those flights. It is recommended to create an airport class and a
 * flight class to represent nodes and edges respectively. There are other ways
 * to accomplish this and you are free to explore those.</p>
 * 
 * <p>Testing will be done with different criteria for the "best" path so be
 * sure to keep all information from the given file. Also, before implementing
 * this class (or any other) draw a diagram of how each class will work in
 * relation to the others. Creating such a diagram will help avoid headache and
 * confusion later.</p>
 * 
 * <p>Be aware also that you are free to add any member variables or methods
 * needed to completed the task at hand</p>
 * 
 * @author CS2420 Teaching Staff - Spring 2016
 */
public class NetworkGraph {
	
	private HashMap<String, Airport> flights;

	/**
	 * <p>Constructs a NetworkGraph object and populates it with the information
	 * contained in the given file. See the sample files or a randomly generated
	 * one for the proper file format.</p>
	 * 
	 * <p>You will notice that in the given files there are duplicate flights with
	 * some differing information. That information should be averaged and stored
	 * properly. For example some times flights are canceled and that is
	 * represented with a 1 if it is and a 0 if it is not. When several of the same
	 * flights are reported totals should be added up and then reported as an
	 * average or a probability (value between 0-1 inclusive).</p>
	 * 
	 * @param flightInfoPath - The path to the file containing flight data. This
	 * should be a *.csv(comma separated value) file
	 * 
	 * @throws FileNotFoundException The only exception that can be thrown in
	 * this assignment and only if a file is not found.
	 */
	public NetworkGraph(String flightInfoPath) throws FileNotFoundException {
		flights = new HashMap<String, Airport>();
		Scanner scanner = new Scanner(new File(flightInfoPath));
		scanner.nextLine();
        scanner.useDelimiter(",");
        while(scanner.hasNext()){
        	String currentLine = scanner.nextLine();
        	String[] splitLine = currentLine.split(",[ ]*");
            String source = splitLine[0];
            String destination = splitLine[1];
            String carrier = splitLine[2];
            Double delay = Double.parseDouble(splitLine[3]);
            Double canceled = Double.parseDouble(splitLine[4]);
            Double time = Double.parseDouble(splitLine[5]);
            Double distance = Double.parseDouble(splitLine[6]);
            Double cost = Double.parseDouble(splitLine[7]);
            Airport airport;
            Flight flight;
            
            if (flights.containsKey(source)) {
            	airport = flights.get(source);
            }
            else {
            	airport = new Airport(source);
            	flights.put(source, airport);
            }
            
            if (airport.containsFlight(destination)) {
            	flight = airport.getFlight(destination);
            	flight.carriers.add(carrier);
            	flight.delay += delay;
            	flight.canceled += canceled;
            	flight.time += time;
            	flight.distance += distance;
            	flight.cost += cost;
            	flight.flightCount++;
            }
            else {
            	flight = new Flight(source, destination, carrier, cost, delay, distance, canceled, time);
            	airport.addFlight(destination, flight);
            }
        }

        scanner.close();
	}

	/**
	 * This method returns a BestPath object containing information about the best
	 * way to fly to the destination from the origin. "Best" is defined by the
	 * FlightCriteria parameter <code>enum</code>. This method should throw no
	 * exceptions and simply return a BestPath object with information dictating
	 * the result. For example, if a destination or origin is not contained in
	 * this instance of NetworkGraph simply return a BestPath with no path (not a
	 * <code>null</code> path). If origin or destination are <code>null</code>
	 * return a BestPath object with null as origin or destination (which ever is
	 * <code>null</code>.
	 * 
	 * @param origin - The starting location to find a path from. This should be a
	 * 3 character long string denoting an airport.
	 * 
	 * @param destination - The destination location from the starting airport.
	 * Again, this should be a 3 character long string denoting an airport.
	 * 
	 * @param criteria - This enum dictates the definition of "best". Based on this
	 * value a path should be generated and return.
	 * 
	 * @return - An object containing path information including origin, destination,
	 * and everything in between.
	 */
	public BestPath getBestPath(String origin, String destination, FlightCriteria criteria) {
		return dijkstras(flights.get(origin), flights.get(destination), criteria, null);
	}
	
	/**
	 * <p>This overloaded method should do the same as the one above only when looking for paths
	 * skip the ones that don't match the given airliner.</p>
	 * 
	 * @param origin - The starting location to find a path from. This should be a
	 * 3 character long string denoting an airport.
	 * 
	 * @param destination - The destination location from the starting airport.
	 * Again, this should be a 3 character long string denoting an airport.
	 * 
	 * @param criteria - This enum dictates the definition of "best". Based on this
	 * value a path should be generated and return.
	 * 
	 * @param airliner - a string dictating the airliner the user wants to use exclusively. Meaning
	 * no flights from other airliners will be considered.
	 * 
	 * @return - An object containing path information including origin, destination,
	 * and everything in between.
	 */
	public BestPath getBestPath(String origin, String destination, FlightCriteria criteria, String airliner) {
		return dijkstras(flights.get(origin), flights.get(destination), criteria, airliner);
	}
	
	private BestPath dijkstras(Airport start, Airport goal, FlightCriteria criteria, String airliner) {
		
		// reset the graph for running Dijkstra's more than once
		for(Airport airport : flights.values()) { 
			airport.reset();
		}
		
		boolean specificAirliner = false;
		if (airliner != null) {
			specificAirliner = true;
		}
		
		PriorityQueue<Airport> queue = new PriorityQueue<Airport>();
		queue.add(start);
		start.costFromStart = 0;
		while(!queue.isEmpty()) {
			Airport current = queue.remove();
			
			// Path found - construct a BestPath object to represent it
			if (current == goal) {
				BestPath solution = new BestPath();
				solution.pathLength = current.costFromStart;
				
				Airport walkPath = goal;
				while (walkPath.previous != null) {
					solution.path.add(0, walkPath.airportCode);
					walkPath = walkPath.previous;
				}
				solution.path.add(0, start.airportCode);
				
				return solution;
			}
			
			current.visited = true;
			
			for (Flight flight : current.flights.values()) {
				if (specificAirliner && flight.carriers.contains(airliner)) {
					continue;
				}
				
				Airport n = flights.get(flight.destination);
				double cost = 0.0;
				switch(criteria) {
				case COST:
					cost = flight.cost/flight.flightCount;
					break;
				case DELAY:
					cost = flight.delay/flight.flightCount;
					break;
				case DISTANCE:
					cost = flight.distance/flight.flightCount;
					break;
				case CANCELED:
					cost = flight.canceled/flight.flightCount;
					break;
				case TIME:
					cost = flight.time/flight.flightCount;
					break;
				}
				if (n != null && !n.visited && n.costFromStart > (current.costFromStart + cost)) {
					queue.remove(n);
					queue.add(n);
					n.previous = current;
					n.costFromStart = current.costFromStart + cost;
				}
			}
		}
		
		return new BestPath();
	}
}
