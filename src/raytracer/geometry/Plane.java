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
	 * A point on this plane.
	 */
	public final Point3 a;
	/**
	 * The normal of this plane.
	 */
	public final Normal3 n;
	
	/**
	 * Constructs a new <code>Plane</code> with the specified parameters.
	 * 
	 * @param a		A point on the plane. Must not be <code>null</code>.
	 * @param n		The normal of the plane. Must not be <code>null</code>.
	 * @param color	The color of the plane. Must not be <code>null</code>.
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((n == null) ? 0 : n.hashCode());
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
		final Plane other = (Plane) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (n == null) {
			if (other.n != null)
				return false;
		} else if (!n.equals(other.n))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ",\n\t[a = " + a + ",\n" 
								+ "\tn = " + n + "]";
	}
	
	//---- Test
	public static void main(String[] args) {
		System.out.println(new Plane(new Point3(0, 0, 0), new Normal3(1, 1, 1), new Color(0.5, 0.5, 0.5)));
	}
}
