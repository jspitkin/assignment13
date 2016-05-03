package wrappertest;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import autograderutils.annotations.Autograder;

public class WrapperJunit {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	@Autograder(points=3, group="small data set")
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	@Autograder(points=3, group="aggregated data set")
	public void meaningfullName() {
		Assert.assertTrue("This should be true!", false);
	}
	
	@Test
	@Autograder(points=3)
	public void testNoGroupName() {
		Assert.assertTrue("This is a message", true);
	}
	
	@Test
	@Autograder(points=3, group="full data set")
	public void bigGroupName() {
		Assert.assertTrue(false);
	}
	
}
