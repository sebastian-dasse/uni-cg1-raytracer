package raytracer.geometry;

import raytracer.Color;
import raytracer.Ray;
import raytracer.math.Point3;

/**
 * @author 
 *
 */
public class Triangle extends Geometry {
	/**
	 * 
	 */
	public final Point3 a;
	/**
	 * 
	 */
	public final Point3 b;
	/**
	 * 
	 */
	public final Point3 c;
	
	/**
	 * @param a
	 * @param b
	 * @param c
	 * @param color
	 */
	public Triangle(final Point3 a, final Point3 b, final Point3 c, final Color color) {
		super(color);
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public Hit hit(final Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
