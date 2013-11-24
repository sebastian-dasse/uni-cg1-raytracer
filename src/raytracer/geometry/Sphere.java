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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((center == null) ? 0 : center.hashCode());
		long temp;
		temp = Double.doubleToLongBits(r);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		final Sphere other = (Sphere) obj;
		if (center == null) {
			if (other.center != null)
				return false;
		} else if (!center.equals(other.center))
			return false;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ",\n\tcenter = " + center + ",\n" 
								+ "\tr = " + r + "]";
	}
}
