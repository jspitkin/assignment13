package assignment13;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * A variety of tests which verify that everything in the assignment13 package is working correctly.
 * Each test method has at least 2 assertEquals tests contained in it. These tests verify that the NetworkGraph
 * class functions as expected, for a different inputs and/or conditions.
 * 
 * @author David Reeves
 * @author Naser Abu-Rmaileh
 *
 */
public class Tests 
{
	/**
	 * The NetworkGraph used for testing.
	 */
	private NetworkGraph graph;

	/**
	 * The path used for testing.
	 */
	private BestPath path;

	/**
	 * The equality array used to verify that the path found is correct.
	 */
	private String [] equalityArray;

	/**
	 * The accuracy for testing equality of doubles.
	 */
	private static final double DELTA = 1e-15;

	/**
	 * Set up the graph to read from the file used for testing.
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception 
	{
		graph = new NetworkGraph("testFile.csv");
	}

	/**
	 * Test that getBestPath returns the correct path when it should be a direct flight.
	 * Based on cost.
	 */
	@Test
	public void testBestPathForDirectFlight() 
	{
		//Get the best path
		path = graph.getBestPath("A", "B", FlightCriteria.COST);
		//Print out the path
		System.out.println("Direct Flight Path Based on Cost: " + path.toString());
		//Set the equality array
		equalityArray = new String []{"A", "B"};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(163.5, path.getPathLength(), DELTA);

	}

	/**
	 * Test that getBestPath returns the correct path when it should be a non direct flight.
	 * Based on distance.
	 */
	@Test
	public void testBestPathForNonDirectFlight() 
	{
		//Get the best path
		path = graph.getBestPath("A", "B", FlightCriteria.DISTANCE);
		//Print out the path
		System.out.println("Non-direct Flight Path Based on Distance: " + path.toString());
		//Set the equality array
		equalityArray = new String []{"A", "C", "B"};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(467, path.getPathLength(), DELTA);
	}

	/**
	 * Test that getBestPath returns the correct path with the correctly averaged values.
	 * Based on delay.
	 */
	@Test
	public void testBestPathForCorrectlyAveragedValues() 
	{
		//Get the best path
		path = graph.getBestPath("X", "Y", FlightCriteria.DELAY);
		//Print out the path
		System.out.println("Direct flight tested for correct averaging: " + path.toString());
		//Set the equality array
		equalityArray = new String []{"X", "Y"};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(8.25, path.getPathLength(), DELTA);
	}

	/**
	 * Test that getBestPath returns the correct path with the correctly averaged values.
	 * Based on distance, only considering airline "QA." This test passes because
	 * all the costs are the same, so the average distance is the same as a single flight's distance.
	 */
	@Test
	public void testBestPathForCorrectlyAveragedDistanceWhenASpecificAirlineIsConsidered() 
	{
		//Get the best path
		path = graph.getBestPath("X", "Y", FlightCriteria.DISTANCE, "QA");
		//Print out the path
		System.out.println("Direct flight tested for correct averaging when only a certain airline is considered: " + path.toString());
		//Set the equality array
		equalityArray = new String []{"X", "Y"};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(327, path.getPathLength(), DELTA);
	}

	/**
	 * Test that getBestPath returns the correct path with the correctly averaged values.
	 * Based on distance, only considering airline "QA."
	 */
	@Test
	public void testBestPathForCorrectlyAveragedCostWhenASpecificAirlineIsConsidered() 
	{
		//Get the best path
		path = graph.getBestPath("X", "Y", FlightCriteria.DISTANCE, "QA");
		//Print out the path
		System.out.println("Direct flight tested for correct averaging when only a certain airline is considered: " + path.toString());
		//Set the equality array
		equalityArray = new String []{"X", "Y"};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(327, path.getPathLength(), DELTA);

	}

	/**
	 * Test that getBestPath returns the correct path with the correctly averaged values when there are multiple legs.
	 * Based on distance
	 */
	@Test
	public void testBestPathForCorrectlyAveragedCostWhenThereAreMultipleLegs() 
	{
		//Get the best path
		path = graph.getBestPath("P", "F", FlightCriteria.DISTANCE);
		//Print out the path
		System.out.println("Path tested for correct averaging when there are multiple legs: " + path.toString());
		//Set the equality array
		equalityArray = new String []{"P", "Q", "R", "F"};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(1392.3333333333335, path.getPathLength(), DELTA);

	}

	/**
	 * Test that getBestPath returns the correct path with the correctly averaged values when there are multiple legs.
	 * Based on distance
	 */
	@Test
	public void testBestPathForCorrectlyProbabilityOfCancellationWhenThereAreMultipleLegs() 
	{
		//Get the best path
		path = graph.getBestPath("P", "F", FlightCriteria.CANCELED);
		//Print out the path
		System.out.println("Path tested for correct averaging of cancelation when there are multiple legs: " + path.toString());
		//Set the equality array
		equalityArray = new String []{"P", "Q", "R", "F"};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(0.3333333333333333, path.getPathLength(), DELTA);
	}


	/**
	 * Test that getBestPath return the correct path with the correctly averaged values.
	 * Based on time, only considering airline "RR" which does not fly this route. Output should be an empty array.
	 */
	@Test
	public void testBestPathForWhenACarrierDoesNotFlyARoute() 
	{
		//Get the best path
		path = graph.getBestPath("X", "Y", FlightCriteria.TIME, "RR");
		//Print out the path
		System.out.println("Direct flight tested when only a certain, nonexistent airline is considered: " + path.toString());
		//Set the equality array
		equalityArray = new String []{""};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(0, path.getPathLength(), DELTA);
	}

