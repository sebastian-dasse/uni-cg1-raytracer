package raytracer.geometry;

import static raytracer.math.MathUtil.isValid;
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
	public final Point3 center;
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
		if (c == null) {
			throw new IllegalArgumentException("The parameter 'c' must not be null.");
		}
		if (r < 0 || !isValid(r)) {
			throw new IllegalArgumentException("The paramameter 'r' must be a positive double value other than Infinity or NaN.");
		}
		center = c;
		this.r = r;
	}

	@Override
	public Hit hit(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("The parameter 'ray' must not be null.");
		}
		
		// a = d.dot(d)  = d.x * d.x + d.y * d.y + d.z * d.z  <--  will never be negative!
		// b = d.dot(o.sub(center).mul(2))
		// c = (o.sub(c)).dot(o.sub(c)) - r*r
		// t = -b +- sqrt(b*b -4ac) / 2a
		final double a = ray.d.dot(ray.d);
		if (a == 0) {
			return null;
		}
		final double b = ray.d.dot((ray.o.sub(center)).mul(2.0));
		final double c = ray.o.sub(center).dot(ray.o.sub(center)) - r*r;
		final double discriminant = b * b - 4 * a * c;	
		if (discriminant < 0) {
			return null; // no hit
		}
		if (discriminant == 0) { // one hit
			return (b > 0) ? null : new Hit((-b / (2 * a)), ray, this);
		} // discrimant > 0  =>  2 results => 2 hits
		final double numerator;
		final double n1 = -b + Math.sqrt(discriminant);
		final double n2 = -b - Math.sqrt(discriminant);
		if (n1 < 0 && n2 < 0){
			return null; // no hit
		} // t1 >= 0 || t2 >= 0
		if (n1 < 0) {
			numerator = n2;
		} else if (n2 < 0) { // t1 >= 0
			numerator = n1;
		} else { // t1 >= 0 && t2 >= 0
			numerator = Math.min(n1, n2);
		}
		return new Hit((numerator / (2 * a)), ray, this);
	}
}
