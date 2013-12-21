package raytracer.light;

import raytracer.Color;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class represents a point light in a three-dimensional scene. A point light has a position and spreads 
 * its rays equally in all directions.
 * 
 * @author Simon Lischka
 */
public class PointLight extends Light {
	/**
	 * The position of this <code>PointLight</code>.
	 */
	public final Point3 position;
	/**
	 * Constructs a new <code>PointLight</code> object with the specified parameters.
	 * 
	 * @param color		The color of the light. Must not be <code>null</code>.
	 * @param position	The position of the light. Must not be <code>null</code>.
	 */
	public PointLight(final Color color, final Point3 position, final boolean castShadow) {
		super(color, castShadow);
		if (position == null) {
			throw new IllegalArgumentException("The parameter 'position' must not be null.");
		}
		this.position = position;
	}
	/**
	 * Checks if the given point is illuminated by the light
	 * @param point A point to be processed by the light source
	 */
	@Override
	public boolean illuminates(final Point3 point) {
		return true;
		
	}
	/**
	 * Returns the vector which points from the specified point to this light.
	 * 
	 * @param point	The point to be checked. Must not be <code>null</code>.
	 * @return		A <code>Vector3</code>. 
	 */
	@Override
	public Vector3 directionFrom(final Point3 point) {
		if (point == null) {
			throw new IllegalArgumentException("The parameter 'point' must not be null.");
		}
		return position.sub(point).normalized();
	}
}
