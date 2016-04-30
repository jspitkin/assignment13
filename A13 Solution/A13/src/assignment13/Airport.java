package assignment13;

import java.util.Hashtable;
import java.util.LinkedList;

/**
 * The airport class is meant to represent the unique airports, or vertices available in the flight input file.
 * 
 * @author David Reeves
 * @author Naser Abu-Rmaileh
 *
 */
public class Airport 
{
	/**
	 * The airport name.
	 */
	private String airport;

	/**
	 * The list of edges the airport has.
	 */
	private Hashtable<String, Flight> edges;

	/**
	 * The airport's parent (AKA previous) node.
	 */
	private Airport parent;

	/**
	 * Whether or not that the airport has been visited.
	 */
	private boolean isVisited;

	/**
	 * This variable is used as a general edge-weight value holder whose meaning changes, based on the 
	 * user's input criteria (COST, DELAY, DISTANCE, CANCELED, TIME).
	 */
	private double airWeight;

	/**
	 * The airport constructor. Creates an airport, based on the input String value.
	 * @param airport -- The airport's name
	 */
	public Airport (String airport)
	{
		this.airport = airport;
		edges = new Hashtable<String, Flight>();
	}
	
	/**
	 * Return the airWeight value.
	 * @return airWeight
	 */
	public double getAirWeight ()
	{
		return airWeight;
	}

	/**
	 * Set the airWeight value. The airWeight value is used as a general edge-weight value holder whose meaning changes, 
	 * based on the user's input criteria .
	 * @param airWeight
	 */
	public void setAirWeight (double airWeight)
	{
		this.airWeight = airWeight;
	}

	/**
	 * Looks through the table of edges to check whether a the airport has an edge with (AKA a flight to) another airport.
	 * 
	 * @param node -- The input flight
	 * @return -- The flight you are checking for
	 */
	public boolean hasEdge(Flight node) 
	{

		return edges.containsKey(node.getOrigin());
	}
	
	/**
	 * Return the airport's parent (AKA previous)
	 * @return -- The parent
	 */
	public Airport getParent ()
	{
		return parent;
	}

	/**
	 * Set the parent (AKA previous).
	 * @param-- parent
	 */
	public void setParent (Airport parent)
	{
		this.parent = parent;
	}

	/**
	 * Whether or not the airport has been visited.
	 * @return -- True if visited, false, otherwise
	 */
	public boolean isVisited ()
	{
		return isVisited;
	}

	/**
	 * Set the airport's visited status.
	 * @param isVisited -- The value to which you wish to set the airport's status.
	 */
	public void setVisited (boolean isVisited)
	{
		this.isVisited = isVisited;
	}

	/**
	 * Return the airport's String representation..
	 * @return -- The airport's string value.
	 */
	public String getAirport() 
	{
		return airport;
	}

	/**
	 * Set the airport.
	 * @param airport -- The with which you wish to set the airport
	 */
	public void setAirport(String airport)
	{
		this.airport = airport;
	}

	/**
	 * The Hashtable of edges/flights this airport has.
	 * 
	 * @return -- The hashtable of edges 
	 */
	public Hashtable<String, Flight> getEdges() 
	{
		return edges;
	}

	/**
	 * Sets the airport's hashtable of edges to the table in the input.
	 * @param edges -- The input hashtable
	 */
	public void setEdges(Hashtable<String, Flight> edges) 
	{
		this.edges = edges;
	}

	
	/**
	 * This takes in a flight and all of the information that the flight was created with. If the airport already has
	 * an edge with that flight, returns false. Otherwise, adds that flight to the hashtable of edges and returns true.
	 * 
	 * @param flight -- The flight/edge to be added.
	 */
	public boolean addFlight (Flight flight)
	{
		if (hasEdge(flight))
		{
			return false;
		}

		edges.put(flight.getDestination().getAirport(),  flight);
		return true;
	}

	/**
	 * The linked list version of the hashtable of edges/flights. Much easier to iterate through.
	 * @return -- The linked list version of the flight/edge hashtable
	 */
	public LinkedList<Flight> linkedFlight()
	{
		LinkedList<Flight> myFlights = new LinkedList<Flight>();
		
		for(Flight edge: edges.values())
		{
			myFlights.add(edge);
		}
		return myFlights;
	}

	/**
	 * A method to check for airport equality. If two airports have the same name, then they're considered equal.
	 * @param -- The name of the airport's name with which we are comparing
	 * @return -- True if this airport and the input airport's names are the same. False, otherwise.
	 */
	public boolean equals(String a)
	{
		if(a.equals(this.airport))
		{
			return true;
		}
		return false; 
	}
}