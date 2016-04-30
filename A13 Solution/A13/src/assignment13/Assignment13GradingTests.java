package assignment13;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

// Assignment 13 Grading Tests

public class Assignment13GradingTests
{

	NetworkGraph path;

	@Before
	public void setUp() throws Exception
	{
		path = new NetworkGraph("flights-2015-q3.csv");
	}

	@Test
	public void test2()
	{
		BestPath bestPath = path.getBestPath("MOB", "ACV", FlightCriteria.DISTANCE);
		System.out.println(bestPath);
		String answer = "Path Length: " + "2253.0" + "\nPath: " + "[MOB, DFW, SFO, ACV]";
		assertTrue(bestPath.toString().equals(answer));
	}

	@Test
	public void test3()
	{
		BestPath bestPath = path.getBestPath("SFO", "DFW", FlightCriteria.DISTANCE, "DL");
		System.out.println(bestPath);
		String answer = "Path Length: " + "1588.0" + "\nPath: " + "[SFO, SLC, DFW]";
		assertTrue(bestPath.toString().equals(answer));
	}

	@Test
	public void test4()
	{
		BestPath bestPath = path.getBestPath("MOB", "SLC", FlightCriteria.TIME);
		System.out.println(bestPath);
		String answer = "Path Length: " + "269.2534145" + "\nPath: " + "[MOB, DFW, SLC]";
		assertTrue(bestPath.toString().equals(answer));
	}

	@Test
	public void test5()
	{
		BestPath bestPath = path.getBestPath("LAS", "LAX", FlightCriteria.COST);
		System.out.println(bestPath);
		String answer = "Path Length: " + "138.39" + "\nPath: " + "[LAS, LAX]";
		assertTrue(bestPath.toString().equals(answer));
	}

	@Test
	public void test6()
	{
		BestPath bestPath = path.getBestPath("SLC", "MVY", FlightCriteria.DISTANCE);
		System.out.println(bestPath);
		String answer = "Path Length: " + "2163.0" + "\nPath: " + "[SLC, JFK, MVY]";
		assertTrue(bestPath.toString().equals(answer));
	}

	@Test
	public void test7()
	{
		BestPath bestPath = path.getBestPath("ACK", "ACV", FlightCriteria.DISTANCE);
		System.out.println(bestPath);
		String answer = "Path Length: " + "3035.0" + "\nPath: " + "[ACK, JFK, SFO, ACV]";
		assertTrue(bestPath.toString().equals(answer));
	}

	@Test
	public void test8()
	{
		BestPath bestPath = path.getBestPath("ACK", "ACV", FlightCriteria.CANCELED);
		System.out.println(bestPath);
		String answer1 = "Path Length: " + "3035.0" + "\nPath: " + "[ACK, JFK, ATL, ANC, SFO, ACV]";
		String answer2 = "Path Length: " + "3035.0" + "\nPath: " + "[ACK, JFK, HNL, ANC, SFO, ACV]";
		String answer3 = "Path Length: " + "3035.0" + "\nPath: " + "[ACK, JFK, HNL, DFW, MKE, SFO, ACV]";
		
		// students other solution
		String answer4 = "Path Length: " + "0.045914961209972606" + "\nPath: " + "[ACK, JFK, HOU, IND, SFO, ACV]";

		assertTrue(bestPath.toString().equals(answer1) || bestPath.toString().equals(answer2) || bestPath.toString().equals(answer3)
				|| bestPath.toString().equals(answer4));
	}

	
	@Test
	public void test9()
	{
		BestPath bestPath = path.getBestPath("ACK", "WYS", FlightCriteria.DISTANCE);
		System.out.println(bestPath);
		String answer = "Path Length: " + "2462.0" + "\nPath: " + "[ACK, JFK, SLC, WYS]";
		assertTrue(bestPath.toString().equals(answer));
	}
	
	@Test
	public void test10()
	{
		BestPath bestPath = path.getBestPath("ACK", "WYS", FlightCriteria.CANCELED);
		System.out.println(bestPath);
		String answer = "Path Length: " + "0.004132231405" + "\nPath: " + "[ACK, JFK, SLC, WYS]";
		assertTrue(bestPath.toString().equals(answer));
	}
	/**
	 * This method itemizes each piece of the given path in an ArrayList of
	 * Strings.
	 * 
	 * @param thePath
	 *            is the best path produced by a Network Graph search
	 * @return An ArrayList of Strings. The first element in the list is the
	 *         cost of the path, the remainder of the list is the actual path.
	 * 
	 *         For example: [2253.0, MOB, DFW, SFO, AC]
	 */
	public ArrayList<String> breakUpPath(BestPath thePath)
	{
		String[] tempPath = thePath.toString().split("\\s+");

		// remove the "[" and "]" from the first and last strings
		tempPath[4] = tempPath[4].substring(1);
		tempPath[tempPath.length - 1] = tempPath[tempPath.length - 1].substring(0, tempPath[tempPath.length - 1].length() - 2);

		ArrayList<String> bestPath = new ArrayList<String>();

		// add the cost of the path to the first thing in the list
		bestPath.add(tempPath[2]);

		// add the remaining pieces of the path to the list
		for (int i = 4; i < tempPath.length; i++)
		{
			// remove the comma from each element
			if (i != tempPath.length - 1)
			{
				bestPath.add(tempPath[i].substring(0, tempPath[i].length() - 1));
			} else
			{
				bestPath.add(tempPath[i]);
			}
		}
		return bestPath;
	}
}
