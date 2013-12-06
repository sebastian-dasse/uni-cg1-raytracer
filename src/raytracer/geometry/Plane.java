package raytracer.geometry;

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
	public final Point3 a;
	/**
	 * The normal of this plane.
	 */
	public final Normal3 n;
	
	/**
	 * Constructs a new <code>Plane</code> with the specified parameters.
	 * 
	 * @param a			A point on the plane. Must not be <code>null</code>.
	 * @param n			The normal of the plane. Must not be <code>null</code>.
	 * @param material	The material of the plane. Must not be <code>null</code>.
	 */
	public Plane(final Point3 a, final Normal3 n, final Material material) {
		super(material);
		if (a == null || n == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.a = a;
		this.n = n;
	}

	@Override
	public Hit hit(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("The parameter 'ray' must not be null.");
		}
		
		// t = (a - o).dot(n) / d.dot(n)
		final double denominator = ray.d.dot(n);
		if (denominator == 0) { // not hit
			return null;
		}
		final double t = a.sub(ray.o).dot(n) / denominator;
		return (t < 0) ? null : new Hit(t, ray, this);
	}

	@Override
	public String toString() {
		return super.toString() + ",\n\t[a = " + a + ",\n" 
								+ "\tn = " + n + "]";
	}
}
