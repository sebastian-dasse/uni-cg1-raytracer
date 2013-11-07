package raytracer.math;

/**
 * This immutable class represents a normal - i.e. a perpendicular vector - on a surface vector in three-dimensional 
 * space. Thus it has fields for its three components.
 * 
 * <p>The class <code>Normal3</code> includes methods for certain vector operations: scalar multiplication with a double 
 * value (not to be confused with the scalar product!), addition of another <code>Normal3</code> and scalar product 
 * (also known as dot product) with a <code>Vector3</code>.
 * 
 * <p>Passing a <code>null</code> object to a method in this class will cause an <code>IllegalArgumentException</code> 
 * to be thrown.
 * 
 * @author Sebastian Dass&eacute;
 */
public class Normal3 {
	/**
	 * The x coodinate of this <code>Normal3</code>.
	 */
	public final double x;
	/**
	 * The y coordinate of this <code>Normal3</code>.
	 */
	public final double y;
	/**
	 * The z coordinate of this <code>Normal3</code>.
	 */
	public final double z;
	
	// TODO: evtl. Parameter checken: Werte > Double.MAX_VALUE oder < -Double.MAX_VALUE oder +-Infinity oder NaN verbieten
	/**
	 * Constructs a new <code>Normal3</code> based on the three specified coordinates. The null vector (0, 0, 0) is prevented  
	 * from being constructed as <code>Normal3</code>.
	 *  
	 * @param x The x coordinate. Must be a double value other than +-Infinity or NaN.
	 * @param y The y coordinate. Must be a double value other than +-Infinity or NaN.
	 * @param z The z coordinate. Must be a double value other than +-Infinity or NaN.
	 */
	public Normal3(final double x, final double y, final double z) {
		if (x == 0 && y == 0 && z == 0) {
			throw new IllegalArgumentException("The null vector (0, 0, 0) is not a meaningful normal.");
		}
		if (isNotValid(x) || isNotValid(y) || isNotValid(z)) {
			throw new IllegalArgumentException("Only double values other than +-Infinity or NaN allowed.");
		}
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	// TODO: evtl. Parameter checken: Werte > Double.MAX_VALUE oder < -Double.MAX_VALUE oder +-Infinity oder NaN verbieten
	/**
	 * Calculates the scalar multiplication (not to be confused with the scalar product!) of this <code>Normal3</code> 
	 * with the specified double value.
	 * 
	 * @param c The scalar with which this <code>Normal3</code> is multiplied. Must be a double value other than 
	 * 			+-Infinity or NaN.
	 * @return	The resulting <code>Normal3</code>.
	 */
	public Normal3 mul(final double c) {
		if (isNotValid(c)) {
			throw new IllegalArgumentException("Only double values other than +-Infinity or NaN allowed.");
		}
		return new Normal3(x * c, 
						   y * c, 
						   z * c);
	}
	
	/**
	 * Adds the specified <code>Normal3</code> to this <code>Normal3</code>.
	 * 
	 * @param n The <code>Normal3</code> to be added. Must not be null.
	 * @return	The resulting <code>Normal3</code>.
	 */
	public Normal3 add(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		return new Normal3(x + n.x, 
						   y + n.y, 
						   z + n.z);
	}
	
	/**
	 * Calculates the scalar product (or dot product) of this <code>Normal3</code> with the specified 
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(x);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(y);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(z);
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
		Normal3 other = (Normal3) obj;
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
		return getClass().getSimpleName() + "[x = " + x + ", y = " + y + ", z = " + z + "]";
	}
	
	/**
	 * Checks if the specified double is NaN or infinite and therefore not a valid input. Returns true in this case. 
	 * 
	 * @param d The double value to be checked for validity.
	 * @return	True if not valid, otherwise false.
	 */
	private boolean isNotValid(final double d) {
		return Double.isNaN(d) || Double.isInfinite(d);
	}
}
