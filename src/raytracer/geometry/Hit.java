package raytracer.geometry;

import raytracer.Ray;

/**
 * @author 
 *
 */
public class Hit {
	/**
	 * 
	 */
	public final double t;
	/**
	 * 
	 */
	public final Ray ray;
	/**
	 * 
	 */
	public final Geometry geo;
	
	/**
	 * @param t
	 * @param ray
	 * @param geo
	 */
	public Hit(final double t, final Ray ray, final Geometry geo) {
		this.t = t;
		this.ray = ray;
		this.geo = geo;
	}
}
