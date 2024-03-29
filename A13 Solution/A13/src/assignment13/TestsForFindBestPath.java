package assignment13;

/**
 * These tests are simple print out statements to verify taht the data looks visually correct.
 * The proper JUnit tests are in the "Tests" class.
 *
 * @author David Reeves
 * @author Naser Abu-Rmaileh
 *
 */
public class TestsForFindBestPath {

	public static void main(String[] args) {
		NetworkGraph airportGraph = null;
		try {
			airportGraph = new NetworkGraph("flights-2015-q3.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Returns the shortest distance path of flights from MOB to ACV
		// Solution: a path of ['MOB', 'DFW', 'SFO', 'ACV'] and distance of 2253
		BestPath shortestDistancePath = airportGraph.getBestPath("MOB", "ACV", FlightCriteria.DISTANCE);
		System.out.println(shortestDistancePath.toString());
		
		// Returns the shortest distance path of flights from SFO to DWF when flying with DL
		// Solution: a path of ['SFO', 'SLC', 'DFW'] and distance of 1588
		BestPath shortestDistancePath2 = airportGraph.getBestPath("SFO", "DFW", FlightCriteria.DISTANCE, "DL");
		System.out.println(shortestDistancePath2.toString());
		
		// Returns the shortest flight time path from MOB to SLC
		// Solution: a path of ['MOB', 'DFW', 'SLC'] and time of ~269.25
		BestPath shortestTimePath = airportGraph.getBestPath("MOB", "SLC", FlightCriteria.TIME);
		System.out.println(shortestTimePath.toString());
		
		// Returns the fiscally cheapest path of flights from LAS to LAX
		// Solution: a path of ['LAS', 'LAX'] and cost of ~138.39
		BestPath cheapestPath = airportGraph.getBestPath("LAS", "LAX", FlightCriteria.COST);
		System.out.println(cheapestPath.toString());
		
		BestPath testpath = airportGraph.getBestPath(null, "LAX", FlightCriteria.COST);
		System.out.println("This is the test method where start == null:");
		System.out.println(testpath.toString());
		
		BestPath testpath2 = airportGraph.getBestPath("LAS", null, FlightCriteria.COST);
		System.out.println("This is the test method where end == null:");
		System.out.println(testpath2.toString());
		
		BestPath testpath3 = airportGraph.getBestPath(null, null, FlightCriteria.COST);
		System.out.println("This is the test method where origin and destination == null:");
		System.out.println(testpath3.toString());
		
		BestPath testpath4 = airportGraph.getBestPath("", "", FlightCriteria.COST);
		System.out.println("This is the test method where origin and destination == nothing:");
		System.out.println(testpath4.toString());
		
		BestPath testpath5 = airportGraph.getBestPath("XXX", "YYY", FlightCriteria.COST);
		System.out.println("This is the test method where neither airport exists:");
		System.out.println(testpath5.toString());
		
		BestPath testpath6 = airportGraph.getBestPath("LAS", "XXX", FlightCriteria.COST);
		System.out.println("This is the test method where only the origin airport exists:");
		System.out.println(testpath6.toString());
		
		BestPath shortestDistancePath3 = airportGraph.getBestPath(null, "DFW", FlightCriteria.DISTANCE, "DL");
		System.out.println("This is the test method where start == null");
		System.out.println(shortestDistancePath3.toString());
		
		
	}

}
