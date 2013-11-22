package raytracer.geometry;

import raytracer.Color;
import raytracer.Ray;
import raytracer.math.Normal3;
import raytracer.math.Point3;

/**
 * @author 
 *
 */
public class Plane extends Geometry {
	/**
	 * 
	 */
	public final Point3 a;
	/**
	 * 
	 */
	public final Normal3 n;
	
	/**
	 * @param a
	 * @param n
	 * @param color
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
