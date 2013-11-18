package raytracer.geometry;

import raytracer.Color;
import raytracer.Ray;
import raytracer.math.Point3;

/**
 * @author 
 *
 */
public class Sphere extends Geometry {
	/**
	 * 
	 */
	public final Point3 c;
	/**
	 * 
	 */
	public final double r;
	
	/**
	 * @param c
	 * @param r
	 * @param color
	 */
	public Sphere(final Point3 c, final double r, final Color color) {
		super(color);
		this.c = c;
		this.r = r;
	}

	@Override
	public Hit hit(final Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
