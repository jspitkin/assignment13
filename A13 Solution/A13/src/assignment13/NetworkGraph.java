/** 
 * 
 */
package assignment13;

import java.io.BufferedReader; 
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.PriorityQueue;

/**
 * <p>
 * This class represents a graph of flights and airports along with specific data about those flights. It is recommended
 * to create an airport class and a flight class to represent nodes and edges respectively. There are other ways to
 * accomplish this and you are free to explore those.
 * </p>
 * 
 * <p>
 * Testing will be done with different criteria for the "best" path so be sure to keep all information from the given
 * file. Also, before implementing this class (or any other) draw a diagram of how each class will work in relation to
 * the others. Creating such a diagram will help avoid headache and confusion later.
 * </p>
 * 
 * <p>
 * Be aware also that you are free to add any member variables or methods needed to completed the task at hand
 * </p>
 * 
 * @author David Reeves
 * @author Naser Abu-Rmaileh
 */
public class NetworkGraph
{
	/**
	 * The flight to be added to the hashtable of edges.
	 */
	private static Flight flights;

	/**
	 * The hashtable of airports (vertices).
	 */
	public Hashtable<String, Airport> airports;

	/**
	 * The list representing the best path to follow, from origin to destination.
	 */
	private ArrayList<Airport> bestPath;

	/**
	 * <p>
	 * Constructs a NetworkGraph object and populates it with the information contained in the given file. See the
	 * sample files or a randomly generated one for the proper file format.
	 * </p>
	 * 
	 * <p>
	 * You will notice that in the given files there are duplicate flights with some differing information. That
	 * information should be averaged and stored properly. For example some times flights are canceled and that is
	 * represented with a 1 if it is and a 0 if it is not. When several of the same flights are reported totals should
	 * be added up and then reported as an average or a probability (value between 0-1 inclusive).
	 * </p>
	 * 
	 * @param flightInfoPath - The path to the file containing flight data. This should be a *.csv(comma separated
	 *            value) file
	 * 
	 * @throws FileNotFoundException The only exception that can be thrown in this assignment and only if a file is not
	 *             found.
	 */
	public NetworkGraph (String flightInfoPath) throws FileNotFoundException
	{

		airports = new Hashtable<String, Airport>();

		//Reading in the file
		File file = new File(flightInfoPath);
		try
		{
			BufferedReader br = new BufferedReader(new FileReader(file));
			String line;

			// Check for empty file
			if (br.readLine() == null)
			{
				br.close();
				return;
			}

			// Parse rows until end of document
			while ((line = br.readLine()) != null)
			{
				String[] inputData = line.split(",");

				//Sort the info so that it can be put into a graph
				sortData(inputData);
			}

			br.close();
		}

		//If the file isn't found, throw an exception
		catch (IOException e)
		{
			System.out.println("NetworkGraph has run into an IO Exception");
		}

	}

	/**
	 * This is a private helper method which aggregates specifically ordered input data from a .csv file and sorts it into an array.
	 * That data is then sorted and used as input to construct a graph consisting of airports as vertices and flights as edges.
	 * 
	 * @param list -- The list of aggregated data, in a specific order
	 */
	private void sortData (String[] list)
	{
		// This keeps us from throwing an out of bounds exception
		if (list.length < 8)
		{
			return;
		}

		//Get all the data from the list
		Airport origin = new Airport(list[0]);
		Airport destination = new Airport(list[1]);
		String carrier = list[2];	
		double delay = Double.parseDouble(list[3]);

		double canceled = Double.parseDouble(list[4]);		
		double time = Double.parseDouble(list[5]);
		double distance = Double.parseDouble(list[6]);
		double cost = Double.parseDouble(list[7]);

		//Add the airports to the graph
		origin = addVertex(origin);
		destination = addVertex(destination);

		//If the flight is not a duplicate, construct a new one, and add it to the graph
		if (!isDuplicateFlight(origin, destination))
		{
			flights = new Flight(origin, destination, carrier, delay, canceled, time, distance, cost);
			addFlight(origin, destination, flights);
			return;
		}

		//Construct the flight, even if it's a duplicate. Doing this allows all the parameters to be incremented,
		//such that an accurate, single, average edge weight can be calculated
		Flight duplicateFlight = origin.getEdges().get(destination.getAirport());
		duplicateFlight.setCanceled(canceled);
		duplicateFlight.setCost(cost);
		duplicateFlight.setDelay(delay);
		duplicateFlight.setDistance(distance);
		duplicateFlight.setTime(time);
		duplicateFlight.addCarrier(carrier);


	}

