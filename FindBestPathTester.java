package assignment13;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.reflect.Field;

/**
 * <p>An example of how a user will use your best flight API.</p>
 * <p>You will still be required to writed JUnit tests to test your program.</p>
 *
 * @author CS2420 Teaching Staff - Spring 2016
 *
 */
public class FindBestPathTester {

	public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		NetworkGraph airportGraph = null;
		try {
			 airportGraph = new NetworkGraph("testfile.csv");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Returns the shortest distance path of flights from MOB to ACV
		// Solution: a path of ['MOB', 'DFW', 'SFO', 'ACV'] and distance of 2253
//		BestPath shortestDistancePath = airportGraph.getBestPath("MOB", "ACV", FlightCriteria.DISTANCE);
//		System.out.println(shortestDistancePath.toString());
//		
//		// Returns the shortest distance path of flights from SFO to DWF when flying with DL
//		// Solution: a path of ['SFO', 'SLC', 'DFW'] and distance of 1588
//		BestPath shortestDistancePath2 = airportGraph.getBestPath("SFO", "DFW", FlightCriteria.DISTANCE, "DL");
//		System.out.println(shortestDistancePath2.toString());
//		
//		// Returns the shortest flight time path from MOB to SLC
//		// Solution: a path of ['MOB', 'DFW', 'SLC'] and time of ~269.25
//		BestPath shortestTimePath = airportGraph.getBestPath("MOB", "SLC", FlightCriteria.TIME);
//		System.out.println(shortestTimePath.toString());
//		
//		// Returns the fiscally cheapest path of flights from LAS to LAX
//		// Solution: a path of ['LAS', 'LAX'] and cost of ~138.39
//		BestPath cheapestPath = airportGraph.getBestPath("LAS", "LAX", FlightCriteria.COST);
//		System.out.println(cheapestPath.toString());
		
//		 BestPath shortestDistancePath = airportGraph.getBestPath("IXS", "PWB", FlightCriteria.DISTANCE, "MQ");
//		 System.out.println(shortestDistancePath.toString());
//		 ArrayList<String> expectedPath = new ArrayList<String>(Arrays.asList("IXS", "PWB"));
//		 System.out.println(shortestDistancePath.equals(createSolutionPath(expectedPath, 2882)));
		
		// command-line user interface
		if(args.length != 0){
			System.out.println();
			System.out.println("Please enter a search string for Dijkstra's:");
			System.out.println("  ORIGIN DESTINATION CRITERIA CARRIER");
			System.out.println("  e.g. SFO DFW DISTANCE DL");
			System.out.println("Default criteria of DISTANCE and across all carriers.");
			System.out.println("Input nothing to exit the interface.");
			System.out.println();
			
			Scanner input = new Scanner(System.in);
			String search = "SFO DFW DISTANCE DL";
			while(search.length() != 0){
				System.out.print("Dijkstra's: ");
				
				search = input.nextLine();
				String[] searchArgs = search.split(" ");
				BestPath cheapestPath;
				
				if(searchArgs.length > 1 && searchArgs.length < 5){
					FlightCriteria criteria = FlightCriteria.DISTANCE;
					if(searchArgs.length > 2){
						switch(searchArgs[2]) {
							case "COST":
								criteria = FlightCriteria.COST;
								break;
							case "DELAY":
								criteria = FlightCriteria.DELAY;
								break;
							case "DISTANCE":
								criteria = FlightCriteria.DISTANCE;
								break;
							case "CANCELED":
								criteria = FlightCriteria.CANCELED;
								break;
							case "TIME":
								criteria = FlightCriteria.TIME;
								break;
							}
					}
					if(searchArgs.length > 3){
						cheapestPath = airportGraph.getBestPath(searchArgs[0], searchArgs[1], criteria, searchArgs[3]);
					}else{
						cheapestPath = airportGraph.getBestPath(searchArgs[0], searchArgs[1], criteria);
					}
					System.out.println(cheapestPath);
				}else{
					System.out.println("Error - please utilize 2-4 search arguments.");
				}
				
				System.out.println();
			}
			input.close();
		}
	}
	
	public static BestPath createSolutionPath(ArrayList<String> path, double pathLength) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		BestPath solutionPath = new BestPath();
		
	    Field pathField = solutionPath.getClass().getDeclaredField("path");
	    pathField.setAccessible(true);
	    pathField.set(solutionPath, path);
	    
	    Field pathLengthField = solutionPath.getClass().getDeclaredField("pathLength");
	    pathLengthField.setAccessible(true);
	    pathLengthField.set(solutionPath, pathLength);
	    
	    return solutionPath;
	}

}
