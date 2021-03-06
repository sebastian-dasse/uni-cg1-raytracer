package raytracer.geometry;

import raytracer.Constants;
import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.texture.TexCoord2;

/**
 * This immutable class represents an infinitely large plane in three-dimensional space. It is defined through its 
 * normal (0, 1, 0) and runs through the origin (0, 0, 0).
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class Plane extends Geometry {
	/**
	 * The origin (0, 0, 0).
	 */
	private static final Point3 origin = new Point3(0, 0, 0);
	/**
	 * The standard normal of all planes (0, 1, 0).
	 */
	private static final Normal3 n = new Normal3(0, 1, 0);
	/**
	 * Constructs a new <code>Plane</code> with the specified material. This plane runs through the origin (0, 0, 0) 
	 * and has the standard normal (0, 1, 0).
	 * 
	 * @param material	The material of the plane. Must not be <code>null</code>.
	 */
	public Plane(final Material material) {
		super(material);
	}
	
	@Override
	public Hit hit(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("The parameter 'ray' must not be null.");
		}
		// Formula: t = <a - o, n> / <d, n>,  a = 0  =>  <a - o, n> = <-o, n> = <o, -n>
		final double denominator = ray.d.dot(n);
		if (denominator == 0) { // not hit
			return null;
		}
		final double t = origin.sub(ray.o).dot(n) / denominator;
		final Normal3 normal = n.asVector().normalized().asNormal(); // normalized normal
		final Point3 p = ray.at(t); 
		return (t < Constants.EPSILON) ? null : new Hit(t, ray, this, normal, new TexCoord2(p.x, -p.z));
	}
}
