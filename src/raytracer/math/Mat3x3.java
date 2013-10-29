package raytracer.math;

public class Mat3x3 {
	public final double m11;
	public final double m12;
	public final double m13;
	public final double m21;
	public final double m22;
	public final double m23;
	public final double m31;
	public final double m32;
	public final double m33;
	public final double determinant;
	
	public Mat3x3(final double m11, final double m12, final double m13, 
				  final double m21, final double m22, final double m23, 
				  final double m31, final double m32, final double m33) {
		this.m11 = m11;
		this.m12 = m12;
		this.m13 = m13;
		this.m21 = m21;
		this.m22 = m22;
		this.m23 = m23;
		this.m31 = m31;
		this.m32 = m32;
		this.m33 = m33;
		determinant = m11 * m22 * m33 
					+ m12 * m23 * m31 
					+ m13 * m21 * m32 
					- m31 * m22 * m13 
					- m32 * m23 * m11 
					- m33 * m21 * m12;
	}
	
//	TODO: dot() die richtige Operation?
	public Mat3x3 mul(final Mat3x3 m) {
		return new Mat3x3(m11 * m.m11 + m12 * m.m21 + m13 * m.m31, 
						  m11 * m.m12 + m12 * m.m22 + m13 * m.m32, 
						  m11 * m.m13 + m12 * m.m23 + m13 * m.m33, 
						  m21 * m.m11 + m22 * m.m21 + m23 * m.m31,  
						  m21 * m.m12 + m22 * m.m22 + m23 * m.m32, 
						  m21 * m.m13 + m22 * m.m23 + m23 * m.m33, 
						  m31 * m.m11 + m32 * m.m21 + m33 * m.m31, 
						  m31 * m.m12 + m32 * m.m22 + m33 * m.m32, 
						  m31 * m.m13 + m32 * m.m23 + m33 * m.m33);
	}
	
//	TODO: dot() die richtige Operation? welche Version?
	public Vector3 mul(final Vector3 v) {
//		return new Vector3(m11 * v.x + m12 * v.y + m13 * v.z, 
//						   m11 * v.x + m12 * v.y + m13 * v.z, 
//						   m11 * v.x + m12 * v.y + m13 * v.z);
		
//		return new Vector3(v.dot(new Vector3(m11, m12, m13)), 
//						   v.dot(new Vector3(m21, m22, m23)), 
//						   v.dot(new Vector3(m31, m32, m33)));
		
		return new Vector3(v.dot(new Vector3(m11, m12, m13)), 
						   v.dot(new Vector3(m21, m22, m23)), 
						   v.dot(new Vector3(m31, m32, m33)));
	}
	
//	TODO: Delegation ok?
	public Point3 mul(final Point3 p) {
		Vector3 v = mul(new Vector3(p.x, p.y, p.z));
		return new Point3(v.x, v.y, v.z);
	}
	
//	TODO
	public Mat3x3 changeCol1(final Vector3 v) {
		return new Mat3x3(v.x, m12, m13, 
						  v.y, m22, m23, 
						  v.z, m32, m33);
	}
	
//	TODO
	public Mat3x3 changeCol2(final Vector3 v) {
		return new Mat3x3(m11, v.x, m13, 
				  		  m21, v.y, m23, 
				  		  m31, v.z, m33);
	}
	
//	TODO
	public Mat3x3 changeCol3(final Vector3 v) {
		return new Mat3x3(m11, m12, v.x, 
						  m21, m22, v.y, 
						  m31, m32, v.z);
	}
	
	public String toString() {
//		return getClass().getName() 
//				+ "[m11 = " + m11 + ", m12 = " + m12 + ", m13 = " + m13 
//				+ ", m21 = " + m21 + ", m22 = " + m21 + ", m23 = " + m23 
//				+ ", m31 = " + m31 + ", m32 = " + m32 + ", m33 = " + m33 + "]";
		
		// als Matrix mit Nachkommastellen
		return String.format("% 8f % 8f % 8f%n% 8f % 8f % 8f%n% 8f % 8f % 8f%n", m11, m12, m13, m21, m22, m23, m31, m32, m33);
		
		// als Matrix mit ohne Nachkommastellen
//		return String.format("% 8.0f % 8.0f % 8.0f%n% 8.0f % 8.0f % 8.0f%n% 8.0f % 8.0f % 8.0f%n", m11, m12, m13, m21, m22, m23, m31, m32, m33);
	}
}

//----
class Test {
	public static void main(String[] args) {
		Mat3x3 m = new Mat3x3(1, 0, 0, 
							  0, 1, 0,
							  0, 0, 1);
		System.out.println(m);
		System.out.println(m.determinant);
		
		m = m.changeCol1(new Vector3(9, 9, 9));
		System.out.println(m);
		m = m.changeCol2(new Vector3(6, 6, 6));
		System.out.println(m);
		m = m.changeCol3(new Vector3(3, 3, 3));
		System.out.println(m);
		System.out.println(m.determinant);
		
		Mat3x3 m2 = new Mat3x3(0.707, -0.707, 0, 
							   0.707,  0.707, 0, 
							   0,	   0,	  1);
		System.out.println(m2);
		System.out.println(m2.determinant);
	}
}
