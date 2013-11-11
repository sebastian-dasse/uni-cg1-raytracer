package raytracer.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test runner for raytracer tests
 * 
 * @author Simon Lischka
 *
 */
public class AllTests {
	
	/**
	 * Creates new test suite with MathTest-Class
	 * 
	 * @return suite
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(MathTests.class);
		return suite;
	}
	
	/**
	 * Runs test suite
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
