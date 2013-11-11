package raytracer.tests;

import junit.framework.Test;
import junit.framework.TestSuite;

/**
 * TODO
 * 
 * @author 
 *
 */
public class AllTests {
	
	/**
	 * TODO
	 * 
	 * @return
	 */
	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(MathTests.class);
		return suite;
	}
	
	/**
	 * TODO
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());
	}
}
