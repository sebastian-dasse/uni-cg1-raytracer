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
	
	/**
	 * @param o	The origin of the ray. Must not be <code>null</code>.
	 * @param d	The direction of the ray. Must not be <code>null</code>.
	 */
	public Ray(final Point3 o, final Vector3 d) {
		if (o == null || d == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
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
		return o.add(d.mul(t));
	}
	
	/**
	 * @param p
	 * @return
	 */
	public double tOf(final Point3 p) {
		return 0;
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
