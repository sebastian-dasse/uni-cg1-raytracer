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
		if (c == null || r == 0) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.c = c;
		this.r = r;
	}

	@Override
	public Hit hit(final Ray ray) {
		double a = ray.d.dot(ray.d);
		if ( 2*a == 0){
			return null;
		}
		double b = ray.d.dot((ray.o.sub(c)).mul(2.0));
		double c1 = ray.o.sub(c).dot(ray.o.sub(c)) - r*r;
		double d = b*b - 4*a*c1;	
		if (d < 0) {
			return null;
		}
		if (d == 0) {
			double t = -b/2*a;
			return new Hit(t, ray, this);
		}
		if (d > 0) {
			
		}
		
		return null;
	}
}
