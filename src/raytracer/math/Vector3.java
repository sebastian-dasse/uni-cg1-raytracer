package raytracer.math;

/**
 * This immutable class represents a vector in three-dimensional space.
 * <p>
 * In addition the class <code>Vector3</code> has a field in which its magnitude is stored.
 * <p>
 * The class <code>Mat3x3</code> provides various methods for vector operations, which include addition and subtraction 
 * of two vectors as well as scalar multiplication, scalar (or dot) product (which are not to be confused!) and 
 * cross product (or vector product).
 * <p>
 * There is a method that calculates the vector resulting from the reflection of a vector on a plane given through its 
 * normal. Finally there is also method a for normalization and another one for the conversion from <code>Vector3</code> 
 * to <code>Normal3</code>.
 * <p>
 * Passing a <code>null</code> object to a method in this class will cause an <code>IllegalArgumentException</code> to be thrown.
 * @author Sebastian Dassé
 */
public class Vector3 {
	public final double x;
	public final double y;
	public final double z;
	public final double magnitude;
	
	// TODO: evtl. Parameter checken: Werte > Double.MAX_VALUE oder < -Double.MAX_VALUE oder +-Infinity oder NaN verbieten
	public Vector3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		magnitude = Math.sqrt(x * x + y * y + z * z);
	}
	
	public Vector3 add(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Vector3(x + v.x, 
						   y + v.y, 
						   z + v.z);
	}
	
	public Vector3 add(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		return new Vector3(x + n.x, 
						   y + n.y, 
						   z + n.z);
	}
	
	public Vector3 sub(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		return new Vector3(x - n.x, 
						   y - n.y, 
						   z - n.z);
	}
	
	// TODO: evtl. Parameter checken: Werte > Double.MAX_VALUE oder < -Double.MAX_VALUE oder +-Infinity oder NaN verbieten
	public Vector3 mul(double c) {
		return new Vector3(x * c, 
						   y * c, 
						   z * c);
	}
	
	public double dot(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return x * v.x + y * v.y + z * v.z;
	}
	
	public double dot(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		return x * n.x + y * n.y + z * n.z;
	}
	
	public Vector3 normalized() {
		return new Vector3(x / magnitude, 
						   y / magnitude, 
						   z / magnitude);
	}
	
	public Normal3 asNormal() {
		return new Normal3(x, y, z);
	}
	
//	TODO: FRAGE: was soll hier passieren?
//	zwei Anmerkungen: 
//		- Skalarprodukt benutzen; 
//		- evtl. nicht magnitude^2 sondern direkt ausrechnen für höhere Genauigkeit
	public Vector3 reflectedOn(final Normal3 n) {
		if (n == null) {
			throw new IllegalArgumentException("The parameter 'n' must not be null.");
		}
		final double r = -(x * n.x 
						 + y * n.y 
						 + z * n.z) / (magnitude * magnitude); 
		return new Vector3(x + r * n.x, 
						   y + r * n.y, 
						   z + r * n.z);
	}
	
	public Vector3 x(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Vector3(y * v.z - z * v.y, 
						   z * v.x - x * v.z, 
						   x * v.y - y * v.x);
	}
	
	public String toString() {
		return getClass().getSimpleName() + "[x = " + x + ", y = " + y + ", z = " + z + ", magnitude = " + magnitude + "]";
	}
}
