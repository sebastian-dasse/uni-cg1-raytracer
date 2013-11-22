package raytracer.geometry;

import raytracer.Color;
import raytracer.Ray;

/**
 * @author 
 *
 */
public abstract class Geometry {
	/**
	 * 
	 */
	public final Color color;
	
	/**
	 * @param color
	 */
	public Geometry(final Color color) {
		if (color == null) {
			throw new IllegalArgumentException("The parameter 'color' must not be null.");
		}
		this.color = color;
	}
	
	/**
	 * @param ray
	 * @return
	 */
	public abstract Hit hit(final Ray ray);
}
