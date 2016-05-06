package assignment13;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.ArrayList;

import autograderutils.annotations.Autograder;

// Assignment 13 Grading Tests

public class Assignment13GradingTests
{

	static NetworkGraph aggregatedDataset, fullDataset, smallDataset;
	static int total, aggregatePoints, fullDatasetPoints, smallDatasetPoints;
	static DecimalFormat df4, df2, df1;

	@BeforeClass
	public static void setUp() throws Exception
	{
		aggregatedDataset = new NetworkGraph("aggregates.csv");
		fullDataset = new NetworkGraph("flights-2015-q3.csv");
		smallDataset = new NetworkGraph("testfile.csv");
		total = 0;
		aggregatePoints = 0; // total of 20 points
		fullDatasetPoints = 0; // total of 60 points
		smallDatasetPoints = 0; // total of 40 points
		df4 = new DecimalFormat("0.0000");
		df2 = new DecimalFormat("0.00");
		df1 = new DecimalFormat("0.0");

	}

	@AfterClass
	public static void tearDown() throws Exception
	{
		System.out.println(aggregatePoints + "/20");
		System.out.println(fullDatasetPoints +"/60");
		System.out.println(smallDatasetPoints);
	}

	// String answer = "Path Length: " + "2253.0" + "\nPath: " +
	// "[MOB, DFW, SFO, ACV]";
	/**
	 * This tests the aggreagate and full dataset path's.
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testMOBtoACVDistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("MOB", "ACV", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(2253.0);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("MOB"));
		assertTrue(checkAggregatePath.get(2).equals("DFW"));
		assertTrue(checkAggregatePath.get(3).equals("SFO"));
		assertTrue(checkAggregatePath.get(4).equals("ACV"));

		aggregatePoints += 1;
	}

	// String answer = "Path Length: " + "2253.0" + "\nPath: " +
	// "[MOB, DFW, SFO, ACV]";
	/**
	 * This tests the aggreagate and full dataset path's.
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testMOBtoACVDistanceFullData()
	{
		BestPath fullPath = fullDataset.getBestPath("MOB", "ACV", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(2253.0);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("MOB"));
		assertTrue(checkFullPath.get(2).equals("DFW"));
		assertTrue(checkFullPath.get(3).equals("SFO"));
		assertTrue(checkFullPath.get(4).equals("ACV"));

		fullDatasetPoints += 2;
	}

	/**
	 * This test is for the full data set only. String answer = "Path Length: "
	 * + "1588.0" + "\nPath: " + "[SFO, SLC, DFW]"; CANNOT DO WITH AGGREGATE.CSV
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testSFOtoDFWDistanceFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("SFO", "DFW", FlightCriteria.DISTANCE, "DL");

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(1588.0);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("SFO"));
		assertTrue(checkFullPath.get(2).equals("SLC"));
		assertTrue(checkFullPath.get(3).equals("DFW"));

		fullDatasetPoints += 2;
	}

	/**
	 * This tests both the aggregate and full dataset. String answer =
	 * "Path Length: " + "269.2534145" + "\nPath: " + "[MOB, DFW, SLC]";
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testMOBtoSLCTimeAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("MOB", "SLC", FlightCriteria.TIME);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df4.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df4.format(269.2534145);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("MOB"));
		assertTrue(checkAggregatePath.get(2).equals("DFW"));
		assertTrue(checkAggregatePath.get(3).equals("SLC"));

		aggregatePoints += 1;
	}

	/**
	 * This tests both the aggregate and full dataset. String answer =
	 * "Path Length: " + "269.2534145" + "\nPath: " + "[MOB, DFW, SLC]";
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testMOBtoSLCTimeFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("MOB", "SLC", FlightCriteria.TIME);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df4.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df4.format(269.2534145);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("MOB"));
		assertTrue(checkFullPath.get(2).equals("DFW"));
		assertTrue(checkFullPath.get(3).equals("SLC"));

		fullDatasetPoints += 2;
	}

	/**
	 * Tests both aggregate and full dataset. String answer = "Path Length: " +
	 * "138.39" + "\nPath: " + "[LAS, LAX]";
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testLAStoLAXCostAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("LAS", "LAX", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df2.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df2.format(138.39);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("LAS"));
		assertTrue(checkAggregatePath.get(2).equals("LAX"));

		aggregatePoints += 1;
	}

	/**
	 * Tests both aggregate and full dataset. String answer = "Path Length: " +
	 * "138.39" + "\nPath: " + "[LAS, LAX]";
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testLAStoLAXCostFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("LAS", "LAX", FlightCriteria.COST);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df2.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df2.format(138.39);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("LAS"));
		assertTrue(checkFullPath.get(2).equals("LAX"));

		fullDatasetPoints += 2;
	}

	/**
	 * Tests both aggregate and the full dataset. String answer =
	 * "Path Length: " + "2163.0" + "\nPath: " + "[SLC, JFK, MVY]"; Multiple
	 * paths possible.
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testSLCtoMVYDistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("SLC", "MVY", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(2163.0);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("SLC"));
		assertTrue(checkAggregatePath.get(2).equals("JFK"));
		assertTrue(checkAggregatePath.get(3).equals("MVY"));

		aggregatePoints += 1;
	}

	/**
	 * Tests both aggregate and the full dataset. String answer =
	 * "Path Length: " + "2163.0" + "\nPath: " + "[SLC, JFK, MVY]"; Multiple
	 * paths possible.
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testSLCtoMVYDistanceFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("SLC", "MVY", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(2163.0);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("SLC"));
		assertTrue(checkFullPath.get(2).equals("JFK"));
		assertTrue(checkFullPath.get(3).equals("MVY"));

		fullDatasetPoints += 2;
	}

	/**
	 * Tests both aggregate and full data set. multiple paths possible. String
	 * answer = "Path Length: " + "3035.0" + "\nPath: " +
	 * "[ACK, JFK, SFO, ACV]";
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testACKtoACVDistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ACK", "ACV", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(3035.0);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("ACK"));
		assertTrue(checkAggregatePath.get(2).equals("JFK"));
		assertTrue(checkAggregatePath.get(3).equals("SFO"));
		assertTrue(checkAggregatePath.get(4).equals("ACV"));

		aggregatePoints += 1;
	}

	/**
	 * Tests both aggregate and full data set. multiple paths possible. String
	 * answer = "Path Length: " + "3035.0" + "\nPath: " +
	 * "[ACK, JFK, SFO, ACV]";
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testACKtoACVDistanceFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("ACK", "ACV", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(3035.0);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("ACK"));
		assertTrue(checkFullPath.get(2).equals("JFK"));
		assertTrue(checkFullPath.get(3).equals("SFO"));
		assertTrue(checkFullPath.get(4).equals("ACV"));

		fullDatasetPoints += 2;
	}

	/**
	 * Infinitely many paths, only checking cost. Tests both aggregate and full
	 * data set.
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testACKtoACVCancelledAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ACK", "ACV", FlightCriteria.CANCELED);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df4.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df4.format(0.04591496121);

		assertTrue(aggAnswer.equals(correctCost));

		aggregatePoints += 1;
	}

	/**
	 * Infinitely many paths, only checking cost. Tests both aggregate and full
	 * data set.
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testACKtoACVCancelledFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("ACK", "ACV", FlightCriteria.CANCELED);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df4.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df4.format(0.04591496121);

		assertTrue(fullAnswer.equals(correctCost));

		fullDatasetPoints += 2;
	}

	/**
	 * Tests both aggregate and full data set. Multiple path's possible. Only
	 * check cost.
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testACKtoWYSDistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ACK", "WYS", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(2462.0);

		assertTrue(aggAnswer.equals(correctCost));

		aggregatePoints += 1;
	}

	/**
	 * Tests both aggregate and full data set. Multiple path's possible. Only
	 * test cost.
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testACKtoWYSDistanceFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("ACK", "WYS", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(2462.0);

		assertTrue(fullAnswer.equals(correctCost));

		fullDatasetPoints += 2;
	}

	/**
	 * tests aggregate and full data set. Infinitely many path's possible. Only
	 * test cost.
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testACKtoWYSCancelledAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ACK", "WYS", FlightCriteria.CANCELED);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df4.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df4.format(0.004132231405);

		assertTrue(aggAnswer.equals(correctCost));

		aggregatePoints += 1;
	}

	/**
	 * tests aggregate and full data set. Infinitely many path's possible. Only
	 * test cost.
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testACKtoWYSCancelledFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("ACK", "WYS", FlightCriteria.CANCELED);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df4.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df4.format(0.004132231405);

		assertTrue(fullAnswer.equals(correctCost));

		fullDatasetPoints += 2;
	}

	/**
	 * Tests both aggregate and full data set. String answer = "Path Length: " +
	 * "1122.0" + "\nPath: " + "[ACV, SFO, SLC, WYS]";
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testACVtoWYSDistanceAggregatec()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ACV", "WYS", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(1122.0);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("ACV"));
		assertTrue(checkAggregatePath.get(2).equals("SFO"));
		assertTrue(checkAggregatePath.get(3).equals("SLC"));
		assertTrue(checkAggregatePath.get(4).equals("WYS"));

		aggregatePoints += 1;
	}

	/**
	 * Tests both aggregate and full data set. String answer = "Path Length: " +
	 * "1122.0" + "\nPath: " + "[ACV, SFO, SLC, WYS]";
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testACVtoWYSDistanceFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("ACV", "WYS", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(1122.0);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("ACV"));
		assertTrue(checkFullPath.get(2).equals("SFO"));
		assertTrue(checkFullPath.get(3).equals("SLC"));
		assertTrue(checkFullPath.get(4).equals("WYS"));

		fullDatasetPoints += 2;
	}

	/**
	 * Tests both aggregate and full data structure. Infinately many path's,
	 * only test cost.
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testACVtoWYSCancelledAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ACV", "WYS", FlightCriteria.CANCELED);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df4.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df4.format(0.03899721448);

		aggregatePoints += 1;
		assertTrue(aggAnswer.equals(correctCost));
	}

	/**
	 * Tests both aggregate and full data structure. Infinately many path's,
	 * only test cost.
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testACVtoWYSCancelledFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("ACV", "WYS", FlightCriteria.CANCELED);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df4.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df4.format(0.03899721448);

		fullDatasetPoints += 2;
		assertTrue(fullAnswer.equals(correctCost));
	}

	/**
	 * Tests both aggregate and full data set. Multiple path's possible. Only
	 * test cost.
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testMOBtoSLCDistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("MOB", "SLC", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(1528.0);

		aggregatePoints += 1;

		assertTrue(aggAnswer.equals(correctCost));
	}

	/**
	 * Tests both aggregate and full data set. Multiple path's possible. Only
	 * test cost.
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testMOBtoSLCDistance()
	{
		BestPath fullPath = fullDataset.getBestPath("MOB", "SLC", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(1528.0);

		fullDatasetPoints += 2;

		assertTrue(fullAnswer.equals(correctCost));
	}

	/**
	 * Tests both aggregate and full data set. Multiple path's possible. Only
	 * test cost.
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testMOBtoMVYDistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("MOB", "MVY", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(1235.0);

		aggregatePoints += 1;

		assertTrue(aggAnswer.equals(correctCost));
	}

	/**
	 * Tests both aggregate and full data set. Multiple path's possible. Only
	 * test cost.
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testMOBtoMVYDistanceFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("MOB", "MVY", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(1235.0);

		fullDatasetPoints += 2;

		assertTrue(fullAnswer.equals(correctCost));
	}

	/**
	 * Tests both aggregate and full data set.
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testITOtoMOBDistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ITO", "MOB", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(4223.0);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("ITO"));
		assertTrue(checkAggregatePath.get(2).equals("LAX"));
		assertTrue(checkAggregatePath.get(3).equals("DFW"));
		assertTrue(checkAggregatePath.get(4).equals("MOB"));

		aggregatePoints += 1;
	}

	/**
	 * Tests both aggregate and full data set.
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testITOtoMOBDistance()
	{
		BestPath fullPath = fullDataset.getBestPath("ITO", "MOB", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(4223.0);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("ITO"));
		assertTrue(checkFullPath.get(2).equals("LAX"));
		assertTrue(checkFullPath.get(3).equals("DFW"));
		assertTrue(checkFullPath.get(4).equals("MOB"));

		fullDatasetPoints += 2;
	}

	/**
	 * Tests both aggregate and full data structure. String answer =
	 * "Path Length: " + "205.0" + "\nPath: " + "[KOA, OGG, ITO]";
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testKOAtoITODistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("KOA", "ITO", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(205.0);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("KOA"));
		assertTrue(checkAggregatePath.get(2).equals("OGG"));
		assertTrue(checkAggregatePath.get(3).equals("ITO"));

		aggregatePoints += 1;
	}

	/**
	 * Tests both aggregate and full data structure. String answer =
	 * "Path Length: " + "205.0" + "\nPath: " + "[KOA, OGG, ITO]";
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testKOAtoITODistanceFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("KOA", "ITO", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(205.0);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("KOA"));
		assertTrue(checkFullPath.get(2).equals("OGG"));
		assertTrue(checkFullPath.get(3).equals("ITO"));

		fullDatasetPoints += 2;
	}

	/**
	 * Tests both aggregate and full data structure. Multiple paths possible.
	 * Only test cost.
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testKOAtoCWADistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("KOA", "CWA", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(4425.0);

		assertTrue(aggAnswer.equals(correctCost));
		aggregatePoints += 1;
	}

	/**
	 * Tests both aggregate and full data structure. Multiple paths possible.
	 */
	@Test
	@Autograder(points = 1, group = "full data set")
	public void testKOAtoCWADistanceFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("KOA", "CWA", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(4425.0);

		assertTrue(fullAnswer.equals(correctCost));
		fullDatasetPoints += 1;
	}

