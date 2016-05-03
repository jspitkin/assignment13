package wrappertest;

import java.io.File;

import autograderutils.JUnitWrapper;

public class WrapperTest {

	public static void main(String[] args) {
		String groupPath = new File("groups.properties").getAbsolutePath();
		String extraPath = new File("extra.properties").getAbsolutePath();
		String className = WrapperJunit.class.getName(); // or hardcode the string "package.ClassName"
		JUnitWrapper.main(new String[] {className, groupPath, extraPath});
	}
}
