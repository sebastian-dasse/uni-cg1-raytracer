package raytracer.math;

import static raytracer.math.MathUtil.isValid;

/**
 * This immutable class represents a matrix with four rows and four columns. Thus it has fields for its sixteen 
 * components, which are to be addressed as follows: the first index specifies the row, the second indicates the column 
 * of the particular element, like that: 
 * <blockquote><code>
 * m11 m12 m13 m14<br />
 * m21 m22 m23 m24<br />
 * m31 m32 m33 m34<br />
 * m41 m42 m43 m44</code></blockquote>
 * 
 * <p>The class <code>Mat4x4</code> includes methods to calculate the matrix product with a <code>Vector3</code>, 
 * a <code>Point3</code> or with another <code>Matrix4</code> object.
 * 
 * <p>Passing a <code>null</code> object to a method in this class will cause an <code>IllegalArgumentException</code> 
 * to be thrown.
 * 
 * @author Maxim Novichkov
 */
public class Mat4x4 {
	/**
	 * The m11 component of this <code>Mat4x4</code>.
	 */
	public final double m11;
	/**
	 * The m12 component of this <code>Mat4x4</code>.
	 */
	public final double m12;
	/**
	 * The m13 component of this <code>Mat4x4</code>.
	 */
	public final double m13;
	/**
	 * The m14 component of this <code>Mat4x4</code>.
	 */
	public final double m14;
	/**
	 * The m21 component of this <code>Mat4x4</code>.
	 */
	public final double m21;
	/**
	 * The m22 component of this <code>Mat4x4</code>.
	 */
	public final double m22;
	/**
	 * The m23 component of this <code>Mat4x4</code>.
	 */
	public final double m23;
	/**
	 * The m24 component of this <code>Mat4x4</code>.
	 */
	public final double m24;
	/**
	 * The m31 component of this <codeMat4x4</code>.
	 */
	public final double m31;
	/**
	 * The m32 component of this <code>Mat4x4</code>.
	 */
	public final double m32;
	/**
	 * The m33 component of this <code>Mat4x4</code>.
	 */
	public final double m33;
	/**
	 * The m34 component of this <code>Mat4x4</code>.
	 */
	public final double m34;
	/**
	 * The m41 component of this <code>Mat4x4</code>.
	 */
	public final double m41;
	/**
	 * The m42 component of this <code>Mat4x4</code>.
	 */
	public final double m42;
	/**
	 * The m43 component of this <code>Mat4x4</code>.
	 */
	public final double m43;
	/**
	 * The m44 component of this <code>Mat4x4</code>.
	 */
	public final double m44;
	/**
	 * Constructs a new <code>Mat4x4</code> based on the nine specified components.
	 * 
	 * @param m11 The m11 component. Must be a double value other than +-Infinity or NaN.
	 * @param m12 The m12 component. Must be a double value other than +-Infinity or NaN.
	 * @param m13 The m13 component. Must be a double value other than +-Infinity or NaN.
	 * @param m14 The m14 component. Must be a double value other than +-Infinity or NaN.
	 * @param m21 The m21 component. Must be a double value other than +-Infinity or NaN.
	 * @param m22 The m22 component. Must be a double value other than +-Infinity or NaN.
	 * @param m23 The m23 component. Must be a double value other than +-Infinity or NaN.
	 * @param m24 The m24 component. Must be a double value other than +-Infinity or NaN.
	 * @param m31 The m31 component. Must be a double value other than +-Infinity or NaN.
	 * @param m32 The m32 component. Must be a double value other than +-Infinity or NaN.
	 * @param m33 The m33 component. Must be a double value other than +-Infinity or NaN.
	 * @param m34 The m34 component. Must be a double value other than +-Infinity or NaN.
	 * @param m41 The m41 component. Must be a double value other than +-Infinity or NaN.
	 * @param m42 The m42 component. Must be a double value other than +-Infinity or NaN.
	 * @param m43 The m43 component. Must be a double value other than +-Infinity or NaN.
	 * @param m44 The m44 component. Must be a double value other than +-Infinity or NaN.
	 */
	public Mat4x4(final double m11, final double m12, final double m13, final double m14, 
			      final double m21, final double m22, final double m23, final double m24, 
			      final double m31, final double m32, final double m33, final double m34,
			      final double m41, final double m42, final double m43, final double m44) {
		if (!(isValid(m11) && isValid(m12) && isValid(m13) && isValid(m14) && 
			  isValid(m21) && isValid(m22) && isValid(m23) && isValid(m24) &&
			  isValid(m31) && isValid(m32) && isValid(m33) && isValid(m34) &&
			  isValid(m41) && isValid(m42) && isValid(m43) && isValid(m44))) {
			throw new IllegalArgumentException("Only double values other than +-Infinity or NaN are allowed.");
		}
		this.m11 = m11;
		this.m12 = m12;
		this.m13 = m13;
		this.m14 = m14;
		this.m21 = m21;
		this.m22 = m22;
		this.m23 = m23;
		this.m24 = m24;
		this.m31 = m31;
		this.m32 = m32;
		this.m33 = m33;
		this.m34 = m34;
		this.m41 = m41;
		this.m42 = m42;
		this.m43 = m43;
		this.m44 = m44;
	}
	
