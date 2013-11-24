package raytracer.geometry;

import raytracer.Color;
import raytracer.Ray;
import raytracer.math.Mat3x3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 *  This immutable class represents a flat triangle with three points in the corner.
 * @author 
 *
 */
public class Triangle extends Geometry {
	/**
	 * The first point af a triangle.
	 */
	public final Point3 a;
	/**
	 * The second point af a triangle.
	 */
	public final Point3 b;
	/**
	 * The fourth point af a triangle.
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
		if (a == null || b == null || c == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}	
			this.a = a;
			this.b = b;
			this.c = c;
	}

	@Override
	public Hit hit(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("The parameter 'ray' must not be null.");
		}
		
		// beta  = detA_1 / detA
		// gamma = detA_2 / detA
		// t     = detA_3 / detA
		// 0 <= beta + gamma <= 1
		final Mat3x3 matrix = new Mat3x3(a.x - b.x, a.x - c.x, ray.d.x,
								   		 a.y - b.y, a.y - c.y, ray.d.y,
								   		 a.z - b.z, a.z - c.z, ray.d.z);
		final Vector3 dvector = a.sub(ray.o);
		if (matrix.determinant == 0) {
			return null; // no hit
		}
		final double beta  = matrix.changeCol1(dvector).determinant / matrix.determinant;
		final double gamma = matrix.changeCol2(dvector).determinant / matrix.determinant;
		final double t     = matrix.changeCol3(dvector).determinant / matrix.determinant;
		
		// TODO FRAGE: kann t < 0 werden?
		if (gamma < 0 || beta < 0 || beta + gamma > 1 || t < 0) {
			return null; // no hit
		}
		return new Hit(t, ray, this);
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + ((c == null) ? 0 : c.hashCode());
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
		final Triangle other = (Triangle) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ",\n\ta = " + a + ",\n" 
								+ "\tb = " + b + ",\n" 
								+ "\tc = " + c + "]";
	}
}
