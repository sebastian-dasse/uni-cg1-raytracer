package raytracer.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * Test runner for raytracer tests.
 * 
 * @author Simon Lischka
 *
 */
public class AllTests {
	
	/**
	 * Creates a new test suite with the <code>MathTests</code> class.
	 * 
	 * @return A <code>Test</code> suite.
	 */
	public static Test suite() {
		final TestSuite suite = new TestSuite();
		suite.addTestSuite(MathTests.class);
		return suite;
	}
	
	/**
	 * Runs the test suite.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
