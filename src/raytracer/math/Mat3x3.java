package raytracer.math;

import static raytracer.math.MathUtil.isValid;

/**
 * This immutable class represents a matrix with three rows and three columns. Thus it has fields for its nine 
 * components, which are to be addressed as follows: the first index specifies the row, the second indicates the column 
 * of the particular element, like that: 
 * <blockquote><code>
 * m11 m12 m13 <br />
 * m21 m22 m23 <br />
 * m31 m32 m33</code></blockquote>
 * In addition the class <code>Mat3x3</code> has a field in which its determinant stored.
 * 
 * <p>The class <code>Mat3x3</code> includes methods to calculate the matrix product with a <code>Vector3</code>, 
 * a <code>Point3</code> or with another <code>Matrix3</code> object as well as methods intended to change particular 
 * columns of the <code>Mat3x3</code> object.
 * 
 * <p>Passing a <code>null</code> object to a method in this class will cause an <code>IllegalArgumentException</code> 
 * to be thrown.
 * 
 * @author Sebastian Dass&eacute;
 */
public class Mat3x3 {
	/**
	 * The m11 component of this <code>Mat3x3</code>.
	 */
	public final double m11;
	/**
	 * The m12 component of this <code>Mat3x3</code>.
	 */
	public final double m12;
	/**
	 * The m13 component of this <code>Mat3x3</code>.
	 */
	public final double m13;
	/**
	 * The m21 component of this <code>Mat3x3</code>.
	 */
	public final double m21;
	/**
	 * The m22 component of this <code>Mat3x3</code>.
	 */
	public final double m22;
	/**
	 * The m23 component of this <code>Mat3x3</code>.
	 */
	public final double m23;
	/**
	 * The m31 component of this <code>Mat3x3</code>.
	 */
	public final double m31;
	/**
	 * The m32 component of this <code>Mat3x3</code>.
	 */
	public final double m32;
	/**
	 * The m33 component of this <code>Mat3x3</code>.
	 */
	public final double m33;
	/**
	 * The determinant of this <code>Mat3x3</code>.
	 */
	public final double determinant;
	
	/**
	 * Constructs a new <code>Mat3x3</code> based on the nine specified components.
	 * 
	 * @param m11 The m11 component. Must be a double value other than +-Infinity or NaN.
	 * @param m12 The m12 component. Must be a double value other than +-Infinity or NaN.
	 * @param m13 The m13 component. Must be a double value other than +-Infinity or NaN.
	 * @param m21 The m21 component. Must be a double value other than +-Infinity or NaN.
	 * @param m22 The m22 component. Must be a double value other than +-Infinity or NaN.
	 * @param m23 The m23 component. Must be a double value other than +-Infinity or NaN.
	 * @param m31 The m31 component. Must be a double value other than +-Infinity or NaN.
	 * @param m32 The m32 component. Must be a double value other than +-Infinity or NaN.
	 * @param m33 The m33 component. Must be a double value other than +-Infinity or NaN.
	 */
	public Mat3x3(final double m11, final double m12, final double m13, 
				  final double m21, final double m22, final double m23, 
				  final double m31, final double m32, final double m33) {
		if (!(isValid(m11) && isValid(m12) && isValid(m13) && 
			  isValid(m21) && isValid(m22) && isValid(m23) && 
			  isValid(m31) && isValid(m32) && isValid(m33))) {
			throw new IllegalArgumentException("Only double values other than +-Infinity or NaN are allowed.");
		}
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
	
	/**
	 * Calculates the matrix product of this matrix with the specified <code>Mat3x3</code>.
	 * 
	 * @param m The other matrix with which this matrix is multiplied. Must not be <code>null</code>.
	 * @return	The resulting <code>Mat3x3</code>.
	 */
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
	
	/**
	 * Calculates the matrix product of this matrix with the specified <code>Vector3</code>.
	 * 
	 * @param v The <code>Vector3</code> with which	this matrix is multiplied. Must not be <code>null</code>.
	 * @return	The resulting <code>Vector3</code>.
	 */
	public Vector3 mul(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Vector3(m11 * v.x + m12 * v.y + m13 * v.z, 
						   m21 * v.x + m22 * v.y + m23 * v.z, 
						   m31 * v.x + m32 * v.y + m33 * v.z);
	}
	
	/**
	 * Calculates the matrix product of this matrix with the specified <code>Point3</code>.
	 * 
	 * @param p The <code>Point3</code> with which this matrix is multiplied. Must not be <code>null</code>.
	 * @return	The resulting <code>Point3</code>;
	 */
	public Point3 mul(final Point3 p) {
		if (p == null) {
			throw new IllegalArgumentException("The parameter 'p' must not be null.");
		}
		return new Point3(m11 * p.x + m12 * p.y + m13 * p.z, 
						  m21 * p.x + m22 * p.y + m23 * p.z, 
						  m31 * p.x + m32 * p.y + m33 * p.z);
	}
	
	/**
	 * Replaces the first column of this matrix by the specified <code>Vector3</code>.
	 * 
	 * @param v The replacement <code>Vector3</code> for the first column. Must not be <code>null</code>.
	 * @return	The resulting <code>Mat3x3</code>.
	 */
	public Mat3x3 changeCol1(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Mat3x3(v.x, m12, m13, 
						  v.y, m22, m23, 
						  v.z, m32, m33);
	}
	
	/**
	 * Replaces the second column of this matrix by the specified <code>Vector3</code>.
	 * 
	 * @param v The replacement <code>Vector3</code> for the second column. Must not be <code>null</code>.
	 * @return	The resulting <code>Mat3x3</code>.
	 */
	public Mat3x3 changeCol2(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Mat3x3(m11, v.x, m13, 
				  		  m21, v.y, m23, 
				  		  m31, v.z, m33);
	}
	
	/**
	 * Replaces the third column of this matrix by the specified <code>Vector3</code>.
	 * 
	 * @param v The replacement <code>Vector3</code> for the third column. Must not be <code>null</code>.
	 * @return	The resulting <code>Mat3x3</code>.
	 */
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
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Mat3x3 other = (Mat3x3) obj;
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
	
	@Override
	public String toString() {
		return getClass().getSimpleName() 
				+ "[\tm11 = " + m11 + ", m12 = " + m12 + ", m13 = " + m13 + ",\n" 
				+ "\tm21 = " + m21 + ", m22 = " + m22 + ", m23 = " + m23 + ",\n" 
				+ "\tm31 = " + m31 + ", m32 = " + m32 + ", m33 = " + m33 + ", " 
				+ "determinant = " + determinant + "]";
	}
}
