package wrappertest;

import java.io.File;

import autograderutils.JUnitWrapper;

public class WrapperTest {

	public static void main(String[] args) {
		String groupPath = new File("groups.properties").getAbsolutePath();
		String extraPath = new File("extra.properties").getAbsolutePath();
		String className = Assignment13GradingTests.class.getName(); // or hardcode the string "package.ClassName"
		JUnitPlugin.main(new String[] {className, groupPath, extraPath});
	}
}