	/**
	 * Tests both aggregate and full data structure. String answer =
	 * "Path Length: " + "2993.0" + "\nPath: " + "[ANC, HNL, ITO]";
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testANCtoITODistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("ANC", "ITO", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(2993.0);

		assertTrue(aggAnswer.equals(correctCost));

		assertTrue(checkAggregatePath.get(1).equals("ANC"));
		assertTrue(checkAggregatePath.get(2).equals("HNL"));
		assertTrue(checkAggregatePath.get(3).equals("ITO"));

		aggregatePoints += 1;
	}

	/**
	 * Tests both aggregate and full data structure. String answer =
	 * "Path Length: " + "2993.0" + "\nPath: " + "[ANC, HNL, ITO]";
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testANCtoITODistanceFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("ANC", "ITO", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(2993.0);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("ANC"));
		assertTrue(checkFullPath.get(2).equals("HNL"));
		assertTrue(checkFullPath.get(3).equals("ITO"));

		fullDatasetPoints += 2;
	}

	/**
	 * Test both aggregate and full data structure. String answer =
	 * "Path Length: " + "3254.0" + "\nPath: " + "[FAI, ANC, HNL, ITO]";
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testFAItoITODistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("FAI", "ITO", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(3254.0);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("FAI"));
		assertTrue(checkAggregatePath.get(2).equals("ANC"));
		assertTrue(checkAggregatePath.get(3).equals("HNL"));
		assertTrue(checkAggregatePath.get(4).equals("ITO"));

		aggregatePoints += 1;
	}

	/**
	 * Test both aggregate and full data structure. String answer =
	 * "Path Length: " + "3254.0" + "\nPath: " + "[FAI, ANC, HNL, ITO]";
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testFAItoITODistanceFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("FAI", "ITO", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(3254.0);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("FAI"));
		assertTrue(checkFullPath.get(2).equals("ANC"));
		assertTrue(checkFullPath.get(3).equals("HNL"));
		assertTrue(checkFullPath.get(4).equals("ITO"));

		fullDatasetPoints += 2;
	}

	/**
	 * Tests both aggregate and full data set. String answer = "Path Length: " +
	 * "261.0" + "\nPath: " + "[FAI, ANC]";
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testFAItoANCDistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("FAI", "ANC", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(261.0);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("FAI"));
		assertTrue(checkAggregatePath.get(2).equals("ANC"));

		aggregatePoints += 1;
	}

	/**
	 * Tests both aggregate and full data set. String answer = "Path Length: " +
	 * "261.0" + "\nPath: " + "[FAI, ANC]";
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testFAItoANCDistanceFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("FAI", "ANC", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(261.0);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("FAI"));
		assertTrue(checkFullPath.get(2).equals("ANC"));

		fullDatasetPoints += 2;
	}

	/**
	 * Tests aggregated and full data set. Multiple paths possible. only test
	 * cost.
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testFAItoGNVDistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("FAI", "GNV", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(3673.0);

		assertTrue(aggAnswer.equals(correctCost));
		aggregatePoints += 1;
	}

	/**
	 * Tests aggregated and full data set. Multiple paths possible. only test
	 * cost.
	 */
	@Test
	@Autograder(points = 1, group = "full data set")
	public void testFAItoGNVDistanceFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("FAI", "GNV", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(3673.0);

		assertTrue(fullAnswer.equals(correctCost));
		fullDatasetPoints += 1;
	}

