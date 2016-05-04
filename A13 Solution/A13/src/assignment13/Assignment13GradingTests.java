package assignment13;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;

// Assignment 13 Grading Tests

public class Assignment13GradingTests
{

	NetworkGraph alreadyAggregated, fullDataset, smallDataset;
	int total, aggreagatePoints, fullDatasetPoints, smallDatasetPoints;
	DecimalFormat df4, df2;

	@Before
	public void setUp() throws Exception
	{
		alreadyAggregated = new NetworkGraph("aggregates.csv");
		fullDataset = new NetworkGraph("flights-2015-q3.csv");
		smallDataset = new NetworkGraph("testfile.csv");
		total = 0;
		aggreagatePoints = 0; // total of 20 points
		fullDatasetPoints = 0; // total of 60 points
		smallDatasetPoints = 0; // total of 40 points
		df4 = new DecimalFormat("0.0000");
		df2 = new DecimalFormat("0.00");

	}

	@After
	public void tearDown() throws Exception
	{
	}

	@Test
	public void testMOBtoACVDistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("MOB", "ACV", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "2253.0" + "\nPath: " + "[MOB, DFW, SFO, ACV]";
		
		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	@Test
	public void testSFOtoDFWDistance()
	{
		BestPath bestPath = fullDataset.getBestPath("SFO", "DFW", FlightCriteria.DISTANCE, "DL");

		String answer = "Path Length: " + "1588.0" + "\nPath: " + "[SFO, SLC, DFW]";
		
		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	@Test
	public void testMOBtoSLCTime()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("MOB", "SLC", FlightCriteria.TIME);

		String answer = "Path Length: " + "269.2534145" + "\nPath: " + "[MOB, DFW, SLC]";
		
		if (!bestPath.toString().equals(answer))
		{
			ArrayList<String> checkPath = breakUpPath(bestPath);
			String n1 = df4.format(Double.parseDouble(checkPath.get(0)));
			String n2 = df4.format(269.2534145);
			
			if (!bestPath.toString().equals(answer))
			{
				int count = 0;
				if (!bestPath.toString().equals(answer))
				{
					if(!n1.equals(n2))
					{
						count++;
						// take away from total points
					}
					if(!checkPath.get(1).equals("IMT"))
					{
						count++;
						// take away some total points
					}
					if(!checkPath.get(2).equals("MSP"))
					{
						count++;
					}
					if(!checkPath.get(3).equals("RHI"))
					{
						count++;
					}
					if(count == 4)
					{
						fail();
					}
				}
			}
		}
	}

	@Test
	public void testLAStoLAXCost()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("LAS", "LAX", FlightCriteria.COST);

		String answer = "Path Length: " + "138.39" + "\nPath: " + "[LAS, LAX]";
		
		if (!bestPath.toString().equals(answer))
		{
			ArrayList<String> checkPath = breakUpPath(bestPath);
			String n1 = df2.format(Double.parseDouble(checkPath.get(0)));
			String n2 = df2.format(138.39);
			
			int count = 0;
			if (!bestPath.toString().equals(answer))
			{
				if(!n1.equals(n2))
				{
					count++;
					// take away from total points
				}
				if(!checkPath.get(1).equals("IMT"))
				{
					count++;
					// take away some total points
				}
				if(!checkPath.get(2).equals("MSP"))
				{
					count++;
				}
				if(count == 3)
				{
					fail();
				}
			}
		}
	}