	/**
	 * Adds a vertex to the graph and returns the input if there are no airports that match vertex already in the map or
	 * it will return the Airport that is in the map that matches the inputs key.
	 * 
	 * @param vertex -- Vertex to add
	 */

	public Airport addVertex (Airport vertex)
	{

		if (airports.get(vertex.getAirport()) != null)
		{
			return airports.get(vertex.getAirport());
		}

		airports.put(vertex.getAirport(), vertex);
		return vertex;
	}

	/**
	 * Adds a flight/edge to it's originating and terminating airports.
	 * 
	 * @param originAirport -- The origin airport
	 * @param destination -- The destination airport
	 * @param flight -- The flight to be added
	 */
	public static void addFlight (Airport originAirport, Airport destination, Flight flight)
	{
		originAirport.addFlight(flights);
		destination.addFlight(flights);
		return;
	}

	/**
	 * Helper method to check whether or not a certain flight already exists in our airport hashtable.
	 * This is done by comparing its originating and terminating airports. Returns true if the flight already
	 * exists and false, otherwise.
	 * 
	 * @param originAirport -- The origin airport
	 * @param destination -- The destination airport
	 * @return -- True if the flight is a duplicate and false if it is not
	 */
	public boolean isDuplicateFlight (Airport originAirport, Airport destination)
	{
		Airport air = airports.get(originAirport.getAirport());
		// Does this originating ap exist
		if (airports.containsKey(originAirport.getAirport()))
		{
			// Does this destination exist in this originating air port
			if (air.getEdges().containsKey(destination.getAirport()))
			{
				return true;
			}
		}
		return false;
	}

	/**
	 * This method returns a BestPath object containing information about the best way to fly to the destination from
	 * the origin. "Best" is defined by the FlightCriteria parameter <code>enum</code>. This method should throw no
	 * exceptions and simply return a BestPath object with information dictating the result. For example, if a
	 * destination or origin is not contained in this instance of NetworkGraph simply return a BestPath with no path
	 * (not a <code>null</code> path). If origin or destination are <code>null</code> return a BestPath object with null
	 * as origin or destination (which ever is <code>null</code>.
	 * 
	 * @param origin - The starting location to find a path from. This should be a 3 character long string denoting an
	 *            airport.
	 * 
	 * @param destination - The destination location from the starting airport. Again, this should be a 3 character long
	 *            string denoting an airport.
	 * 
	 * @param criteria - This enum dictates the definition of "best". Based on this value a path should be generated and
	 *            return.
	 * 
	 * @return - An object containing path information including origin, destination, and everything in between.
	 */
	public BestPath getBestPath (String origin, String destination, FlightCriteria criteria)
	{

		BestPath path = new BestPath();
		this.bestPath = new ArrayList<Airport>();

		/**
		 * This list is used only if the user input is considered "garbage." This entails the origin and/or destination airports 
		 * not existing in the flights file, or the user input for the origin and/or destination airports being null.
		 */
		ArrayList<String> garbagePath = new ArrayList<String>();

		//If both the origin AND destination inputs are null
		if (origin == null && destination == null)
		{
			garbagePath.add("null");
			garbagePath.add("null");
			path.setStringArray(garbagePath);
			path.setPathLength(0);
			return path;
		}

		//If only the origin input is null
		if (origin == null)
		{
			garbagePath.add("null");
			garbagePath.add(destination);
			path.setStringArray(garbagePath);
			path.setPathLength(0);
			return path;
		}

		//If only the destination input is null
		if (destination == null)
		{
			garbagePath.add(origin);
			garbagePath.add("null");
			path.setStringArray(garbagePath);
			path.setPathLength(0);
			return path;
		}

		/**
		 * The origin airport. Becomes null if the origin does not exist.
		 */
		Airport start = findAirport(origin);

		/**
		 * The destination airport. Becomes null if the destination does not exist.
		 */
		Airport end = findAirport(destination);

		//If both the start AND end airports do not exist
		if (start == null && end == null)
		{
			garbagePath.add("");
			path.setStringArray(garbagePath);
			path.setPathLength(0);
			return path;
		}

		//If only the start airport does not exist
		if (start == null && end != null)
		{
			garbagePath.add("");
			path.setStringArray(garbagePath);
			path.setPathLength(0);
			return path;
		}

		//If only the end airport does not exist
		if (start != null && end == null)
		{
			garbagePath.add("");
			path.setStringArray(garbagePath);
			path.setPathLength(0);
			return path;
		}


		//Find the cheapest path between the 2 input airports, based on the criteria
		dijkstra(start, end, criteria);

		//Reverse the order of the path found
		Collections.reverse(bestPath);

		//Set the path
		path.setPath(bestPath);

		//Set the path's length
		path.setPathLength(end.getAirWeight());
		
		//In case, after the criteria is entered, a path is still not found between the airports
		if (path.getPathLength() == 0)
		{
			garbagePath.add("");
			path.setStringArray(garbagePath);
			return path;
		}

		return path;
	}

