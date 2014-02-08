package raytracer.math;

import static raytracer.math.MathUtil.isValid;

/**
 * This immutable class represents a point in three-dimensional space. Thus it has fields for its three components.
 * 
 * <p>The class <code>Point3</code> includes methods for certain vector operations: subtraction of another 
 * <code>Point3</code> and subtraction and addition of a <code>Vector3</code> object.
 * 
 * <p>Passing a <code>null</code> object to a method in this class will cause an <code>IllegalArgumentException</code> 
 * to be thrown.
 * 
 * @author Sebastian Dass&eacute;
 */
public class Point3 {
	/**
	 * The x coordinate of this <code>Point3</code>.
	 */
	public final double x;
	/**
	 * The y coordinate of this <code>Point3</code>.
	 */
	public final double y;
	/**
	 * The z coordinate of this <code>Point3</code>.
	 */
	public final double z;
	
	/**
	 * Constructs a new <code>Point3</code> based on the three specified coordinates.
	 * 
	 * @param x The x coordinate. Must be a double value other than +-Infinity or NaN.
	 * @param y The y coordinate. Must be a double value other than +-Infinity or NaN.
	 * @param z The z coordinate. Must be a double value other than +-Infinity or NaN.
	 */
	public Point3(final double x, final double y, final double z) {
		if (!(isValid(x) && isValid(y) && isValid(z))) {
			throw new IllegalArgumentException("Only double values other than +-Infinity or NaN are allowed.");
		}
		
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	/**
	 * Calculates the vector between two points as a result of their subtraction. More precisely the specified 
	 * <code>Point3</code> p is subtracted from this <code>Point3</code> resulting in a <code>Vector3</code> pointing 
	 * from the specified point towards this point.
	 * 
	 * @param p The other <code>Point3</code>, i.e. the starting point of the resulting vector. Must not be <code>null</code>.
	 * @return	The resulting <code>Vector3</code> pointing from p to this point.
	 */
	public Vector3 sub(final Point3 p) {
		if (p == null) {
			throw new IllegalArgumentException("The parameter 'p' must not be null.");
		}
		return new Vector3(x - p.x, 
						   y - p.y, 
						   z - p.z);
	}
	
	/**
	 * Calculates the <code>Point3</code> resulting from the subtraction of the specified <code>Vector3</code> from 
	 * this <code>Point3</code>.
	 * 
	 * @param v The <code>Vector3</code> subtracted from this point. Must not be <code>null</code>.
	 * @return	The resulting <code>Point3</code>.
	 */
	public Point3 sub(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Point3(x - v.x, 
						  y - v.y, 
						  z - v.z);
	}
	
	/**
	 * Calculates the <code>Point3</code> resulting from the addition of the specified <code>Vector3</code> to this 
	 * <code>Point3</code>.
	 * 
	 * @param v The <code>Vector3</code> added to this point. Must not be <code>null</code>.
	 * @return	The resulting <code>Point3</code>
	 */
	public Point3 add(final Vector3 v) {
		if (v == null) {
			throw new IllegalArgumentException("The parameter 'v' must not be null.");
		}
		return new Point3(x + v.x, 
						  y + v.y, 
						  z + v.z);
	}
	
	/**
	 * Converts this <code>Point</code> to <code>Vector3</code>.
	 * 
	 * @return The <code>Vector3</code>.
	 */
	public Vector3 asVector() {
		return new Vector3(x, y, z);
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
		final Point3 other = (Point3) obj;
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
}
