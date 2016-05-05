package assignment13;

import static org.junit.Assert.*; 

import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;

// Assignment 13 Grading Tests

public class Assignment13GradingTests
{

	static NetworkGraph aggregatedDataset, fullDataset, smallDataset;
	static int total, aggreagatePoints, fullDatasetPoints, smallDatasetPoints;
	static DecimalFormat df4, df2, df1;

	@BeforeClass
	public void setUp() throws Exception
	{
		aggregatedDataset = new NetworkGraph("aggregates.csv");
		fullDataset = new NetworkGraph("flights-2015-q3.csv");
		smallDataset = new NetworkGraph("testfile.csv");
		total = 0;
		aggreagatePoints = 0; // total of 20 points
		fullDatasetPoints = 0; // total of 60 points
		smallDatasetPoints = 0; // total of 40 points
		df4 = new DecimalFormat("0.0000");
		df2 = new DecimalFormat("0.00");
		df1 = new DecimalFormat("0.0");

	}


	// String answer = "Path Length: " + "2253.0" + "\nPath: " +
	// "[MOB, DFW, SFO, ACV]";
	/**
	 * This tests the aggreagate and full dataset path's.
	 */
	@Test
	public void testMOBtoACVDistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("MOB", "ACV", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("MOB", "ACV", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(2253.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(1).equals("MOB"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(2).equals("DFW"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(3).equals("SFO"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(4).equals("ACV"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(1).equals("MOB"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(2).equals("DFW"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(3).equals("SFO"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(4).equals("ACV"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: MOB to ACV Distance");
	}

	// String answer = "Path Length: " + "1588.0" + "\nPath: " +
	// "[SFO, SLC, DFW]";
	// CANNOT DO WITH AGGREGATE.CSV
	/**
	 * This test is for the full data set only.
	 */
	@Test
	public void testSFOtoDFWDistance()
	{
		BestPath fullPath = fullDataset.getBestPath("SFO", "DFW", FlightCriteria.DISTANCE, "DL");

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(1588.0);

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(1).equals("SFO"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(2).equals("SLC"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(3).equals("DFW"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: SFO to DFW Distance");
	}

	/**
	 * This tests both the aggregate and full dataset. String answer =
	 * "Path Length: " + "269.2534145" + "\nPath: " + "[MOB, DFW, SLC]";
	 */
	@Test
	public void testMOBtoSLCTime()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("MOB", "SLC", FlightCriteria.TIME);

		BestPath fullPath = fullDataset.getBestPath("MOB", "SLC", FlightCriteria.TIME);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df4.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df4.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df4.format(269.2534145);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(1).equals("MOB"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(2).equals("DFW"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(3).equals("SLC"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(1).equals("MOB"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(2).equals("DFW"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(3).equals("SLC"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: MOB to SLC Time");
	}

	/**
	 * Tests both aggregate and full dataset. String answer = "Path Length: " +
	 * "138.39" + "\nPath: " + "[LAS, LAX]";
	 */
	@Test
	public void testLAStoLAXCost()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("LAS", "LAX", FlightCriteria.COST);

		BestPath fullPath = fullDataset.getBestPath("LAS", "LAX", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df2.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df2.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df2.format(138.39);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(1).equals("LAS"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(2).equals("LAX"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(1).equals("LAS"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(2).equals("LAX"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: LAS to LAX Cost");
	}

	/**
	 * Tests both aggregate and the full dataset. String answer =
	 * "Path Length: " + "2163.0" + "\nPath: " + "[SLC, JFK, MVY]"; Multiple
	 * paths possible.
	 */
	@Test
	public void testSLCtoMVYDistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("SLC", "MVY", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("SLC", "MVY", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(2163.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints += 2;
		}
		if (checkAggregatePath.contains("SLC"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("MVY"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("JFK"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints += 2;
		}
		if (checkFullPath.contains("SLC"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("MVY"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("JFK"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: SLC to MVY Distance");
	}

	/**
	 * Tests both aggregate and full data set. multiple paths possible. String
	 * answer = "Path Length: " + "3035.0" + "\nPath: " +
	 * "[ACK, JFK, SFO, ACV]";
	 */
	@Test
	public void testACKtoACVDistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ACK", "ACV", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("ACK", "ACV", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(3035.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("ACK"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("JFK"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("SFO"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("ACV"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("ACK"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("JFK"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("SFO"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("ACV"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: ACK to ACV Distance");
	}

	/**
	 * Infinitely many paths, only checking cost. Tests both aggregate and full
	 * data set.
	 */
	@Test
	public void testACKtoACVCancelled()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ACK", "ACV", FlightCriteria.CANCELED);

		BestPath fullPath = fullDataset.getBestPath("ACK", "ACV", FlightCriteria.CANCELED);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df4.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df4.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df4.format(0.04591496121);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints += 5;
		}
		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints += 5;
		}

		System.out.println("PASSED: ACK to ACV Cancelled");
	}

	// TODO
	/**
	 * Tests both aggregate and full data set. Multiple path's possible.
	 */
	@Test
	public void testACKtoWYSDistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ACK", "WYS", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("ACK", "WYS", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(2462.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("ACK"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("JFK"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("SLC"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("WYS"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("ACK"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("JFK"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("SLC"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("WYS"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: ACK to WYS Distance");
	}

	/**
	 * tests aggregate and full data set. Infinitely many path's possible.
	 */
	@Test
	public void testACKtoWYSCancelled()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ACK", "WYS", FlightCriteria.CANCELED);

		BestPath fullPath = fullDataset.getBestPath("ACK", "WYS", FlightCriteria.CANCELED);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df4.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df4.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df4.format(0.004132231405);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints += 5;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints += 5;
		}

		System.out.println("PASSED: ACK to WYS Distance");
	}

	/**
	 * Tests both aggregate and full data set. String answer = "Path Length: " +
	 * "1122.0" + "\nPath: " + "[ACV, SFO, SLC, WYS]";
	 */
	@Test
	public void testACVtoWYSDistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ACV", "WYS", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("ACV", "WYS", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(1122.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(1).equals("ACV"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(2).equals("SFO"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(3).equals("SLC"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(4).equals("WYS"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(1).equals("ACV"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(2).equals("SFO"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(3).equals("SLC"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(4).equals("WYS"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: ACV to WYS Distance");
	}

	/**
	 * Tests both aggregate and full data structure.
	 */
	@Test
	public void testACVtoWYSCancelled()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ACV", "WYS", FlightCriteria.CANCELED);

		BestPath fullPath = fullDataset.getBestPath("ACV", "WYS", FlightCriteria.CANCELED);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df4.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df4.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df4.format(0.03899721448);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints += 5;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints += 5;
		}

		System.out.println("PASSED: ACV to WYS Distance");
	}

	/**
	 * Tests both aggregate and full data set. Multiple path's possible.
	 */
	@Test
	public void testMOBtoSLCDistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("MOB", "SLC", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("MOB", "SLC", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(1528.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints += 2;
		}
		if (checkAggregatePath.contains("MOB"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("DFW"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("SLC"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints += 2;
		}
		if (checkFullPath.contains("MOB"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("DFW"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("SLC"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: MOB to SLC Distance");
	}

	/**
	 * Tests both aggregate and full data set. Multiple path's possible.
	 */
	@Test
	public void testMOBtoMVYDistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("MOB", "MVY", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("MOB", "MVY", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(1235.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("MOB"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("ATL"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("JFK"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("MVY"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("MOB"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("ATL"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("JFK"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("MVY"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: MOB to MVY Distance");

	}

	/**
	 * Tests both aggregate and full data set.
	 */
	@Test
	public void testITOtoMOBDistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ITO", "MOB", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("ITO", "MOB", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(4223.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(1).equals("ITO"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(2).equals("LAX"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(3).equals("DFW"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(4).equals("MOB"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(1).equals("ITO"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(2).equals("LAX"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(3).equals("DFW"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(4).equals("MOB"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: ITO to MOB Distance");
	}

	/**
	 * Tests both aggregate and full data structure. String answer =
	 * "Path Length: " + "205.0" + "\nPath: " + "[KOA, OGG, ITO]";
	 */
	@Test
	public void testKOAtoITODistance()
	{

		BestPath aggregatePath = aggregatedDataset.getBestPath("KOA", "ITO", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("KOA", "ITO", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(205.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints += 2;
		}
		if (checkAggregatePath.get(1).equals("KOA"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(2).equals("OGG"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(3).equals("ITO"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints += 2;
		}
		if (checkFullPath.get(1).equals("KOA"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(2).equals("OGG"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(3).equals("ITO"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: KOA to ITO Distance");
	}

	/**
	 * Tests both aggregate and full data structure. Multiple paths possible.
	 */
	@Test
	public void testKOAtoCWADistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("KOA", "CWA", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("KOA", "CWA", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(4425.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints += 2;
		}
		if (checkAggregatePath.contains("KOA"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("ORD"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("CWA"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints += 2;
		}
		if (checkFullPath.contains("KOA"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("ORD"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("SLC"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("CWA"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: KOA to CWA Distance");
	}

	/**
	 * Tests both aggregate and full data structure. String answer =
	 * "Path Length: " + "2993.0" + "\nPath: " + "[ANC, HNL, ITO]";
	 */
	@Test
	public void testANCtoITODistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ANC", "ITO", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("ANC", "ITO", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(2993.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints += 2;
		}
		if (checkAggregatePath.get(1).equals("ANC"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(2).equals("HNL"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(3).equals("ITO"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints += 2;
		}
		if (checkFullPath.get(1).equals("ANC"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(2).equals("HNL"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(3).equals("ITO"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: ANC to ITO Distance");
	}

	/**
	 * Test both aggregate and full data structure. String answer =
	 * "Path Length: " + "3254.0" + "\nPath: " + "[FAI, ANC, HNL, ITO]";
	 */
	@Test
	public void testFAItoITODistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("FAI", "ITO", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("FAI", "ITO", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(3254.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(1).equals("FAI"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(2).equals("ANC"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(3).equals("HNL"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(4).equals("ITO"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(1).equals("FAI"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(2).equals("ANC"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(3).equals("HNL"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(4).equals("ITO"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: FAI to ITO Distance");
	}

	/**
	 * Tests both aggregate and full data set. String answer = "Path Length: " +
	 * "261.0" + "\nPath: " + "[FAI, ANC]";
	 */
	@Test
	public void testFAItoANCDistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("FAI", "ANC", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("FAI", "ANC", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(261.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints += 3;
		}
		if (checkAggregatePath.get(1).equals("FAI"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(2).equals("ANC"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints += 3;
		}
		if (checkFullPath.get(1).equals("FAI"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(2).equals("ANC"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: FAI to ANC Distance");
	}

	/**
	 * Tests aggregated and full data set. Multiple paths possible.
	 */
	@Test
	public void testFAItoGNVDistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("FAI", "GNV", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("FAI", "GNV", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(3673.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("FAI"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("MSP"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("ATL"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("GNV"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("FAI"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("MSP"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("ATL"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("GNV"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: FAI to GNV Distance");
	}

	/**
	 * tests both aggregate and full data set. Multiple paths possible.
	 */
	@Test
	public void testFLLtoFAIDistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("FLL", "FAI", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("FLL", "FAI", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(3953.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints += 2;
		}
		if (checkAggregatePath.contains("FLL"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("MSP"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.contains("FAI"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints += 2;
		}
		if (checkFullPath.contains("FLL"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("MSP"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.contains("FAI"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: FLL to FAI Distance");
	}

	// TODO
	@Test
	public void testPWMtoMVYDistance()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("PWM", "MVY", FlightCriteria.DISTANCE);

		BestPath fullPath = fullDataset.getBestPath("PWM", "MVY", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(446.0);

		if (aggAnswer.equals(correctCost))
		{
			aggreagatePoints += 2;
		}
		if (checkAggregatePath.get(1).equals("PWM"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(2).equals("JFK"))
		{
			aggreagatePoints++;
		}
		if (checkAggregatePath.get(3).equals("MVY"))
		{
			aggreagatePoints++;
		}

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints += 2;
		}
		if (checkFullPath.get(1).equals("PWM"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(2).equals("JFK"))
		{
			fullDatasetPoints++;
		}
		if (checkFullPath.get(3).equals("MVY"))
		{
			fullDatasetPoints++;
		}

		System.out.println("PASSED: PWM to MVY Distance");
	}

	/**
	 * This tests only the full data set.
	 * Worth 10 points
	 */
	@Test
	public void testSLCtoMVYDistanceWithB6Carrier()
	{
		BestPath fullPath = fullDataset.getBestPath("SLC", "MVY", FlightCriteria.DISTANCE, "B6");

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(2163.0);

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints += 4;
		}
		if (checkFullPath.get(1).equals("SLC"))
		{
			fullDatasetPoints += 2;
		}
		if (checkFullPath.get(2).equals("JFK"))
		{
			fullDatasetPoints += 2;
		}
		if (checkFullPath.get(3).equals("MVY"))
		{
			fullDatasetPoints += 2;
		}

		System.out.println("PASSED: SCL to MVY Distance with B6 Carrier");
		
	}


	/**
	 * This test is only for the full data set. 
	 */
	@Test
	public void testSLCtoMVYDistanceWithDLCarrier()
	{
		BestPath fullPath = fullDataset.getBestPath("SLC", "MVY", FlightCriteria.DISTANCE, "DL");

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(0.0);

		if (fullAnswer.equals(correctCost))
		{
			fullDatasetPoints += 4;
		}
		if (checkFullPath.size() == 2)
		{
			fullDatasetPoints += 2;
		}

		System.out.println("PASSED: SLC to MVY Distance with DL Carrier");
	}

	// The following tests are only to be 10 points of the final grade

	/**
	 * --> these are edges for avoiding negative cycles! together, no more than 5% of grade (10 pts)
	 * 		String answer = "Path Length: " + "288.31" + "\nPath: " + "[IMT, MSP, RHI]";
	 */
	@Test
	public void testIMTtoRHICost()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("IMT", "RHI", FlightCriteria.COST);

		BestPath fullPath = fullDataset.getBestPath("IMT", "RHI", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df2.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df2.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df2.format(288.31);

		int count1 = 0;
		int count2 = 0;
		
		if (aggAnswer.equals(correctCost))
		{
			count1++;
		}
		if (checkAggregatePath.get(1).equals("IMT"))
		{
			count1++;
		}
		if (checkAggregatePath.get(2).equals("MSP"))
		{
			count1++;
		}
		if (checkAggregatePath.get(3).equals("RHI"))
		{
			count1++;
		}

		if (fullAnswer.equals(correctCost))
		{
			count2++;
		}
		if (checkFullPath.get(1).equals("IMT"))
		{
			count2++;
		}
		if (checkFullPath.get(2).equals("MSP"))
		{
			count2++;
		}
		if (checkFullPath.get(3).equals("RHI"))
		{
			count2++;
		}

		if (count1 == 4)
		{
			aggreagatePoints++;
		}
		if (count2 == 4)
		{
			fullDatasetPoints++;
		}
		
		System.out.println("PASSED: IMT to RHI Cost");
	}

	/**
	 * --> these are edges for avoiding negative cycles! together, no more than 5% of grade (10 pts)
	 * * CDV YAK COST ['CDV', 'ANC','JNU', 'YAK'] 521.5
	 */
	@Test
	public void testCDVtoYAKCost()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("CDV", "YAK", FlightCriteria.COST);

		BestPath fullPath = fullDataset.getBestPath("CDV", "YAK", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(521.5);

		int count1 = 0;
		int count2 = 0;
		
		if (aggAnswer.equals(correctCost))
		{
			count1++;
		}
		if (checkAggregatePath.get(1).equals("CDV"))
		{
			count1++;
		}
		if (checkAggregatePath.get(2).equals("ANC"))
		{
			count1++;
		}
		if (checkAggregatePath.get(3).equals("JNU"))
		{
			count1++;
		}
		if (checkAggregatePath.get(4).equals("YAK"))
		{
			count1++;
		}

		if (fullAnswer.equals(correctCost))
		{
			count2++;
		}
		if (checkFullPath.get(1).equals("CDV"))
		{
			count2++;
		}
		if (checkFullPath.get(2).equals("ANC"))
		{
			count2++;
		}
		if (checkFullPath.get(3).equals("JNU"))
		{
			count2++;
		}
		if (checkFullPath.get(4).equals("YAK"))
		{
			count2++;
		}
		
		if (count1 == 4)
		{
			aggreagatePoints++;
		}
		if (count2 == 4)
		{
			fullDatasetPoints++;
		}
		
		System.out.println("PASSED: CDV to YAK Cost");
	}


	/**
	 * --> these are edges for avoiding negative cycles! together, no more than 5% of grade (10 pts)
	 * FCA MSO COST ['FCA', 'SLC', 'MSO'] 577
	 */
	@Test
	public void testFCAtoMSOCost()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("FCA", "MSO", FlightCriteria.COST);

		BestPath fullPath = fullDataset.getBestPath("FCA", "MSO", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(577.0);

		int count1 = 0;
		int count2 = 0;
		
		if (aggAnswer.equals(correctCost))
		{
			count1++;
		}
		if (checkAggregatePath.get(1).equals("FCA"))
		{
			count1++;
		}
		if (checkAggregatePath.get(2).equals("SLC"))
		{
			count1++;
		}
		if (checkAggregatePath.get(3).equals("MSO"))
		{
			count1++;
		}

		if (fullAnswer.equals(correctCost))
		{
			count2++;
		}
		if (checkFullPath.get(1).equals("FCA"))
		{
			count2++;
		}
		if (checkFullPath.get(2).equals("SLC"))
		{
			count2++;
		}
		if (checkFullPath.get(3).equals("MSO"))
		{
			count2++;
		}
		
		if (count1 == 4)
		{
			aggreagatePoints++;
		}
		if (count2 == 4)
		{
			fullDatasetPoints++;
		}
		
		System.out.println("PASSED: FCA to MSO Cost");
	}

	/**
	 * --> these are edges for avoiding negative cycles! together, no more than 5% of grade (10 pts)
	 * MEI PIB COST [] 0
	 */
	@Test
	public void testMEItoPIBCost()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("MEI", "PIB", FlightCriteria.COST);

		BestPath fullPath = fullDataset.getBestPath("MEI", "PIB", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(0.0);

		int count1 = 0;
		int count2 = 0;
		
		if (aggAnswer.equals(correctCost))
		{
			count1++;
		}

		if (fullAnswer.equals(correctCost))
		{
			count2++;
		}
		
		if (count1 == 1)
		{
			aggreagatePoints++;
		}
		if (count2 == 1)
		{
			fullDatasetPoints++;
		}
		
		System.out.println("PASSED: MEI to PIB Cost");
	}


	/**
	 * --> these are edges for avoiding negative cycles! together, no more than 5% of grade (10 pts)
	 * INL HIB COST ['INL', 'MSP', 'HIB'] 311.33
	 */
	@Test
	public void testINLtoHIBCost()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("INL", "HIB", FlightCriteria.COST);

		BestPath fullPath = fullDataset.getBestPath("INL", "HIB", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df2.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df2.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df2.format(311.33);

		int count1 = 0;
		int count2 = 0;
		
		if (aggAnswer.equals(correctCost))
		{
			count1++;
		}
		if (checkAggregatePath.get(1).equals("INL"))
		{
			count1++;
		}
		if (checkAggregatePath.get(2).equals("MSP"))
		{
			count1++;
		}
		if (checkAggregatePath.get(3).equals("HIB"))
		{
			count1++;
		}

		if (fullAnswer.equals(correctCost))
		{
			count2++;
		}
		if (checkFullPath.get(1).equals("INL"))
		{
			count2++;
		}
		if (checkFullPath.get(2).equals("MSP"))
		{
			count2++;
		}
		if (checkFullPath.get(3).equals("HIB"))
		{
			count2++;
		}
		
		if (count1 == 4)
		{
			aggreagatePoints++;
		}
		if (count2 == 4)
		{
			fullDatasetPoints++;
		}
		
		System.out.println("PASSED: INL to HIB Cost");
	}


	/**
	 * --> these are edges for avoiding negative cycles! together, no more than 5% of grade (10 pts)
	 * SAV BQK COST ['SAV','ATL', 'BQK'] 430.65
	 */
	@Test
	public void testSAVtoBQKCost()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("SAV", "BQK", FlightCriteria.COST);

		BestPath fullPath = fullDataset.getBestPath("SAV", "BQK", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df2.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df2.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df2.format(430.65);

		int count1 = 0;
		int count2 = 0;
		
		if (aggAnswer.equals(correctCost))
		{
			count1++;
		}
		if (checkAggregatePath.get(1).equals("SAV"))
		{
			count1++;
		}
		if (checkAggregatePath.get(2).equals("ATL"))
		{
			count1++;
		}
		if (checkAggregatePath.get(3).equals("BQK"))
		{
			count1++;
		}

		if (fullAnswer.equals(correctCost))
		{
			count2++;
		}
		if (checkFullPath.get(1).equals("SAV"))
		{
			count2++;
		}
		if (checkFullPath.get(2).equals("ATL"))
		{
			count2++;
		}
		if (checkFullPath.get(3).equals("BQK"))
		{
			count2++;
		}
		
		if (count1 == 4)
		{
			aggreagatePoints++;
		}
		if (count2 == 4)
		{
			fullDatasetPoints++;
		}
		
		System.out.println("PASSED: SAV to BQK Cost");

	}

	/**
	 * --> these are edges for avoiding negative cycles! together, no more than 5% of grade (10 pts)
	 * HIB INL COST ['HIB', 'MSP', 'INL'] 271.52
	 */
	@Test
	public void testHIBtoINLCost()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("HIB", "INL", FlightCriteria.COST);

		BestPath fullPath = fullDataset.getBestPath("HIB", "INL", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df2.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df2.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df2.format(271.52);

		int count1 = 0;
		int count2 = 0;
		
		if (aggAnswer.equals(correctCost))
		{
			count1++;
		}
		if (checkAggregatePath.get(1).equals("HIB"))
		{
			count1++;
		}
		if (checkAggregatePath.get(2).equals("MSP"))
		{
			count1++;
		}
		if (checkAggregatePath.get(3).equals("INL"))
		{
			count1++;
		}

		if (fullAnswer.equals(correctCost))
		{
			count2++;
		}
		if (checkFullPath.get(1).equals("HIB"))
		{
			count2++;
		}
		if (checkFullPath.get(2).equals("MSP"))
		{
			count2++;
		}
		if (checkFullPath.get(3).equals("INL"))
		{
			count2++;
		}
		
		if (count1 == 4)
		{
			aggreagatePoints++;
		}
		if (count2 == 4)
		{
			fullDatasetPoints++;
		}
		
		System.out.println("PASSED: HIB to INL Cost");
	}


	/**
	 * --> these are edges for avoiding negative cycles! together, no more than 5% of grade (10 pts)
	 * RHI IMT COST ['RHI', 'MSP', 'IMT'] 348.5
	 */
	@Test
	public void testRHItoIMTCost()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("RHI", "IMT", FlightCriteria.COST);

		BestPath fullPath = fullDataset.getBestPath("RHI", "IMT", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(348.5);

		int count1 = 0;
		int count2 = 0;
		
		if (aggAnswer.equals(correctCost))
		{
			count1++;
		}
		if (checkAggregatePath.get(1).equals("RHI"))
		{
			count1++;
		}
		if (checkAggregatePath.get(2).equals("MSP"))
		{
			count1++;
		}
		if (checkAggregatePath.get(3).equals("IMT"))
		{
			count1++;
		}

		if (fullAnswer.equals(correctCost))
		{
			count2++;
		}
		if (checkFullPath.get(1).equals("RHI"))
		{
			count2++;
		}
		if (checkFullPath.get(2).equals("MSP"))
		{
			count2++;
		}
		if (checkFullPath.get(3).equals("IMT"))
		{
			count2++;
		}
		
		if (count1 == 4)
		{
			aggreagatePoints++;
		}
		if (count2 == 4)
		{
			fullDatasetPoints++;
		}
		
		System.out.println("PASSED: RHI to IMT Cost");
	}


	/**
	 * --> these are edges for avoiding negative cycles! together, no more than 5% of grade (10 pts)
	 * DVL JMS COST ['DVL', 'DEN', 'JMS'] 340.93
	 */
	@Test
	public void testDVLtoJMSCost()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("DVL", "JMS", FlightCriteria.COST);

		BestPath fullPath = fullDataset.getBestPath("DVL", "JMS", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(340.93);

		int count1 = 0;
		int count2 = 0;
		
		if (aggAnswer.equals(correctCost))
		{
			count1++;
		}
		if (checkAggregatePath.get(1).equals("DVL"))
		{
			count1++;
		}
		if (checkAggregatePath.get(2).equals("DEN"))
		{
			count1++;
		}
		if (checkAggregatePath.get(3).equals("JMS"))
		{
			count1++;
		}

		if (fullAnswer.equals(correctCost))
		{
			count2++;
		}
		if (checkFullPath.get(1).equals("DVL"))
		{
			count2++;
		}
		if (checkFullPath.get(2).equals("DEN"))
		{
			count2++;
		}
		if (checkFullPath.get(3).equals("JMS"))
		{
			count2++;
		}
		
		if (count1 == 4)
		{
			aggreagatePoints++;
		}
		if (count2 == 4)
		{
			fullDatasetPoints++;
		}
		
		System.out.println("PASSED: DVL to JMS Cost");
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
