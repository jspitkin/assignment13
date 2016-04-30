package assignment13;


import java.util.Hashtable;

/**
 * This class is meant to represent the flights, or edges, between airports. All flights between two airports are 
 * averaged into a single flight/edge, based on the input data.
 * 
 * @author David Reeves
 * @author Naser Abu-Rmaileh
 *
 */
public class Flight
{
	/**
	 * The origin airport.
	 */
	private Airport origin;

	/**
	 * The destination airport.
	 */
	private Airport destination;

	/**
	 * The hashtable of carriers which have flights between two airports. 
	 */
	private Hashtable<String,String> carrierList;

	/**
	 * The flight's carrier.
	 */
	private String carrier;

	/**
	 * The flight's delay.
	 */
	private double delay;

	/**
	 * Whether or not the flight has been canceled.
	 */
	private double canceled;

	/**
	 * The length of a flight.
	 */
	private double time;

	/**
	 * The flight's distance.
	 */
	private double distance;

	/**
	 * The flight's cost.
	 */
	private double cost;

	/**
	 * A number used to keep track of the number of carriers with flights between two airports.
	 */
	private int countCarrier;

	/**
	 * The flight criteria. This value is used to find paths between airports, based on the user's input preference.
	 * The value of this variable changes based on said preference and dictates which constraints or paths to observe 
	 * when searching for a flight.
	 */
	private static Enum<FlightCriteria> weightType = null;
	
	/**
	 * The flight constructor. Creates a flight/edge, based on the input values. Flights are considered to be the same if the
	 * go between the same two airports.
	 * 
	 * @param origin -- The originating airport.
	 * @param destination -- The destination airport.
	 * @param carrier -- The carrier.
	 * @param delay -- The delay.
	 * @param canceled -- Whether or not the flight has been canceled.
	 * @param time -- The length of the flight.
	 * @param distance -- The distance between the origin and destination airports.
	 * @param cost -- The cost of the flight.
	 */
	public Flight (Airport origin, Airport destination, String carrier, double delay, double canceled, double time,
			double distance, double cost)
	{
		this.carrier = carrier;
		carrierList = new Hashtable<String,String>();
		this.origin = origin;
		this.destination = destination;
		carrierList.put(carrier,carrier);
		countCarrier++;
		this.delay = delay;
		this.canceled = canceled;
		this.time = time;
		this.distance = distance;
		this.cost = cost;

	}

	/**
	 * Returns weightType.
	 * @return -- weightType.
	 */
	public Enum<FlightCriteria> getWeightType()
	{
		return weightType;
	}

	/**
	 * Returns the weight. The weight is what gives each edge/flight a value and changes based
	 * on the value of the weightType (AKA the flight criteria). 
	 * @param weightType
	 * @return
	 */
	public double getWeight (FlightCriteria weightType)
	{
		//The value with which we're dividing the number of flights/edges between two airports
		//This will give us a single edge, represented with an average value of all the edges
		int divider = 1;

		FlightCriteria crit = weightType;

		//Gotta avoid dividing by 0. If there's more than one flight between two airports, then update divider's value.
		if(countCarrier > 0)
		{
			divider = countCarrier;
		}

		//If null, then leave it as null.
		if(Flight.weightType == null)
		{
			Flight.weightType = weightType;
		}

		//Switch case for assigning weightType and then, based on that, calculating average edge weight
		switch (crit)
		{
		case COST:
		{               
			return cost/divider;
		}
		case DELAY:
		{          
			return delay/divider;
		}
		case DISTANCE:
		{             
			return distance/divider;
		}
		case CANCELED:
		{               
			return canceled/divider;
		}
		case TIME:
		{
			return time/divider;
		}
		}

		return 0.0;


	}

	/**
	 * Sets the weight of the edge, based on the user input flight criteria.
	 * @param weight -- The input value to which you wish to set the weight.
	 */
	public void setWeight (double weight)
	{
		if (weightType.equals(FlightCriteria.COST))
		{
			this.cost = weight;
		}
		else if (weightType.equals(FlightCriteria.DELAY))
		{
			this.delay = weight;
		}
		else if (weightType.equals(FlightCriteria.DISTANCE))
		{
			this.distance = weight;
		}
		else if (weightType.equals(FlightCriteria.TIME))
		{
			this.time = weight;
		}
		else if (weightType.equals(FlightCriteria.CANCELED))
		{
			this.canceled = weight;
		}
	}
	
