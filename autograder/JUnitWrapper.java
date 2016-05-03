package autograderutils;


import java.io.FileInputStream;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import autograderutils.annotations.Autograder;

/**
 * Used to run a JUnit test suite and pipe it's results to stdout. 
 * Args[0] the qualified name of the JUnit test class, e.g. example.package.Name
 * args[1] the Absolute path to group.properties (see below)
 * args[2] the absolute path to extra.properties (see below).
 * These path arguments must be absolute due to the nature of the Autograder grading portion.
 * 
 * group.properties is a properties file of groupName=pointsPossible, that the Autograder annotation will key off of.
 * An example might look like this:
 * mergesort=20
 * quicksort=20
 * insertion_sort=20
 * 
 * If no groups are neccessary, the second argument needs to be the number of total possible points in the grader script. 
 * The @Autograder annotation will default to "Autograder," but the extra.properites file is alywas necessary.
 *
 * The extra.properties file is a similar key=value pair where the key is category that isn't tested by JUnit, i.e.
 * test=10
 * style=10
 * analysis=30
 * 
 * @author ryans
 *
 */
public class JUnitWrapper {
	String graderName;
	public static void main(String[] args) {
		argCheck(args);
		String graderClassName = args[0];
		Properties groupProperties = loadProperties(args[1]);
		if(groupProperties.size() == 0) {
			groupProperties.put("Autograder", args[1]);
		}
		Properties extraProperties = loadProperties(args[2]);
		Map<String, Integer> pointGroups = getGroups(groupProperties);
		
		JUnitCore core = new JUnitCore();
		Class<?> unitGraderClass = graderClass(graderClassName);
		Result graderResult = core.run(unitGraderClass);
		
		for(Failure failure : graderResult.getFailures()) {
			Autograder autoAnno = failure.getDescription().getAnnotation(Autograder.class);
			checkAnnotationPresent(failure.getTestHeader(), autoAnno);
			
			String groupName = autoAnno.group();
			int currentPoints = pointGroups.get(groupName);
			pointGroups.put(groupName, currentPoints + autoAnno.points());
			System.out.println("TEST FAILED: " + failure.getDescription().getMethodName());
			if(failure.getMessage() != null) {
				System.out.println(failure.getMessage());
			}
		}
		
		//print out results
		for(String group : pointGroups.keySet()) {
			int totalPoints = Integer.parseInt(groupProperties.getProperty(group));
			int pointsEarned = totalPoints - pointGroups.get(group);
			System.out.printf("%s:" + calculateTabs(group) + "%d/%d\n", group, pointsEarned, totalPoints);
		}
		
		// add any extra fields
		for(Object extraField : extraProperties.keySet()) {
			System.out.printf("%s:" + calculateTabs((String)extraField) + " /%s\n", extraField, extraProperties.getProperty((String)extraField));
		}
		
	}
	
	private static String calculateTabs(String key) {
		StringBuilder sb = new StringBuilder();
		int numOf8characters = (key.length() +1) / 8; // add one to account for colon after key
		for(int i = 0; i < 4 - numOf8characters; i++) {
			sb.append('\t');
		}
		return sb.toString();
		
	}

	private static Properties loadProperties(String path) {
		Properties groupProperties = new Properties();
		try(FileInputStream groupPropInputStream = new FileInputStream(path)) {
			groupProperties.load(groupPropInputStream);
		} catch (IOException e) {
			System.out.println("Didn't supply property file." + e.getMessage());
		}
		return groupProperties;
	}
	
	private static void checkAnnotationPresent(String testMethodName, Annotation anno) {
		if(anno == null) {
			throw new RuntimeException(testMethodName + " does not have required annotation.");
		}
	}
	
	private static Map<String, Integer> getGroups(Properties groupProps) {
		Map<String, Integer> map = new HashMap<>();
		for(Object group : Collections.list(groupProps.propertyNames())) {
			map.put(group.toString(), 0);
		}
		return map;
	}
	private static Class<?> graderClass(String name) {
		Class<?> unitGraderClass = null;
		try {
			unitGraderClass = Class.forName(name);
		} catch (ClassNotFoundException e) {
			System.out.println("Could not find supplied class " + name + ".\n" + e.getMessage());
			System.exit(1);
		}
		return unitGraderClass;
	}
	private static void argCheck(String[] args) {
		if(args.length != 3) {
			System.out.println("Requires Grader script class name and group.properties file.");
			System.exit(1);
		}
	}
}
