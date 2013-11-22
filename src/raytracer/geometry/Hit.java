package raytracer.geometry;

import static raytracer.math.MathUtil.isValid;
import raytracer.Color;
import raytracer.Ray;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class represents a hit of a <code>Ray</code> with a <code>Geometry</code>. Therefore it stores the 
 * value t, which parameterizes the hit point, the <code>Ray</code> itself and the <code>Geometry</code> that was hit.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class Hit {
	/**
	 * The parameter for the hit point.
	 */
	public final double t;
	/**
	 * The <code>Ray</code> that hit the <code>Geometry</code>.
	 */
	public final Ray ray;
	/**
	 * The <code>Geometry</code> that was hit.
	 */
	public final Geometry geo;
	
	/**
	 * Constructs a new <code>Hit</code> object with the specified parameters.
	 * 
	 * @param t		The parameter for the hit point. Must be a double value other than +-Infinity or NaN.
	 * @param ray	The <code>Ray</code> that hit the <code>Geometry</code>. Must not be <code>null</code>.
	 * @param geo	The <code>Geometry</code> that was hit. Must not be <code>null</code>.
	 */
	public Hit(final double t, final Ray ray, final Geometry geo) {
		if (!isValid(t)) {
			throw new IllegalArgumentException("Only a double value other than +-Infinity or NaN is allowed.");
		}
		if (ray == null || geo == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.t = t;
		this.ray = ray;
		this.geo = geo;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[\tt = " + t + ",\n" 
										  + "\tray = " + ray + ",\n"
										  + "\tgeo = " + geo + "]";
	}
	
	
	//---- Test
	public static void main(String[] args) {
		System.out.println(new Hit(1.23, new Ray(new Point3(0, 0, 0), new Vector3(1, 1, 1)), new Plane(new Point3(2, 2, 2), new Normal3(3, 2, 1), new Color(0.5, 0.5, 0.5))));
	}
}
