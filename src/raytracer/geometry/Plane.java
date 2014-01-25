package raytracer.geometry;

import raytracer.Constants;
import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Normal3;
import raytracer.math.Point3;

/**
 * This immutable class represents an infinitely large plane in three-dimensional space. It is defined through a 
 * <code>Point3</code> and a <code>Normal3</code>.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class Plane extends Geometry {
	/**
	 * A point on this plane.
	 */
	private static final Point3 a = new Point3(0, 0, 0);
	/**
	 * The normal of this plane.
	 */
	private static final Normal3 n = new Normal3(0, 1, 0);
	/**
	 * Constructs a new <code>Plane</code> with the specified parameters.
	 * 
	 * @param material	The material of the plane. Must not be <code>null</code>.
	 */
	public Plane(final Material material) {
		super(material);
	}

	@Override
	public Hit hit(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("The parameter 'ray' must not be null.");
		}
		// Formula: t = <a - o, n> / <d, n>
		final double denominator = ray.d.dot(n);
		if (denominator == 0) { // not hit
			return null;
		}
		final double t = a.sub(ray.o).dot(n) / denominator;
		final Normal3 normal = n.asVector().normalized().asNormal(); // normalized normal
		return (t < Constants.EPSILON) ? null : new Hit(t, ray, this, normal);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((n == null) ? 0 : n.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Plane other = (Plane) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ",\n\t[a = " + a + ",\n" + "\tn = " + n + "]";
	}


}
