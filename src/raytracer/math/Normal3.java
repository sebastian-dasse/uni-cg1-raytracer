package raytracer.math;

/**
 * This immutable class represents a normal - i.e. a perpendicular vector - on a surface vector in three-dimensional space.
 * <p>
 * The class <code>Normal3</code> includes methods for certain vector operations: scalar multiplication with a double 
 * value (not to be confused with the scalar product!), addition of another <code>Normal3</code> and scalar product 
 * (also known as dot product) with a <code>Vector3</code>.
 * <p>
 * Passing a <code>null</code> object to a method in this class will cause an <code>IllegalArgumentException</code> to be thrown.
 * @author Sebastian Dassé
 */
public class Normal3 {
	public final double x;
	public final double y;
	public final double z;
	
	// TODO: evtl. Parameter checken: Werte > Double.MAX_VALUE oder < -Double.MAX_VALUE oder +-Infinity oder NaN verbieten
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
	
	public String toString() {
		return getClass().getSimpleName() + "[x = " + x + ", y = " + y + ", z = " + z + "]";
	}
}
