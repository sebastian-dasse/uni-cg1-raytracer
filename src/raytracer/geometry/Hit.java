package raytracer.geometry;

import static raytracer.math.MathUtil.isValid;
import raytracer.Color;
import raytracer.Ray;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class represents a hit of a <code>Ray</code> with a <code>Geometry</code>. Therefore it stores the 
 * value t, which parameterizes the hit point, the <code>Ray</code> itself and the <code>Geometry</code> that was hit.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class Hit {
	/**
	 * The parameter for this hit point.
	 */
	public final double t;
	/**
	 * The <code>Ray</code> that hit this <code>Geometry</code>.
	 */
	public final Ray ray;
	/**
	 * The <code>Geometry</code> that was hit.
	 */
	public final Geometry geo;
	
	/**
	 * Constructs a new <code>Hit</code> object with the specified parameters.
	 * 
	 * @param t		The parameter for the hit point. Must be a double value other than +-Infinity or NaN.
	 * @param ray	The <code>Ray</code> that hit the <code>Geometry</code>. Must not be <code>null</code>.
	 * @param geo	The <code>Geometry</code> that was hit. Must not be <code>null</code>.
	 */
	public Hit(final double t, final Ray ray, final Geometry geo) {
		if (!isValid(t)) {
			throw new IllegalArgumentException("Only a double value other than +-Infinity or NaN is allowed.");
		}
		if (ray == null || geo == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.t = t;
		this.ray = ray;
		this.geo = geo;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geo == null) ? 0 : geo.hashCode());
		result = prime * result + ((ray == null) ? 0 : ray.hashCode());
		long temp;
		temp = Double.doubleToLongBits(t);
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
		final Hit other = (Hit) obj;
		if (geo == null) {
			if (other.geo != null)
				return false;
		} else if (!geo.equals(other.geo))
			return false;
		if (ray == null) {
			if (other.ray != null)
				return false;
		} else if (!ray.equals(other.ray))
			return false;
		if (Double.doubleToLongBits(t) != Double.doubleToLongBits(other.t))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[\tt = " + t + ",\n" 
										  + "\tray = " + ray + ",\n"
										  + "\tgeo = " + geo + "]";
	}
	
	//---- Test
	public static void main(String[] args) {
		System.out.println(new Hit(1.23, new Ray(new Point3(0, 0, 0), new Vector3(1, 1, 1)), new Plane(new Point3(2, 2, 2), new Normal3(3, 2, 1), new Color(0.5, 0.5, 0.5))));
	}
}
