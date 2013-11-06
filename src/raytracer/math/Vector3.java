package raytracer.math;

/**
 * This immutable class represents a vector in three-dimensional space. Thus it has fields for its three components and 
 * an additional field in which its magnitude is stored.
 * 
 * <p>The class <code>Vector3</code> provides various methods for vector operations, which include addition and 
 * subtraction of two vectors as well as scalar multiplication, scalar (or dot) product (which are not to be confused!) 
 * and cross product (or vector product). There is a method that calculates the vector resulting from the reflection of 
 * a vector on a plane given through its normal. Finally there is also method a for normalization and another one for 
 * the conversion from <code>Vector3</code> to <code>Normal3</code>.
 * 
 * <p>Passing a <code>null</code> object to a method in this class will cause an <code>IllegalArgumentException</code> 
 * to be thrown.
 * 
 * @author Sebastian Dass&eacute;
 */
public class Vector3 {
	/**
	 * The x coordinate of this <code>Vector3</code>.
	 */
	public final double x;
	/**
	 * The y coordinate of this <code>Vector3</code>.
	 */
	public final double y;
	/**
	 * The z coordinate of this <code>Vector3</code>.
	 */
	public final double z;
	/**
	 * The magnitude of this <code>Vector3</code>.
	 */
	public final double magnitude;
	
	// TODO: evtl. Parameter checken: Werte > Double.MAX_VALUE oder < -Double.MAX_VALUE oder +-Infinity oder NaN verbieten
	/**
	 * Constructs a new <code>Vector3</code> based on the three specified coordinates.
	 * 
	 * @param x The x coordinate. Must be a double value other than +-Infinity or NaN.
	 * @param y The y coordinate. Must be a double value other than +-Infinity or NaN.
	 * @param z The z coordinate. Must be a double value other than +-Infinity or NaN.
	 */
	public Vector3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		magnitude = Math.sqrt(x * x + y * y + z * z);
	}
	
	/**
	 * Calculates the <code>Vector3</code> resulting from the addition of this <code>Vector3</code> and the specified 
	 * <code>Vector3</code>.
	 * 
	 * @param v The <code>Vector3</code> added to this vector. Must no be null.
	 * @return	The resulting <code>Vector3</code>.
	 */
	public Vector3 add(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Vector3(x + v.x, 
						   y + v.y, 
						   z + v.z);
	}
	
	/**
	 * Calculates the <code>Vector3</code> resulting from the addition of this <code>Vector3</code> and the specified 
	 * <code>Normal3</code>.
	 * 
	 * @param n The <code>Normal3</code> added to this vector. Must not be null.
	 * @return	The resulting <code>Vector3</code>.
	 */
	public Vector3 add(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		return new Vector3(x + n.x, 
						   y + n.y, 
						   z + n.z);
	}
	
	/**
	 * Calculates the <code>Vector3</code> resulting from the subtraction of the specified <code>Normal3</code> from 
	 * this <code>Vector3</code>.
	 * 
	 * @param n The <code>Normal3</code> subtracted from this vector. Must not be null.
	 * @return	The resulting <code>Vector3</code>.
	 */
	public Vector3 sub(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		return new Vector3(x - n.x, 
						   y - n.y, 
						   z - n.z);
	}
	
	// TODO: evtl. Parameter checken: Werte > Double.MAX_VALUE oder < -Double.MAX_VALUE oder +-Infinity oder NaN verbieten
	/**
	 * Calculates the scalar multiplication (not to be confused with the scalar product!) of this <code>Vector3</code> 
	 * with the specified double value.
	 * 
	 * @param c The scalar with which this <code>Vector3</code> is multiplied. Must be a double value other than 
	 * 			+-Infinity or NaN.
	 * @return	The resulting <code>Vector3</code>.
	 */
	public Vector3 mul(double c) {
		return new Vector3(x * c, 
						   y * c, 
						   z * c);
	}
	
	/**
	 * Calculates the scalar product (or dot product) of this <code>Vector3</code> with the specified other 
	 * <code>Vector3</code>.
	 * 
	 * @param v The other vector of the scalar product. Must not be null.
	 * @return	The resulting scalar.
	 */
	public double dot(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return x * v.x + y * v.y + z * v.z;
	}
	
	/**
	 * Calculates the scalar product (or dot product) of this <code>Vector3</code> with the specified 
	 * <code>Normal3</code>.
	 * 
	 * @param v The other vector of the scalar product. Must not be null.
	 * @return	The resulting scalar.
	 */
	public double dot(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		return x * n.x + y * n.y + z * n.z;
	}
	
	/**
	 * Returns the normalized version of this vector, i.e. the vector with the same direction as this vector but with 
	 * magnitude 1. Cannot be invoked on the null vector.
	 * 
	 * @return The normalized version of this vector, i.e. the vector with the same direction as this vector but with 
	 * 		   magnitude 1.
	 */
	public Vector3 normalized() {
		if (magnitude == 0) {
			throw new ArithmeticException("Cannot normalize a vector with magnitude = 0.");
		}
		return (magnitude == 1) ? this : new Vector3(x / magnitude, 
						   							 y / magnitude, 
						   							 z / magnitude);
	}
	
	/**
	 * Converts this <code>Vector3</code> to <code>Normal3</code>. Cannot be invoked on the null vector.
	 * 
	 * @return The <code>Normal3</code>.
	 */
	public Normal3 asNormal() {
		if (magnitude == 0) {
			throw new RuntimeException("The null vector (0, 0, 0) is not a meaningful normal.");
		}
		return new Normal3(x, y, z);
	}
	
//	Anmerkung: evtl. Methode für das Skalarprodukt benutzen
	/**
	 * Calculates the <code>Vector3</code> resulting from the reflection of this vector on a plane given through its 
	 * specified <code>Normal3</code> n.
	 * 
	 * @param n The <code>Normal3</code> of the plane on which this vector is reflected. Must not be null.
	 * @return	The resulting reflected <code>Vector3</code>.
	 */
	public Vector3 reflectedOn(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		// Ebene		<x - u, q> mit u = 0 und q := n
		// Gerade		g(r) = a + rq mit a := this. Die Gerade durch a und den Lotfußpunkt von a auf der Ebene
		// Parameter	r = -<a - u, q> /|q|², d.h. für den Nenner wird a in die negative Ebenengleichung eingesetzt
		// Spiegelpunkt	a' = g(2rp)
		final double r = -(x * n.x + y * n.y + z * n.z) 
						/ (n.x * n.x + n.y * n.y + n.z * n.z);
		return this.add(n.mul(2 * r));
	}
	
	/**
	 * Calculates the cross product (or vector product) of this vector with the specified <code>Vector3</code>. 
	 * The resulting <code>Vector3</code> is perpendicular to both, this vector and the specified vector and thus 
	 * perpendicular to the plane formed by them. In other words the resulting vector is a normal vector to the plane 
	 * that contains the two vectors. 
	 * 
	 * @param v The other <code>Vector3</code> with which the cross product is formed.
	 * @return	The resulting <code>Vector3</code>, perpendicular to both, this and the specified vector.
	 */
	public Vector3 x(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Vector3(y * v.z - z * v.y, 
						   z * v.x - x * v.z, 
						   x * v.y - y * v.x);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(magnitude);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
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
		Vector3 other = (Vector3) obj;
		if (Double.doubleToLongBits(magnitude) != Double
				.doubleToLongBits(other.magnitude))
			return false;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		if (Double.doubleToLongBits(z) != Double.doubleToLongBits(other.z))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[x = " + x + ", y = " + y + ", z = " + z + ", magnitude = " + magnitude + "]";
	}
}
