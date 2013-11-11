package raytracer.tests;

import junit.framework.Assert;
import junit.framework.TestCase;
import raytracer.math.Mat3x3;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * JUnit Test class for the vector math class.
 * 
 * @author Sebastian Dass&eacute;
 */
public class MathTests extends TestCase {
	 /* 
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	
	/**
	 * Test Definitions ;-)
	 */
	public void testVectorOperations() {
		Assert.assertEquals(new Normal3(0.5, 1, 1.5), new Normal3(1, 2, 3).mul(0.5));
		
		Assert.assertEquals(new Normal3(4, 4, 4), new Normal3(1, 2, 3).add(new Normal3(3, 2, 1)));
		
		Assert.assertEquals(1.0, new Normal3(1, 0, 0).dot(new Vector3(1, 0, 0)));
		Assert.assertEquals(1.0, new Vector3(1, 0, 0).dot(new Normal3(1, 0, 0)));
		Assert.assertEquals(1.0, new Vector3(1, 0, 0).dot(new Vector3(1, 0, 0)));
		
		Assert.assertEquals(0.0, new Normal3(1, 0, 0).dot(new Vector3(0, 1, 0)));
		Assert.assertEquals(0.0, new Vector3(1, 0, 0).dot(new Normal3(0, 1, 0)));
		Assert.assertEquals(0.0, new Vector3(1, 0, 0).dot(new Vector3(0, 1, 0)));
		
		Assert.assertEquals(new Vector3(-1, -1, 1), new Point3(1, 1, 1).sub(new Point3(2, 2, 0)));
		
		Assert.assertEquals(new Point3(-3, -2 , -1), new Point3(1, 1, 1).sub(new Vector3(4, 3, 2)));
		
		Assert.assertEquals(new Point3(5, 4, 3), new Point3(1, 1, 1).add(new Vector3(4, 3, 2)));
		
		Assert.assertEquals(Math.sqrt(3), new Vector3(1, 1, 1).magnitude);
		
		Assert.assertEquals(new Vector3(5, 4, 3), new Vector3(1, 1, 1).add(new Normal3(4, 3, 2)));
		Assert.assertEquals(new Vector3(5, 4, 3), new Vector3(1, 1, 1).add(new Vector3(4, 3, 2)));
		Assert.assertEquals(new Vector3(-3, -2, -1), new Vector3(1, 1, 1).sub(new Normal3(4, 3, 2)));
		
		// TODO
		// erwartet laut Aufgabenlatt:
//		Assert.assertEquals(new Vector3(0.707, 0.707, 0), new Vector3(-0.707, 0.707, 0).reflectedOn(new Normal3(0, 1, 0)));
		// erwartet nach meinem mathematischen Verständnis:
		Assert.assertEquals(new Vector3(-0.707, -0.707, 0), new Vector3(-0.707, 0.707, 0).reflectedOn(new Normal3(0, 1, 0)));
		
		// TODO
		// erwartet laut Aufgabenblatt:
//		Assert.assertEquals(new Vector3(0.707, -0.707, 0), new Vector3(0.707, 0.707, 0).reflectedOn(new Normal3(1, 0, 0)));
		// erwartet nach meinem mathematischen Verständnis:
		Assert.assertEquals(new Vector3(-0.707, 0.707, 0), new Vector3(0.707, 0.707, 0).reflectedOn(new Normal3(1, 0, 0)));
		
		Assert.assertEquals(new Point3(3, 2, 1), new Mat3x3(1, 0, 0, 
											   				0, 1, 0, 
											   				0, 0, 1).mul(new Point3(3, 2, 1)));
		Assert.assertEquals(new Mat3x3(1, 2, 3, 
				  					   4, 5, 6, 
				  					   7, 8, 9), new Mat3x3(1, 2, 3, 
				  							   				4, 5, 6, 
				  							   				7, 8, 9).mul(new Mat3x3(1, 0, 0, 
				  							   										0, 1, 0, 
				  							   										0, 0, 1)));
		Assert.assertEquals(new Mat3x3(3, 2, 1,
				   					   6, 5, 4,
				   					   9, 8, 7), new Mat3x3(1, 2, 3, 
				   							   				4, 5, 6, 
				   							   				7, 8, 9).mul(new Mat3x3(0, 0, 1, 
				   							   										0, 1, 0, 
				   							   										1, 0, 0)));
		Assert.assertEquals(new Mat3x3(8, 2, 3, 
									   8, 5, 6, 
									   8, 8, 9), new Mat3x3(1, 2, 3, 
											   				4, 5, 6, 
											   				7, 8, 9).changeCol1(new Vector3(8, 8, 8)));
		Assert.assertEquals(new Mat3x3(1, 8, 3, 
									   4, 8, 6, 
									   7, 8, 9), new Mat3x3(1, 2, 3, 
											   				4, 5, 6, 
											   				7, 8, 9).changeCol2(new Vector3(8, 8, 8)));
		Assert.assertEquals(new Mat3x3(1, 2, 8, 
									   4, 5, 8, 
									   7, 8, 8), new Mat3x3(1, 2, 3, 
											   				4, 5, 6, 
											   				7, 8, 9).changeCol3(new Vector3(8, 8, 8)));
	}
}
