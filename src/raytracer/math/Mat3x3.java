package raytracer.math;

/**
 * This immutable class represents a matrix with 3 rows and 3 columns. Thus it has 9 elements which are to be addressed 
 * as follows: the first index specifies the row, the second indicates the column of the particular element, like that: 
 * <blockquote><code>
 * m11 m12 m13 <br />
 * m21 m22 m23 <br />
 * m31 m32 m33</code></blockquote>
 * In addition the class <code>Mat3x3</code> has a field in which its determinant stored.
 * <p>
 * The class <code>Mat3x3</code> includes methods to calculate the matrix product with a <code>Vector3</code>, 
 * a <code>Point3</code> or with another <code>Matrix3</code> object as well as methods intended to change particular 
 * columns of the <code>Mat3x3</code> object.
 * <p>
 * Passing a <code>null</code> object to a method in this class will cause an <code>IllegalArgumentException</code> 
 * to be thrown.
 * 
 * @author Sebastian Dassé
 */
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
	
	// TODO: evtl. Parameter checken: Werte > Double.MAX_VALUE oder < -Double.MAX_VALUE oder +-Infinity oder NaN verbieten
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
	
	public Mat3x3 mul(final Mat3x3 m) {
		if (m == null) {
			throw new IllegalArgumentException("The parameter 'm' must not be null.");
		}
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
	
//	TODO: welche Version?
	public Vector3 mul(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
//		return new Vector3(m11 * v.x + m12 * v.y + m13 * v.z, 
//						   m11 * v.x + m12 * v.y + m13 * v.z, 
//						   m11 * v.x + m12 * v.y + m13 * v.z);
		
//		return new Vector3(v.dot(new Vector3(m11, m12, m13)), 
//						   v.dot(new Vector3(m21, m22, m23)), 
//						   v.dot(new Vector3(m31, m32, m33)));
		
		return new Vector3(new Vector3(m11, m12, m13).dot(v), 
						   new Vector3(m21, m22, m23).dot(v), 
						   new Vector3(m31, m32, m33).dot(v));
	}
	
//	TODO: Delegation an mul(final Vector3 v) ok?  -  evtl. lieber nicht?
	public Point3 mul(final Point3 p) {
		if (p == null) {
			throw new IllegalArgumentException("The parameter 'p' must not be null.");
		}
		Vector3 v = mul(new Vector3(p.x, p.y, p.z));
		return new Point3(v.x, v.y, v.z);
	}
	
	public Mat3x3 changeCol1(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Mat3x3(v.x, m12, m13, 
						  v.y, m22, m23, 
						  v.z, m32, m33);
	}
	
	public Mat3x3 changeCol2(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Mat3x3(m11, v.x, m13, 
				  		  m21, v.y, m23, 
				  		  m31, v.z, m33);
	}
	
	public Mat3x3 changeCol3(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Mat3x3(m11, m12, v.x, 
						  m21, m22, v.y, 
						  m31, m32, v.z);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(determinant);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m11);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m12);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m13);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m21);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m22);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m23);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m31);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m32);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m33);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Mat3x3 other = (Mat3x3) obj;
		if (Double.doubleToLongBits(determinant) != Double.doubleToLongBits(other.determinant))
			return false;
		if (Double.doubleToLongBits(m11) != Double.doubleToLongBits(other.m11))
			return false;
		if (Double.doubleToLongBits(m12) != Double.doubleToLongBits(other.m12))
			return false;
		if (Double.doubleToLongBits(m13) != Double.doubleToLongBits(other.m13))
			return false;
		if (Double.doubleToLongBits(m21) != Double.doubleToLongBits(other.m21))
			return false;
		if (Double.doubleToLongBits(m22) != Double.doubleToLongBits(other.m22))
			return false;
		if (Double.doubleToLongBits(m23) != Double.doubleToLongBits(other.m23))
			return false;
		if (Double.doubleToLongBits(m31) != Double.doubleToLongBits(other.m31))
			return false;
		if (Double.doubleToLongBits(m32) != Double.doubleToLongBits(other.m32))
			return false;
		if (Double.doubleToLongBits(m33) != Double.doubleToLongBits(other.m33))
			return false;
		return true;
	}

	public String toString() {
		return getClass().getSimpleName() 
				+ "[\tm11 = " + m11 + ", m12 = " + m12 + ", m13 = " + m13 + ",\n" 
				+ "\tm21 = " + m21 + ", m22 = " + m21 + ", m23 = " + m23 + ",\n" 
				+ "\tm31 = " + m31 + ", m32 = " + m32 + ", m33 = " + m33 + ", " 
				+ "determinant = " + determinant + "]";
	}
}
