package raytracer.geometry;

import raytracer.Color;
import raytracer.Ray;
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
	 * A point on the plane.
	 */
	public final Point3 a;
	/**
	 * The normal of the plane.
	 */
	public final Normal3 n;
	
	/**
	 * Constructs a new <code>Plane</code> with the specified parameters.
	 * 
	 * @param a		A point on this plane. Must not be <code>null</code>.
	 * @param n		The normal of this plane. Must not be <code>null</code>.
	 * @param color	The color of this plane. Must not be <code>null</code>.
	 */
	public Plane(final Point3 a, final Normal3 n, final Color color) {
		super(color);
		if (a == null || n == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.a = a;
		this.n = n;
	}

	@Override
	public Hit hit(final Ray ray) {
		
		// t = (a - o).dot(n) / d.dot(n)
		double denominator = ray.d.dot(n);
		if (denominator == 0) {
			return null;
		}
		double t = a.sub(ray.o).dot(n);
		return new Hit(t, ray, this);
	}
}