	/**
	 * Finds an airport, based on its String name from the hashtable of airports. Returns null if the input airport doesn't exist.
	 * Otherwise, returns the airport.
	 * 
	 * @param vertex -- The input airport you are searching for
	 * @return -- The airport if found. Otherwise, returns null
	 */
	private Airport findAirport (String vertex)
	{
		if (airports.get(vertex) == null)
		{
			return null;
		}
		else
		{
			return airports.get(vertex);
		}
	}

	/**
	 * This method finds the cheapest path between the origin and destination airport, based on the given criteria. 
	 * That path is then put into an ArrayList (but it will be in reverse order, from destination to origin). If no airports exist
	 * in the airport lookup table, an empty path is returned.
	 * 
	 * @param origin -- The origin/start location
	 * @param dest -- The destination/goal location
	 * @param comp -- The criteria used to calculate edge values
	 * @return -- An ArrayList with the cheapest path, from destination to origin
	 */
	private ArrayList<Airport> dijkstra (Airport origin, Airport dest, FlightCriteria comp)
	{
		//Check for empty hashtable
		if (airports.isEmpty())
		{
			return bestPath;
		}

		//Rest the distances
		resetDistances();

		//Get the start location's airport
		Airport source = airports.get(origin.getAirport());
		//Check if that airport exists. If not, return an empty list
		if (source == null)
		{
			return bestPath;
		}

		//Set the edge weight
		source.setAirWeight(0);

		//Use a custom comparator
		Comparator<Airport> compAirWeights = new weightComparator();

		//Create a priority queue of an arbitrary size and give it the comparator to use
		PriorityQueue<Airport> airportQ = new PriorityQueue<Airport>(20, compAirWeights);

		//Add the starting location
		airportQ.add(source);

		//So long as the queue has something in it, do the following:
		while (!airportQ.isEmpty())
		{
			//Remove the top of the queue and set it to the current airport
			Airport curr = airportQ.poll();

			//Iterate through the linked list of curr's edges
			for (Flight edge : curr.linkedFlight())
			{

				// Gets the the destination of the current node for the current flight
				Airport next = getDestinationOnFlight(edge);

				// Gets the weight (Cost, Delay, Canceled, Time, or Distance) of the edge between curr and next.
				double weight = edge.getWeight(comp);

				// Gets the weight of current airport
				double currAirWeight = curr.getAirWeight();

				// Gets the weight of the next
				double nextAirWeight = next.getAirWeight();

				// Combines the weight of the current node with the edge weight
				double potentialWeight = currAirWeight + weight;
				
				//Verify that the edge doesn't have a 100% chance of being canceled
				double cancellationProb = edge.getCancellationProb();

				// A shorter path has been found
				if (nextAirWeight > potentialWeight && !next.isVisited() && potentialWeight > 0 && cancellationProb != 1)
				{
					//Add the next airport to the queue
					airportQ.add(next);
					//Set that airport's parent/previous to the current airport
					next.setParent(curr);
					//Set its new weight
					next.setAirWeight(potentialWeight);
					//Set it as visited
					curr.setVisited(true);
				}
			}

		}

		//If the edge weight to the destination is infinity, then no path from start to goal was found
		if (dest.getAirWeight() == Double.POSITIVE_INFINITY)
		{
			dest.setAirWeight(0);
		}
		//So long as the destination is not null, add it to the list and get the airport which has linked to it (it's "previous node")
		while (dest != null)
		{
			bestPath.add(dest);
			dest = dest.getParent();
		}

		return bestPath;
	}

