package raytracer.math;

/**
 * This immutable class represents a point in three-dimensional space.
 * <p>
 * The class <code>Point3</code> includes methods for certain vector operations: subtraction of another 
 * <code>Point3</code> and subtraction and addition of a <code>Vector3</code> object.
 * <p>
 * Passing a <code>null</code> object to a method in this class will cause an <code>IllegalArgumentException</code> 
 * to be thrown.
 * 
 * @author Sebastian Dassé
 */
public class Point3 {
	public final double x;
	public final double y;
	public final double z;
	
	// TODO: evtl. Parameter checken: Werte > Double.MAX_VALUE oder < -Double.MAX_VALUE oder +-Infinity oder NaN verbieten
	public Point3(final double x, final double y, final double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Vector3 sub(final Point3 p) {
		if (p == null) {
			throw new IllegalArgumentException("The parameter 'p' must not be null.");
		}
		return new Vector3(x - p.x, 
						   y - p.y, 
						   z - p.z);
	}
	
	public Point3 sub(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Point3(x - v.x, 
						  y - v.y, 
						  z - v.z);
	}
	
	public Point3 add(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Point3(x + v.x, 
						  y + v.y, 
						  z + v.z);
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
		Point3 other = (Point3) obj;
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
