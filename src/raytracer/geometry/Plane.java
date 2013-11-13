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
		this.a = a;
		this.n = n;
	}

	@Override
	public Hit hit(final Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
