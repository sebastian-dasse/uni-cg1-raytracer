package raytracer.light;

import raytracer.Color;
import raytracer.World;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This abstract base class represents a light in a three-dimensional scene. 
 * <p>
 * It has a color and a method, which determines whether or not a point is illuminated by this light. It has another 
 * method, that returns the vector which points from a specific point to this light.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public abstract class Light {
	/**
	 * The color of this <code>Light</code>.
	 */
	public final Color color;
	/**
	 * Defines whether or not this <code>Light</code> casts a shadow.
	 */
	public final boolean castsShadow;
	
	/**
	 * Constructs a new <code>Light</code> with the specified color.
	 * 
	 * @param color			The color of the <code>Light</code>. Must not be <code>null</code>.
	 * @param castsShadow	If <code>true</code> the <code>Light</code> casts a shadow.
	 */
	public Light(final Color color, final boolean castsShadow) {
		if (color == null) {
			throw new IllegalArgumentException("The parameter 'color' must not be null.");
		}
		this.color = color;
		this.castsShadow = castsShadow;
	}
	
	/**
	 * Returns <code>true</code> if the specified point is illuminated by this light.
	 * <p>
	 * The default implementation always returns <code>true</code>, thus subclasses must override to change this behavior.
	 * 
	 * @param point	The point to be checked.
	 * @return		<code>true</code> if the specified point is illuminated, otherwise <code>false</code>.
	 */
	public boolean illuminates(final Point3 point, World w) {
		return true;
	}
	
	/**
	 * Returns the vector which points from the specified point to this light.
	 * 
	 * @param point	The point to be checked. Must not be <code>null</code>.
	 * @return		A <code>Vector3</code>. 
	 */
	public abstract Vector3 directionFrom(final Point3 point);
}