	/**
	 * Returns the probability of a flight being cancelled. If that value is 1, then the flight has a 100%
	 * probability of being cancelled and will not be considered in calculating edge weight.
	 * 
	 * @return -- Probability of being cancelled.
	 */
	public double getCancellationProb ()
	{
		int divider = 1;
		//Gotta avoid dividing by 0. If there's more than one flight between two airports, then update divider's value.
		if(countCarrier > 0)
		{
			divider = countCarrier;
		}
		
		return canceled/divider;
		
	}


	/**
	 * Returns the hastable of carriers.
	 * @return -- A hashtable of carriers.
	 */
	public Hashtable<String,String> getCarrierList ()
	{
		return carrierList;
	}

	/**
	 * Adds a carrier to the carrier table and increments countCarrier (the number of carriers).
	 * @param carrier -- The carrier to be added.
	 */
	public void addCarrier (String carrier)
	{
		carrierList.put(carrier,carrier);
		countCarrier++;
	}

	
	/**
	 * This is the airport from which a flight/edge originates.
	 * 
	 * @return -- The origin airport.
	 */
	public Airport getOrigin ()
	{
		return origin;
	}

	/**
	 * This is the airport in which the flight terminates.
	 * 
	 * @return -- The destination airport.
	 */
	public Airport getDestination ()
	{
		return destination;
	}

	/**
	 * Returns the average delay of all the flights between the same two airports.
	 * @return -- The average delay.
	 */
	public double getDelay ()
	{
		if (carrierList.size() >= 1)
		{
			return delay / carrierList.size();
		}
		
		return delay;
	}

	/**
	 * Sets the flight's delay value.
	 * @param delay -- The value which you wish to add to the delay.
	 */
	public void setDelay (double delay)
	{
		this.delay = this.delay + delay;
	}

	/**
	 * Returns the probability of a flight between two airports being canceled.
	 * @return -- The probability, between 0 and 1, inclusive, of a flight being canceled.
	 */
	public double getCanceled ()
	{
		if (carrierList.size() >= 1)
		{
			return canceled / carrierList.size();
		}
		return canceled;
	}

	/**
	 * Sets the flight's canceled value.
	 * @param canceled -- The value which you wish to add to the canceled value
	 */
	public void setCanceled (double canceled)
	{
		this.canceled = this.canceled + canceled;
	}

	/**
	 * Returns the length of a flight between two airports.
	 * @return -- The average length of the flight.
	 */
	public double getTime ()
	{
		if (carrierList.size() >= 1)
		{
			return time / carrierList.size();
		}
		
		return time;
	}

	/**
	 * Sets the flight's time value.
	 * @param time -- The length of a flight
	 */
	public void setTime (double time)
	{
		this.time = this.time + time;
	}

	/**
	 * Returns a flight's distanced traveled between two airports.
	 * @return -- The average distance traveled between two airports.
	 */
	public double getDistance ()
	{
		if (carrierList.size() >= 1)
		{
			return distance / carrierList.size();
		}
		
		return distance;
	}
	
	/**
	 * Sets the flight's distance.
	 * @param distance -- The flight's distance
	 */
	public void setDistance (double distance)
	{
		this.distance = this.distance + distance;
	}

	/**
	 * Returns the cost of a flight between two airports.
	 * @return -- The average cost of a flight between two airports.
	 */
	public double getCost ()
	{
		if (carrierList.size() >= 1)
		{
			return cost / carrierList.size();
		}
		
		return cost;
	}

	/**
	 * Sets the flight's cost.
	 * @param cost -- The flight's cost
	 */
	public void setCost (double cost)
	{
		this.cost = this.cost + cost;
	}

	/**
	 * Returns the flight's carrier.
	 * @return -- The flight's carrier
	 */
	public String getCarrier ()
	{
		return this.carrier;
	}

}
