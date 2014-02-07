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
//	private Point3 origin = new Point3(0, 0, 0); // TODO -- for testing -- when done --> should be static final
	/**
	 * The standard normal of all planes (0, 1, 0).
	 */
	private static final Normal3 n = new Normal3(0, 1, 0);
//	private Normal3 n = new Normal3(0, 1, 0); // TODO -- for testing -- when done --> should be static final
	/**
	 * Constructs a new <code>Plane</code> with the specified material.
	 * 
	 * @param material	The material of the plane. Must not be <code>null</code>.
	 */
	public Plane(final Material material) {
		super(material);
	}
	
	// TODO -- for testing -- when done --> remove
//	public Plane(final Point3 point, final Normal3 normal , final Material material) {
//		super(material);
//		this.origin = point;
//		this.n= normal;
//	}

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
		final Point3 coord = ray.at(t); 
		return (t < Constants.EPSILON) ? null : new Hit(t, ray, this, normal, new TexCoord2(coord.x, coord.z));
	}
}
