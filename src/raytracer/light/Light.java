package raytracer.light;

import raytracer.Color;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * 
 * 
 * @author 
 *
 */
public abstract class Light {
	/**
	 * 
	 */
	public final Color color;
	
	/**
	 * 
	 * 
	 * @param color
	 */
	public Light(final Color color) {
		this.color = color;
	}
	
	/**
	 * 
	 * 
	 * @param point
	 * @return
	 */
	public abstract boolean illuminates(final Point3 point);
	
	/**
	 * 
	 * 
	 * @param point
	 * @return
	 */
	public abstract Vector3 directionFrom(Point3 point);
}