	/**
	 * Return a flight's destination as an airport.
	 * 
	 * @param input -- The flight whose destination you want
	 * @return -- The destination airport
	 */
	private Airport getDestinationOnFlight (Flight input)
	{
		return input.getDestination();
	}

	/**
	 * Reset the edge weight values of all the airports in the airport hashtable to infinity, set the parent (previous)
	 * references to null and set their visited state to false.
	 */
	private void resetDistances ()
	{
		for (Airport curr : airports.values())
		{
			curr.setAirWeight(Double.POSITIVE_INFINITY);
			curr.setParent(null);
			curr.setVisited(false);
		}
	}

	/**
	 * <p>
	 * This overloaded method should do the same as the one above only when looking for paths skip the ones that don't
	 * match the given airliner.
	 * </p>
	 * 
	 * @param origin - The starting location to find a path from. This should be a 3 character long string denoting an
	 *            airport.
	 * 
	 * @param destination - The destination location from the starting airport. Again, this should be a 3 character long
	 *            string denoting an airport.
	 * 
	 * @param criteria - This enum dictates the definition of "best". Based on this value a path should be generated and
	 *            return.
	 * 
	 * @param airliner - a string dictating the airliner the user wants to use exclusively. Meaning no flights from
	 *            other airliners will be considered.
	 * 
	 * @return - An object containing path information including origin, destination, and everything in between.
	 */
	public BestPath getBestPath (String origin, String destination, FlightCriteria criteria, String airliner)
	{
		BestPath path = new BestPath();
		this.bestPath = new ArrayList<Airport>();

		/**
		 * This list is used only if the user input is considered "garbage." This entails the origin and/or destination airports 
		 * not existing in the flights file, or the user input for the origin and/or destination airports being null.
		 */
		ArrayList<String> garbagePath = new ArrayList<String>();

		//If both the origin AND destination inputs are null
		if (origin == null && destination == null)
		{
			garbagePath.add("null");
			garbagePath.add("null");
			path.setStringArray(garbagePath);
			path.setPathLength(0);
			return path;
		}

		//If only the origin input is null
		if (origin == null)
		{
			garbagePath.add("null");
			garbagePath.add(destination);
			path.setStringArray(garbagePath);
			path.setPathLength(0);
			return path;
		}

		//If only the destination input is null
		if (destination == null)
		{
			garbagePath.add(origin);
			garbagePath.add("null");
			path.setStringArray(garbagePath);
			path.setPathLength(0);
			return path;
		}

		/**
		 * The origin airport. Becomes null if the origin does not exist.
		 */
		Airport start = findAirport(origin);

		/**
		 * The destination airport. Becomes null if the destination does not exist.
		 */
		Airport end = findAirport(destination);

		//If both the start AND end airports do not exist
		if (start == null && end == null)
		{
			garbagePath.add("");
			path.setStringArray(garbagePath);
			path.setPathLength(0);
			return path;
		}

		//If only the start airport does not exist
		if (start == null && end != null)
		{
			garbagePath.add("");
			path.setStringArray(garbagePath);
			path.setPathLength(0);
			return path;
		}

		//If only the end airport does not exist
		if (start != null && end == null)
		{
			garbagePath.add("");
			path.setStringArray(garbagePath);
			path.setPathLength(0);
			return path;
		}

		//Find the cheapest path between the 2 input airports, based on the criteria and carrier
		dijkstraCarrier(start, end, criteria, airliner);

		//Reverse the order of the path found
		Collections.reverse(bestPath);

		//Set the path
		path.setPath(bestPath);

		//Set the path's length
		path.setPathLength(end.getAirWeight());
		
		//In case, after the criteria is entered, a path is still not found between the airports
		if (path.getPathLength() == 0)
		{
			garbagePath.add("");
			path.setStringArray(garbagePath);
			return path;
		}

		return path;
	}

