package raytracer;

import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * @author 
 *
 */
public class Ray {
	/**
	 * 
	 */
	public final Point3 o;
	/**
	 * 
	 */
	public final Vector3 d;
	
	/**
	 * @param o
	 * @param d
	 */
	public Ray(final Point3 o, final Vector3 d) {
		this.o = o;
		this.d = d;
	}
	
	/**
	 * @param t
	 * @return
	 */
	public Point3 at(final Double t) {
		return null;
	}
	
	/**
	 * @param p
	 * @return
	 */
	public double tOf(final Point3 p) {
		return 0;
	}
}
