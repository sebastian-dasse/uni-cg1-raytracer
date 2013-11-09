package raytracer.tests;

import junit.framework.Assert;
import junit.framework.TestCase;
import raytracer.math.Mat3x3;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * @author Sebastian Dass&eacute;
 * @author Simon Lischka;
 */
public class MathTests extends TestCase {
	private Normal3 n0d5_1_1d5;
	private Normal3 n010;
	private Normal3 n100;
	private Normal3 n123;
	private Normal3 n321;
	private Normal3 n444;
	private Normal3 n432;
	private Vector3 v010;
	private Vector3 vneg1_neg1_1;
	private Vector3 v0d707_neg0d707_0;
	private Vector3 vneg0d707_0d707_0;
	private Vector3 v0d707_0d707_0;
	private Vector3 v0707;
	private Vector3 v100;
	private Vector3 v111;
	private Vector3 v321;
	private Vector3 v432;
	private Point3 p543;
	private Point3 pneg3_neg2_neg1;
	private Point3 p111;
	private Point3 p220;
	private Mat3x3 mat001_010_100;
	private Mat3x3 mat100_010_001;
	private Mat3x3 mat123_456_789;
	private double sqrt3;
	
	
	private Mat3x3 mat321_654_987;
	
	
	private Point3 p321;
	
	
	/*
	 * The Notation used for defining number description variables follows
	 * this scheme:
	 * 
	 * <int> - One Digit Number
	 * <int>d<int> - Floating point numbers
	 * _<int><int> - Two Digit Numbers
	 * _neg<int> - Negative Numbers
	 * 
	 * Normals / Vectors / Points used in the test case are defined here. 
	 * JUnit reinitializes these Objects before every assertion to maintain
	 * neutral input data. 
	 * 
	 * (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() {
		vneg1_neg1_1 = new Vector3(-1, -1, 1);
		vneg0d707_0d707_0 = new Vector3(-0.707,0.707,0);
		v0d707_0d707_0 = new Vector3(0.707,0.707,0);
		v0d707_neg0d707_0 = new Vector3(0.707,-0.707,0);
		v010 = new Vector3(0, 1, 0);
		v0707 = new Vector3(-0.707, 0.707, 0);
		v100 = new Vector3(1, 0, 0);
		v111 = new Vector3(1, 1, 1);
		v321 = new Vector3(3, 2, 1);
		v432 = new Vector3(4, 3, 2);
		n0d5_1_1d5 = new Normal3(0.5,1,1.5);
		n010 = new Normal3(0, 1, 0);
		n100 = new Normal3(1, 0, 0);
		n123 = new Normal3(1, 2, 3);
		n321 = new Normal3(3, 2, 1);
		n432 = new Normal3(4, 3, 2);
		n444 = new Normal3(4, 4, 4);
		pneg3_neg2_neg1 = new Point3(-3,-2,-1);
		p111 = new Point3(1, 1, 1);
		p220 = new Point3(2, 2, 0);
		p321 = new Point3(3, 2, 1);
		p543 = new Point3(5, 4, 3);
		sqrt3 = Math.sqrt(3);
		
		mat100_010_001 = new Mat3x3(
			1, 0, 0, 
			0, 1, 0, 
			0, 0, 1 
		);
		mat001_010_100 = new Mat3x3(
			0, 0, 1, 
			0, 1, 0, 
			1, 0, 0
		);
		mat123_456_789 = new Mat3x3(
			1, 2, 3, 
			4, 5, 6, 
			7, 8, 9
		);
		mat321_654_987 = new Mat3x3(
			3, 2, 1, 
			6, 5, 4, 
			9, 8, 7
		);
	}
	
	public void testVectorOperations() {
		Assert.assertEquals(n0d5_1_1d5, n123.mul(0.5));
		Assert.assertEquals(n444,n123.add(n321));
		Assert.assertEquals(1,n100.dot(v100));
		Assert.assertEquals(1,v100.dot(n100));
		Assert.assertEquals(1,v100.dot(v100));
		Assert.assertEquals(0,n100.dot(v010));
		Assert.assertEquals(0,v100.dot(n010));
		Assert.assertEquals(0,v100.dot(v010));
		Assert.assertEquals(vneg1_neg1_1,p111.sub(p220));
		Assert.assertEquals(vneg1_neg1_1,p111.sub(p220));
		Assert.assertEquals(pneg3_neg2_neg1,p111.sub(v432));
		Assert.assertEquals(p543,p111.add(v432));
		Assert.assertEquals(sqrt3, v111.magnitude);
		Assert.assertEquals(v432,v111.add(n432));
		Assert.assertEquals(null,v111.add(v432));
		Assert.assertEquals(null,v111.sub(n432));
		Assert.assertEquals(null,vneg0d707_0d707_0.reflectedOn(n100));
		Assert.assertEquals(null,mat100_010_001.mul(p321));
		Assert.assertEquals(null, mat100_010_001.mul(v321));
		Assert.assertEquals(null, mat100_010_001.mul(v321));
		Assert.assertEquals(null, mat123_456_789.mul(mat100_010_001));
		Assert.assertEquals(null, mat123_456_789.mul(mat001_010_100));
	}
}