	/**
	 * Calculates the matrix product of this matrix with the specified <code>Vector3</code>.
	 * 
	 * @param v The <code>Vector3</code> with which	this matrix is multiplied. Must not be <code>null</code>.
	 * @return	The resulting <code>Vector3</code>.
	 */
	public Vector3 mul(final Vector3 v){
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
	public Point3 mul(final Point3 p){
		if (p == null) {
			throw new IllegalArgumentException("The parameter 'p' must not be null.");
		}
		return new Point3(m11 * p.x + m12 * p.y + m13 * p.z + m14,
						  m21 * p.x + m22 * p.y + m23 * p.z + m24,
						  m31 * p.x + m32 * p.y + m33 * p.z + m34);
	}
	/**
	 * Calculates the matrix product of this matrix with the specified <code>Mat4x4</code>.
	 * 
	 * @param m The other matrix with which this matrix is multiplied. Must not be <code>null</code>.
	 * @return	The resulting <code>Mat4x4</code>.
	 */
	public Mat4x4 mul(final Mat4x4 m){
		if (m == null) {
			throw new IllegalArgumentException("The parameter 'm' must not be null.");
		}
		return new Mat4x4(m11*m.m11 + m12*m.m21 + m13*m.m31 + m14*m.m41, 
						  m11*m.m12 + m12*m.m22 + m13*m.m32 + m14*m.m42, 
						  m11*m.m13 + m12*m.m23 + m13*m.m33 + m14*m.m43, 
						  m11*m.m14 + m12*m.m24 + m13*m.m34 + m14*m.m44,
				
						  m21*m.m11 + m22*m.m21 + m23*m.m31 + m24*m.m41, 
						  m21*m.m12 + m22*m.m22 + m23*m.m32 + m24*m.m42, 
						  m21*m.m13 + m22*m.m23 + m23*m.m33 + m24*m.m43, 
						  m21*m.m14 + m22*m.m24 + m23*m.m34 + m24*m.m44,
						  
                          m31*m.m11 + m32*m.m21 + m33*m.m31 + m34*m.m41, 
                          m31*m.m12 + m32*m.m22 + m33*m.m32 + m34*m.m42,
                          m31*m.m13 + m32*m.m23 + m33*m.m33 + m34*m.m43, 
                          m31*m.m14 + m32*m.m24 + m33*m.m34 + m34*m.m44,
                          
                          m41*m.m11 + m42*m.m21 + m43*m.m31 + m44*m.m41, 
                          m41*m.m12 + m42*m.m22 + m43*m.m32 + m44*m.m42, 
                          m41*m.m13 + m42*m.m23 + m43*m.m33 + m44*m.m43, 
                          m41*m.m14 + m42*m.m24 + m43*m.m34 + m44*m.m44);
	}
	
	/**
	 * Calculate transposed <code>Mat4x4</code>.
	 *  
	 * @return The resulted transposed <code>Mat4x4</code>.
	 */
	public Mat4x4 transposed(){
		return new Mat4x4(m11, m21, m31, m41,
						  m12, m22, m32, m42,
						  m13, m23, m33, m43,
						  m14, m24, m34, m44);
	}
	
	public String toString() {
		return getClass().getSimpleName() 
				+ "[\n\tm11 = " + m11 + ", m12 = " + m12 + ", m13 = " + m13 + ", m14 = " + m14 + ",\n" 
				+ "\tm21 =  " + m21 + ", m22 = " + m22 + ", m23 = " + m23 + ", m24 = " + m24 + ",\n" 
				+ "\tm31 =  " + m31 + ", m32 = " + m32 + ", m33 = " + m33 + ", m34 = " + m34 + ",\n"
				+ "\tm41 =  " + m41 + ", m42 = " + m42 + ", m43 = " + m43 + ", m44 = " + m44 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(m11);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m12);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m13);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m14);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m21);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m22);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m23);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m24);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m31);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m32);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m33);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m34);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m41);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m42);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m43);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(m44);
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
		Mat4x4 other = (Mat4x4) obj;
		if (Double.doubleToLongBits(m11) != Double.doubleToLongBits(other.m11))
			return false;
		if (Double.doubleToLongBits(m12) != Double.doubleToLongBits(other.m12))
			return false;
		if (Double.doubleToLongBits(m13) != Double.doubleToLongBits(other.m13))
			return false;
		if (Double.doubleToLongBits(m14) != Double.doubleToLongBits(other.m14))
			return false;
		if (Double.doubleToLongBits(m21) != Double.doubleToLongBits(other.m21))
			return false;
		if (Double.doubleToLongBits(m22) != Double.doubleToLongBits(other.m22))
			return false;
		if (Double.doubleToLongBits(m23) != Double.doubleToLongBits(other.m23))
			return false;
		if (Double.doubleToLongBits(m24) != Double.doubleToLongBits(other.m24))
			return false;
		if (Double.doubleToLongBits(m31) != Double.doubleToLongBits(other.m31))
			return false;
		if (Double.doubleToLongBits(m32) != Double.doubleToLongBits(other.m32))
			return false;
		if (Double.doubleToLongBits(m33) != Double.doubleToLongBits(other.m33))
			return false;
		if (Double.doubleToLongBits(m34) != Double.doubleToLongBits(other.m34))
			return false;
		if (Double.doubleToLongBits(m41) != Double.doubleToLongBits(other.m41))
			return false;
		if (Double.doubleToLongBits(m42) != Double.doubleToLongBits(other.m42))
			return false;
		if (Double.doubleToLongBits(m43) != Double.doubleToLongBits(other.m43))
			return false;
		if (Double.doubleToLongBits(m44) != Double.doubleToLongBits(other.m44))
			return false;
		return true;
	}
}
