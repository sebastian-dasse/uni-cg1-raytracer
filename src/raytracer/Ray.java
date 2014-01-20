package raytracer;

import static raytracer.math.MathUtil.isValid;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class represents a ray. It has has an origin o and a direction d.
 * <p> 
 * It provides a method for calculating a specific point on a ray from a given parameter. It also has a method for 
 * calculating a parameter with which a given point can be generated.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class Ray {
	/**
	 * The origin of this <code>Ray</code>.
	 */
	public final Point3 o;
	/**
	 * The direction of this <code>Ray</code>.
	 */
	public final Vector3 d;
	
	/**
	 * @param o	The origin of the ray. Must not be <code>null</code>.
	 * @param d	The direction of the ray. Thus the null vector (0, 0, 0) is not allowed. Must not be <code>null</code>.
	 */
	public Ray(final Point3 o, final Vector3 d) {
		if (o == null || d == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		if (d.magnitude == 0) {
			throw new IllegalArgumentException("The null vector (0, 0, 0) is not a meaningful direction d.");
		}
		this.o = o;
		this.d = d;
	}
	
	/**
	 * Calculates a point on this <code>Ray</code> as specified by the parameter t.
	 * 
	 * @param t	The parameter. Must be a positive double value other than Infinity or NaN.
	 * @return	The <code>Point3</code> for the given t.
	 */
	public Point3 at(final double t) {
		// Temporarely commented out for AAB - Debug.
//		if (t < 0 || !isValid(t)) {
//			throw new IllegalArgumentException("The paramameter 't' must be a positive double value other than Infinity or NaN.");
//		}
//		 p = o + td
		return o.add(d.mul(t));
	}
	
	/**
	 * Calculates the parameter t of a given <code>Point3</code>.
	 * 
	 * @param p	The <code>Point3</code>.
	 * @return	The parameter t for the given point.
	 */
	public double tOf(final Point3 p) {
		if (p == null) {
			throw new IllegalArgumentException("The parameter 'p' must not be null.");
		}
		// p = o + td  <=>  p - o = td  =>  t = |p - o| / |d|
		return p.sub(o).magnitude / d.magnitude;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((d == null) ? 0 : d.hashCode());
		result = prime * result + ((o == null) ? 0 : o.hashCode());
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
		final Ray other = (Ray) obj;
		if (d == null) {
			if (other.d != null)
				return false;
		} else if (!d.equals(other.d))
			return false;
		if (o == null) {
			if (other.o != null)
				return false;
		} else if (!o.equals(other.o))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[\to = " + o + ",\n" 
										  + "\td = " + d + "]";
	}
}
