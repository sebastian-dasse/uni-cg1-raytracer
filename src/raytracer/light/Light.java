package raytracer.light;

import raytracer.Color;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This abstract base class represents a light in a three-dimensional scene. 
 * <p>
 * It has a color and a method, which determines whether or not a point is illuminated by this light. It has another 
 * method, that returns the vector which points from a specific point to this light.
 * 
 * @author 
 *
 */
public abstract class Light {
	/**
	 * The color of this <code>Light</code>.
	 */
	public final Color color;
	
	/**
	 * Constructs a new <code>Light</code> with the specified color.
	 * 
	 * @param color	The color of the <code>Light</code>. Must not be <code>null</code>.
	 */
	public Light(final Color color) {
		if (color == null) {
			throw new IllegalArgumentException("The parameter 'color' must not be null.");
		}
		this.color = color;
	}
	
	/**
	 * Returns <code>true</code> if the specified point is illuminated by this light.
	 * <p>
	 * The default implementation always returns <code>true</code>, thus subclasses must override to change this behavior.
	 * 
	 * @param point	The point to be checked.
	 * @return		<code>true</code> if the specified point is illuminated, otherwise <code>false</code>.
	 */
	public boolean illuminates(final Point3 point) {
		return true;
	}
	
	/**
	 * Returns the vector which points from the specified point to this light.
	 * 
	 * @param point	The point to be checked.
	 * @return		A <code>Vector3</code>. 
	 */
	public abstract Vector3 directionFrom(Point3 point);
}