	/**
	 * This performs dijkstra's algorithm on the the NetworkGraph and returns an ArrayList<Airports> with the path if it
	 * exists and an empty ArrayList if the path does not exist.
	 * 
	 * @param vertex(The start node)
	 * @param dest(The destination node)
	 * @param typeWeight(The argument used for finding the flight weights (example cost, time, distance etc)
	 * @param airLiner (The airliner you would like to get the path through)
	 * @return
	 */
	private ArrayList<Airport> dijkstraCarrier (Airport vertex, Airport dest, FlightCriteria typeWeight,
			String airLiner)
	{
		//Check if the airport hashtable is empty
		if (airports.isEmpty())
		{
			return bestPath;
		}

		// This will reset the graph for a new start position.
		resetDistances();

		//Get the originating airport
		Airport source = airports.get(vertex.getAirport());

		//If it's null, return an empty list
		if (source == null)
		{
			return bestPath;
		}

		//Set the edge weight to 0
		source.setAirWeight(0);

		//Create the new, custom comparator
		Comparator<Airport> compAirWeights = new weightComparator();

		//Pass in that comparator to the queue
		PriorityQueue<Airport> airportQ = new PriorityQueue<Airport>(15, compAirWeights);

		//Add the originating airport to the queue
		airportQ.add(source);

		//So long as the queue has something in it, do as follows:
		while (!airportQ.isEmpty())
		{
			//Set the current item to the first item in the queue as you poll it
			Airport curr = airportQ.poll();

			//Iterate 
			for (Flight edge : curr.linkedFlight())
			{
				// Gets the the destination of the current node for the current flight
				Airport next = getDestinationOnFlight(edge);

				// Gets the weight (Cost, Delay, Canceled, Time, or Distance) of the edge between curr and next.
				double weight = edge.getWeight(typeWeight);

				// Gets the weight of current airport
				double currAirWeight = curr.getAirWeight();

				// Gets the weight of the next
				double nextAirWeight = next.getAirWeight();

				// Combines the weight of the current node with the edge weight
				double potentialWeight = currAirWeight + weight;
				
				//Verify that the edge doesn't have a 100% chance of being canceled
				double cancellationProb = edge.getCancellationProb();

				// A shorter path has been found and it has a flight with the specified carrier
				if (nextAirWeight > potentialWeight && !next.isVisited() && edge.getCarrierList().containsKey(airLiner) && potentialWeight > 0 && cancellationProb != 1)
				{
					//Add the next airport to the queue
					airportQ.add(next);
					//Set that airport's parent/previous to the current airport
					next.setParent(curr);
					//Set its new weight
					next.setAirWeight(potentialWeight);
					//Set it as visited
					curr.setVisited(true);
				}
			}
		}

		//If the edge weight to the destination is infinity, then no path from start to goal was found
		if (dest.getAirWeight() == Double.POSITIVE_INFINITY)
		{
			dest.setAirWeight(0);
		}

		//So long as the destination is not null, add it to the list and get the airport which has linked to it (it's "previous node")
		while (dest != null)
		{
			bestPath.add(dest);
			dest = dest.getParent();
		}

		return bestPath;
	}
}

/**
 * This class creates a custom comparator with which to compare airports/vertices, using their 
 * edge weight. 
 */
class weightComparator implements Comparator<Airport>
{

	@Override
	public int compare (Airport o1, Airport o2)
	{
		//If the edge weight is the same, return 0
		if (o1.getAirWeight() - o2.getAirWeight() == 0)
		{
			return 0;
		}
		//If the edge weight of o1 is greater than the edge weight of o2, return 1
		else if (o1.getAirWeight() - o2.getAirWeight() > 1)
		{
			return 1;
		}
		//Otherwise, the edge weight of o1 is less than the edge weight of o2, so return -1
		else
		{
			return -1;
		}
	}

}