	@Test
	public void testSLCtoMVYDistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("SLC", "MVY", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "2163.0" + "\nPath: " + "[SLC, JFK, MVY]";
		
		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	@Test
	public void testACKtoACVDistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("ACK", "ACV", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "3035.0" + "\nPath: " + "[ACK, JFK, SFO, ACV]";
		
		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	@Test
	public void testACKtoACVCancelled()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("ACK", "ACV", FlightCriteria.CANCELED);
		ArrayList<String> checkPath = breakUpPath(bestPath);
		String n1 = df4.format(Double.parseDouble(checkPath.get(0)));
		String n2 = df4.format(0.04591496121);
		assertTrue(n1.equals(n2));
	}

	@Test
	public void testACKtoWYSDistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("ACK", "WYS", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "2462.0" + "\nPath: " + "[ACK, JFK, SLC, WYS]";
		
		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	// only check cost
	@Test
	public void testACKtoWYSCancelled()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("ACK", "WYS", FlightCriteria.CANCELED);
		ArrayList<String> checkPath = breakUpPath(bestPath);
		String n1 = df4.format(Double.parseDouble(checkPath.get(0)));
		String n2 = df4.format(0.004132231405);
		assertTrue(n1.equals(n2));
	}

	@Test
	public void testACVtoWYSDistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("ACV", "WYS", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "1122.0" + "\nPath: " + "[ACV, SFO, SLC, WYS]";
		
		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	@Test
	public void testACVtoWYSCancelled()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("ACV", "WYS", FlightCriteria.CANCELED);
		ArrayList<String> checkPath = breakUpPath(bestPath);
		String n1 = df4.format(Double.parseDouble(checkPath.get(0)));
		String n2 = df4.format(0.03899721448);
		assertTrue(n1.equals(n2));
	}

	@Test
	public void testMOBtoSLCDistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("MOB", "SLC", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "1528.0" + "\nPath: " + "[MOB, DFW, GJT, SLC]";
		String answer2 = "Path Length: " + "1528.0" + "\nPath: " + "[MOB, DFW, SLC]";

		if (!bestPath.toString().equals(answer) && !bestPath.toString().equals(answer2))
		{
			fail();
		}
	}

	@Test
	public void testMOBtoMVYDistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("MOB", "MVY", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "1235.0" + "\nPath: " + "[MOB, ATL, DCA, JFK, MVY]";
		String answer2 = "Path Length: " + "1235.0" + "\nPath: " + "[MOB, ATL, JFK, MVY]";

		if (!bestPath.toString().equals(answer) && !bestPath.toString().equals(answer2))
		{
			fail();
		}
	}

	@Test
	public void testITOtoMOBDistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("ITO", "MOB", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "4223.0" + "\nPath: " + "[ITO, LAX, DFW, MOB]";

		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	@Test
	public void testKOAtoITODistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("KOA", "ITO", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "205.0" + "\nPath: " + "[KOA, OGG, ITO]";

		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	@Test
	public void testKOAtoCWADistance()
	{
		BestPath bestPath = fullDataset.getBestPath("KOA", "CWA", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "4425.0" + "\nPath: " + "[KOA, SFO, ORD, CWA]";
		String answer2 = "Path Length: " + "4425.0" + "\nPath: " + "[KOA, SJC, ORD, CWA]";

		if (!bestPath.toString().equals(answer) && !bestPath.toString().equals(answer2))
		{
			fail();
		}
	}

	@Test
	public void testANCtoITODistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("ANC", "ITO", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "2993.0" + "\nPath: " + "[ANC, HNL, ITO]";

		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	@Test
	public void testFAItoITODistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("FAI", "ITO", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "3254.0" + "\nPath: " + "[FAI, ANC, HNL, ITO]";

		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	@Test
	public void testFAItoANCDistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("FAI", "ANC", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "261.0" + "\nPath: " + "[FAI, ANC]";

		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	@Test
	public void testFAItoGNVDistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("FAI", "GNV", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "3673.0" + "\nPath: " + "[FAI, MSP, RST, ATL, GNV]";
		String answer2 = "Path Length: " + "3673.0" + "\nPath: " + "[FAI, MSP, MLI, ATL, GNV]";
		String answer3 = "Path Length: " + "3673.0" + "\nPath: " + "[FAI, MSP, ATL, GNV]";

		if (!bestPath.toString().equals(answer) && !bestPath.toString().equals(answer2) && !bestPath.toString().equals(answer3))
		{
			fail();
		}
	}

	@Test
	public void testFLLtoFAIDistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("FLL", "FAI", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "3953.0" + "\nPath: " + "[FLL, MCO, MSP, FAI]";
		String answer2 = "Path Length: " + "3953.0" + "\nPath: " + "[FLL, MSP, FAI]";

		if (!bestPath.toString().equals(answer) && !bestPath.toString().equals(answer2))
		{
			fail();
		}
	}

	@Test
	public void testPWMtoMVYDistance()
	{
		BestPath bestPath = alreadyAggregated.getBestPath("PWM", "MVY", FlightCriteria.DISTANCE);

		String answer = "Path Length: " + "446.0" + "\nPath: " + "[PWM, JFK, MVY]";

		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	@Test
	public void testSLCtoMVYDistanceWithB6Carrier()
	{
		BestPath bestPath = fullDataset.getBestPath("SLC", "MVY", FlightCriteria.DISTANCE, "B6");

		String answer = "Path Length: " + "2163.0" + "\nPath: " + "[SLC, JFK, MVY]";

		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	@Test
	public void testSLCtoMVYDistanceWithDLCarrier()
	{
		BestPath bestPath = fullDataset.getBestPath("SLC", "MVY", FlightCriteria.DISTANCE, "DL");

		String answer = "Path Length: " + "0.0" + "\nPath: " + "[]";

		if (!bestPath.toString().equals(answer))
		{
			fail();
		}
	}

	// The following tests are only to be 10 points of the final grade

	@Test
	public void testIMTtoRHICost()
	{
		BestPath bestPath = fullDataset.getBestPath("IMT", "RHI", FlightCriteria.COST);
		
		ArrayList<String> checkPath = breakUpPath(bestPath);

		String n1 = df2.format(Double.parseDouble(checkPath.get(0)));
		String n2 = df2.format(288.31);
		
		String answer = "Path Length: " + "288.31" + "\nPath: " + "[IMT, MSP, RHI]";

		int count = 0;
		if (!bestPath.toString().equals(answer))
		{
			if(!n1.equals(n2))
			{
				count++;
				// take away from total points
			}
			if(!checkPath.get(1).equals("IMT"))
			{
				count++;
				// take away some total points
			}
			if(!checkPath.get(2).equals("MSP"))
			{
				count++;
			}
			if(!checkPath.get(3).equals("RHI"))
			{
				count++;
			}
			if(count == 4)
			{
				fail();
			}
		}
	}

	/*
	 * IMT RHI COST ['IMT', 'MSP', 'RHI'] 288.31 CDV YAK COST ['CDV', 'ANC',
	 * 'JNU', 'YAK'] 521.5 FCA MSO COST ['FCA', 'SLC', 'MSO'] 577 MEI PIB COST
	 * [] 0 INL HIB COST ['INL', 'MSP', 'HIB'] 311.33 SAV BQK COST ['SAV',
	 * 'ATL', 'BQK'] 430.65 HIB INL COST ['HIB', 'MSP', 'INL'] 271.52 RHI IMT
	 * COST ['RHI', 'MSP', 'IMT'] 348.5 DVL JMS COST ['DVL', 'DEN', 'JMS']
	 * 340.93
	 */

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
		tempPath[tempPath.length - 1] = tempPath[tempPath.length - 1].substring(0, tempPath[tempPath.length - 1].length() - 1);

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
