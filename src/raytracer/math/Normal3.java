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
 * @author Sebastian Dassé
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
	 * Constructs a new <code>Normal3</code> based on the three specified coordinates.
	 *  
	 * @param x The x coordinate. Must be a double value other than +-Infinity or NaN.
	 * @param y The y coordinate. Must be a double value other than +-Infinity or NaN.
	 * @param z The z coordinate. Must be a double value other than +-Infinity or NaN.
	 */
	public Normal3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	// TODO: evtl. Parameter checken: Werte > Double.MAX_VALUE oder < -Double.MAX_VALUE oder +-Infinity oder NaN verbieten
	public Normal3 mul(final double n) {
		return new Normal3(x * n, 
						   y * n, 
						   z * n);
	}
	
	public Normal3 add(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		return new Normal3(x + n.x, 
						   y + n.y, 
						   z + n.z);
	}
	
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
	public boolean equals(Object obj) {
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
	
	public String toString() {
		return getClass().getSimpleName() + "[x = " + x + ", y = " + y + ", z = " + z + "]";
	}
}