	/**
	 * tests both aggregate and full data set. Multiple paths possible. Only
	 * test cost.
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testFLLtoFAIDistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("FLL", "FAI", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(3953.0);

		assertTrue(aggAnswer.equals(correctCost));
		aggregatePoints += 1;
	}

	/**
	 * tests both aggregate and full data set. Multiple paths possible. Only
	 * test cost.
	 */
	@Test
	@Autograder(points = 1, group = "full data set")
	public void testFLLtoFAIDistance()
	{
		BestPath fullPath = fullDataset.getBestPath("FLL", "FAI", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(3953.0);

		assertTrue(fullAnswer.equals(correctCost));
		fullDatasetPoints += 1;
	}

	/**
	 * Tests both Aggregate and Full Data set PWM MVY DISTANCE ['PWM', 'JFK',
	 * 'MVY'] 446
	 */
	@Test
	@Autograder(points = 1, group = "aggregated data set")
	public void testPWMtoMVYDistanceAggregated()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("PWM", "MVY", FlightCriteria.DISTANCE);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(446.0);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("PWM"));
		assertTrue(checkAggregatePath.get(2).equals("JFK"));
		assertTrue(checkAggregatePath.get(3).equals("MVY"));
		aggregatePoints += 1;
	}

	/**
	 * Tests both Aggregate and Full Data set PWM MVY DISTANCE ['PWM', 'JFK',
	 * 'MVY'] 446
	 */
	@Test
	@Autograder(points = 2, group = "full data set")
	public void testPWMtoMVYDistanceFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("PWM", "MVY", FlightCriteria.DISTANCE);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(446.0);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("PWM"));
		assertTrue(checkFullPath.get(2).equals("JFK"));
		assertTrue(checkFullPath.get(3).equals("MVY"));
		fullDatasetPoints += 2;
	}

	/**
	 * This tests only the full data set. Worth 5 points
	 */
	@Test
	@Autograder(points = 9, group = "full data set")
	public void testSLCtoMVYDistanceWithB6CarrierFullDataset()
	{
		BestPath fullPath = fullDataset.getBestPath("SLC", "MVY", FlightCriteria.DISTANCE, "B6");

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(2163.0);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("SLC"));
		assertTrue(checkFullPath.get(2).equals("JFK"));
		assertTrue(checkFullPath.get(3).equals("MVY"));
		fullDatasetPoints += 10;

	}

	/**
	 * This test is only for the full data set.
	 */
	@Test
	@Autograder(points = 4, group = "full data set")
	public void testSLCtoMVYDistanceWithDLCarrier()
	{
		BestPath fullPath = fullDataset.getBestPath("SLC", "MVY", FlightCriteria.DISTANCE, "DL");

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(0.0);

		assertTrue(fullAnswer.equals(correctCost));
		fullDatasetPoints += 5;
	}

	// The following tests are only to be 10 points of the final grade

	/**
	 * --> these are edges for avoiding negative cycles! together, no more than
	 * 5% of grade (10 pts) String answer = "Path Length: " + "288.31" +
	 * "\nPath: " + "[IMT, MSP, RHI]";
	 */
	@Test
	@Autograder(points = 1, group = "full data set")
	public void testIMTtoRHICost()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("IMT", "RHI", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df2.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df2.format(288.31);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("IMT"));
		assertTrue(checkAggregatePath.get(2).equals("MSP"));
		assertTrue(checkAggregatePath.get(3).equals("RHI"));
		fullDatasetPoints++;
	}

	/**
	 * --> these are edges for avoiding negative cycles! together, no more than
	 * 5% of grade (10 pts) * CDV YAK COST ['CDV', 'ANC','JNU', 'YAK'] 521.5
	 */
	@Test
	@Autograder(points = 1, group = "full data set")
	public void testCDVtoYAKCost()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("CDV", "YAK", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(521.5);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("CDV"));
		assertTrue(checkAggregatePath.get(2).equals("ANC"));
		assertTrue(checkAggregatePath.get(3).equals("JNU"));
		assertTrue(checkAggregatePath.get(4).equals("YAK"));
		fullDatasetPoints++;
	}

	/**
	 * --> these are edges for avoiding negative cycles! together, no more than
	 * 5% of grade (10 pts) FCA MSO COST ['FCA', 'SLC', 'MSO'] 577
	 */
	@Test
	@Autograder(points = 1, group = "full data set")
	public void testFCAtoMSOCost()
	{
		BestPath fullDatasetPath = aggregatedDataset.getBestPath("FCA", "MSO", FlightCriteria.COST);

		ArrayList<String> checkFullPath = breakUpPath(fullDatasetPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(577.0);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("FCA"));
		assertTrue(checkFullPath.get(2).equals("SLC"));
		assertTrue(checkFullPath.get(3).equals("MSO"));
		fullDatasetPoints++;
	}

	/**
	 * --> these are edges for avoiding negative cycles! together, no more than
	 * 5% of grade (10 pts) MEI PIB COST [] 0
	 */
	@Test
	@Autograder(points = 1, group = "full data set")
	public void testMEItoPIBCost()
	{
		BestPath fullPath = fullDataset.getBestPath("MEI", "PIB", FlightCriteria.COST);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(0.0);

		assertTrue(fullAnswer.equals(correctCost));
		fullDatasetPoints++;
	}

	/**
	 * --> these are edges for avoiding negative cycles! together, no more than
	 * 5% of grade (10 pts) INL HIB COST ['INL', 'MSP', 'HIB'] 311.33
	 */
	@Test
	@Autograder(points = 1, group = "full data set")
	public void testINLtoHIBCost()
	{
		BestPath fullPath = fullDataset.getBestPath("INL", "HIB", FlightCriteria.COST);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df2.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df2.format(311.33);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("INL"));
		assertTrue(checkFullPath.get(2).equals("MSP"));
		assertTrue(checkFullPath.get(3).equals("HIB"));
		fullDatasetPoints++;
	}

	/**
	 * --> these are edges for avoiding negative cycles! together, no more than
	 * 5% of grade (10 pts) SAV BQK COST ['SAV','ATL', 'BQK'] 430.65
	 */
	@Test
	@Autograder(points = 1, group = "full data set")
	public void testSAVtoBQKCost()
	{
		BestPath fullPath = fullDataset.getBestPath("SAV", "BQK", FlightCriteria.COST);
		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df2.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df2.format(430.65);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("SAV"));
		assertTrue(checkFullPath.get(2).equals("ATL"));
		assertTrue(checkFullPath.get(3).equals("BQK"));
		fullDatasetPoints++;
	}

	/**
	 * --> these are edges for avoiding negative cycles! together, no more than
	 * 5% of grade (10 pts) HIB INL COST ['HIB', 'MSP', 'INL'] 271.52
	 */
	@Test
	@Autograder(points = 1, group = "full data set")
	public void testHIBtoINLCost()
	{
		BestPath fullPath = fullDataset.getBestPath("HIB", "INL", FlightCriteria.COST);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df2.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df2.format(271.52);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("HIB"));
		assertTrue(checkFullPath.get(2).equals("MSP"));
		assertTrue(checkFullPath.get(3).equals("INL"));
		fullDatasetPoints++;
	}

	/**
	 * --> these are edges for avoiding negative cycles! together, no more than
	 * 5% of grade (10 pts) RHI IMT COST ['RHI', 'MSP', 'IMT'] 348.5
	 */
	@Test
	@Autograder(points = 1, group = "full data set")
	public void testRHItoIMTCost()
	{
		BestPath fullPath = aggregatedDataset.getBestPath("RHI", "IMT", FlightCriteria.COST);

		ArrayList<String> checkFullPath = breakUpPath(fullPath);

		String fullAnswer = df1.format(Double.parseDouble(checkFullPath.get(0)));
		String correctCost = df1.format(348.5);

		assertTrue(fullAnswer.equals(correctCost));
		assertTrue(checkFullPath.get(1).equals("RHI"));
		assertTrue(checkFullPath.get(2).equals("MSP"));
		assertTrue(checkFullPath.get(3).equals("IMT"));
		fullDatasetPoints++;
	}

	/**
	 * --> these are edges for avoiding negative cycles! together, no more than
	 * 5% of grade (10 pts) DVL JMS COST ['DVL', 'DEN', 'JMS'] 340.93
	 */
	@Test
	@Autograder(points = 1, group = "full data set")
	public void testDVLtoJMSCost()
	{
		BestPath aggregatePath = aggregatedDataset.getBestPath("DVL", "JMS", FlightCriteria.COST);

		ArrayList<String> checkAggregatePath = breakUpPath(aggregatePath);

		String aggAnswer = df1.format(Double.parseDouble(checkAggregatePath.get(0)));
		String correctCost = df1.format(340.93);

		assertTrue(aggAnswer.equals(correctCost));
		assertTrue(checkAggregatePath.get(1).equals("DVL"));
		assertTrue(checkAggregatePath.get(2).equals("DEN"));
		assertTrue(checkAggregatePath.get(3).equals("JMS"));
		fullDatasetPoints++;
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
