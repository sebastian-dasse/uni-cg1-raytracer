package raytracer.dirty;

import raytracer.math.*;

public class DirtyTests {
	public static void main(String[] args) {
		Mat3x3 m = new Mat3x3(1, 0, 0, 
							  0, 1, 0,
							  0, 0, 1);
		System.out.println(m);
//		System.out.println(m.determinant);
//		
//		m = m.changeCol1(new Vector3(9, 9, 9));
//		System.out.println(m);
//		m = m.changeCol2(new Vector3(6, 6, 6));
//		System.out.println(m);
//		m = m.changeCol3(new Vector3(3, 3, 3));
//		System.out.println(m);
//		System.out.println(m.determinant);
//		
//		Mat3x3 m2 = new Mat3x3(0.707, -0.707, 0, 
//							   0.707,  0.707, 0, 
//							   0,	   0,	  1);
//		
//		System.out.println(m2);
//		System.out.println(m2.determinant);
//		
		System.out.println(new Normal3(1, 2, 3));
		System.out.println(new Point3(1, 2, 3));
		System.out.println(new Vector3(Math.sqrt(3)/3, Math.sqrt(3)/3, Math.sqrt(3)/3));
		
		Vector3 v = new Vector3(0, 0, 0);
		System.out.println(v.magnitude);
//		System.out.println(v.normalized());
//		System.out.println(v.asNormal());
	}
}