	/**
	 * Test that getBestPath returns the correct output and path length when an input is null.
	 */
	@Test
	public void testBestPathForWhenInputIsNull() 
	{
		//Origin is null
		//Get the best path
		path = graph.getBestPath(null, "Y", FlightCriteria.TIME);
		//Print out the path
		System.out.println("Test for when start == null: " + path.toString());
		//Set the equality array
		equalityArray = new String []{"null", "Y"};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(0, path.getPathLength(), DELTA);

		//Destination is null
		//Get the best path
		path = graph.getBestPath("X", null, FlightCriteria.TIME);
		//Print out the path
		System.out.println("Test for when end == null: " + path.toString());
		//Set the equality array
		equalityArray = new String []{"X", "null"};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(0, path.getPathLength(), DELTA);

		//Origin AND destination are null
		//Get the best path
		path = graph.getBestPath(null, null, FlightCriteria.TIME);
		//Print out the path
		System.out.println("Test for when start && end == null: " + path.toString());
		//Set the equality array
		equalityArray = new String []{"null", "null"};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(0, path.getPathLength(), DELTA);
	}


	/**
	 * Test that getBestPath returns the correct path and path length when a path does not exist between two airports.
	 */
	@Test
	public void testBestPathForAVarietyOfBadInputs() 
	{
		//Origin doesn't exist
		//Get the best path
		path = graph.getBestPath("Cat", "Y", FlightCriteria.COST);
		//Set the equality array
		equalityArray = new String []{""};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(0, path.getPathLength(), DELTA);

		//Destination doesn't exist
		//Get the best path
		path = graph.getBestPath("X", "Dog", FlightCriteria.COST);
		//Set the equality array
		equalityArray = new String []{""};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(0, path.getPathLength(), DELTA);

		//Neither origin nor destination exist
		//Get the best path
		path = graph.getBestPath("Cat", "Dog", FlightCriteria.COST);
		//Set the equality array
		equalityArray = new String []{""};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(0, path.getPathLength(), DELTA);

	}

		/**
		 * Test that getBestPath returns the correct path with the correctly averaged values.
		 * Based on delay, only considering airline "QA."
		 */
		@Test
		public void testBestPathForCorrectlyAveragedDelayWhenASpecificAirlineIsConsidered() 
		{
			//Get the best path
			path = graph.getBestPath("X", "Y", FlightCriteria.DELAY, "QA");
			//Print out the path
			System.out.println("Direct flight tested for correct averaging when only a certain airline is considered: " + path.toString());
			//Set the equality array
			equalityArray = new String []{"X", "Y"};
			//Test for equality
			assertArrayEquals(equalityArray, path.toArray());
			//Test for the correct length
			assertEquals(8.25, path.getPathLength(), DELTA);
		}

	/**
	 * Test that getBestPath returns the correct path with the correctly averaged values.
	 * Based on delay and only considering the airline "RR."
	 */
	@Test
	public void testBestPathForCorrectlyAveragedValuesWhenOnlyASpecificAirlineIsConsidered ()
	{
		//Get the best path
		path = graph.getBestPath("Q", "F", FlightCriteria.COST, "RR");
		//Set the equality array
		equalityArray = new String []{"Q", "R", "F"};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(1566, path.getPathLength(), DELTA);
	}
	
	/**
	 * Test that getBestPath returns the correct path with the correctly averaged values, if some of the values are negative.
	 * This test is to verify that negative edge weights are not being considered.
	 * Based on cost and only considering the airline "JB."
	 */
	@Test
	public void testForNegativesWithCarrierCriteria ()
	{
		//Get the best path
		path = graph.getBestPath("D", "N", FlightCriteria.COST, "JB");
		//Set the equality array
		equalityArray = new String []{""};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(0, path.getPathLength(), DELTA);
	}
	
	/**
	 * Test that getBestPath returns the correct path with the correctly averaged values, if some of the values are negative.
	 * This test is to verify that negative edge weights are not being considered.
	 */
	@Test
	public void testForNegatives ()
	{
		//Get the best path
		path = graph.getBestPath("D", "N", FlightCriteria.COST);
		//Set the equality array
		equalityArray = new String []{"D", "V", "N"};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(500, path.getPathLength(), DELTA);
	}
	
	/**
	 * Test that getBestPath returns the correct path with the correctly averaged values, if the flight has a 100% chance of being canceled.
	 * This test is to verify that edges with a 100% chance of cancellation are not being considered.
	 */
	@Test
	public void testForCancellation ()
	{
		//Get the best path
		path = graph.getBestPath("O", "H", FlightCriteria.COST);
		//Set the equality array
		equalityArray = new String []{""};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(0, path.getPathLength(), DELTA);
	}
	
	/**
	 * Test that getBestPath returns the correct path with the correctly averaged values, if the flight has a 100% chance of being canceled.
	 * This test is to verify that edges with a 100% chance of cancellation are not being considered.
	 * This tests that the method still works for flights with multiple legs.
	 */
	@Test
	public void testForCancellation2 ()
	{
		//Get the best path
		path = graph.getBestPath("I", "S", FlightCriteria.COST);
		//Set the equality array
		equalityArray = new String []{"I", "U", "S"};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(1170, path.getPathLength(), DELTA);
	}
	
	/**
	 * Test that getBestPath returns the correct path with the correctly averaged values, if the flight has a 100% chance of being canceled.
	 * This test is to verify that edges with a 100% chance of cancellation are not being considered.
	 * Takes carrier into account.
	 */
	@Test
	public void testForCancellationWithCarrier ()
	{
		//Get the best path
		path = graph.getBestPath("O", "H", FlightCriteria.COST, "ZZ");
		//Set the equality array
		equalityArray = new String []{""};
		//Test for equality
		assertArrayEquals(equalityArray, path.toArray());
		//Test for the correct length
		assertEquals(0, path.getPathLength(), DELTA);
	}
	
	

}