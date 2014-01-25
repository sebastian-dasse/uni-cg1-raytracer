package raytracer.geometry;

import static raytracer.math.MathUtil.isValid;
import raytracer.Constants;
import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Normal3;
import raytracer.math.Point3;

/**
 * This immutable class represents a sphere in three-dimensional space. It is defined trough its center point and radius.
 *  
 * @author Maxim Novichkov
 * @author Sebastian Dass&eacute;
 */
public class Sphere extends Geometry {
	/**
	 * The center point of this sphere.
	 */
	private static final Point3 center = new Point3(0, 0, 0);
	/**
      * The radius of this sphere.
      */
	private static final double r = 1;
	/**
	 * Constructs a new <code>Sphere</code> with the specified parameters.
	 * 
	 * @param material	The material of the sphere. Must not be <code>null</code>.
	 */
	public Sphere(final Material material) {
		super(material);
	}

	@Override
	public Hit hit(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("The parameter 'ray' must not be null.");
		}
		
		/*
		 * Formulas:
		 * 		a = <d, d> = d.x² + d.y² + d.z²  <-- will never be negative!
		 * 		b = <d, 2(o - center) >
		 * 		c = <o - c, o - c> - r²
		 * 		t = -b +- sqrt(b² - 4ac) / 2a
		 */
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
			final double t = -b / (2.0 * a);
			final Point3 p = ray.at(t);
			
			// Formula: normal = p - center
			final Normal3 normal = p.sub(center).normalized().asNormal(); // normalized normal
			return (b > 0) ? null : new Hit(t, ray, this, normal);
		} // discrimant > 0  =>  2 results => 2 hits
		final double numerator;
		final double n1 = -b + Math.sqrt(discriminant);
		final double n2 = -b - Math.sqrt(discriminant);
		if (n1 < Constants.EPSILON && n2 < Constants.EPSILON){
			return null; // no hit
		} // t1 >= 0 || t2 >= 0
		if (n1 < Constants.EPSILON) {
			numerator = n2;
		} else if (n2 < Constants.EPSILON) { // t1 >= 0
			numerator = n1;
		} else { // t1 >= 0 && t2 >= 0
			numerator = Math.min(n1, n2);
		}
		final double t = numerator / (2 * a);
		final Point3 p = ray.at(t);
		
		// Formula: normal = p - center
		final Normal3 normal = p.sub(center).normalized().asNormal(); // normalized normal
		return new Hit(t, ray, this, normal);
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
		return super.toString() + ",\n\tcenter = " + center + ",\n" + "\tr = "
				+ r + "]";
	}

}
