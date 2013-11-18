package raytracer;

import static raytracer.math.MathUtil.isValid;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * TODO DOC IT!
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
	
	// TODO FRAGE: Nullvektor als direction verbieten? falls nicht müsste bei tOf() Div durch 0 auf anderem Weg vermieden werden
	/**
	 * @param o	The origin of the ray. Must not be <code>null</code>.
	 * @param d	The direction of the ray. Must not be <code>null</code>.
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
	
	// TODO FRAGE: darf t < 0 sein?
	/**
	 * Calculates a point on this <code>Ray</code> as specified by the parameter t.
	 * 
	 * @param t	The parameter.
	 * @return	The <code>Point3</code> for the given t.
	 */
	public Point3 at(final double t) {
		if (!isValid(t)) {
			throw new IllegalArgumentException("Only a double value other than +-Infinity or NaN is allowed.");
		}
		// p = o + td
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
		return getClass().getSimpleName() + "[\to = " + o + ", \n\td = " + d + "]";
	}
}
