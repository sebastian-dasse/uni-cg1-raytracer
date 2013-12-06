package raytracer.light;

import raytracer.Color;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This class represents a point light in a three-dimensional scene.
 * <p>
 * It has a color and position. It includes a method to determine
 * whether a point is illuminated by this light. 
 * Another method returns the vector between the light position and the 
 * point to be illuminated.
 * <p>
 * @author Simon Lischka
 */
public class PointLight extends Light {
	/**
	 * A point to be illuminated
	 */
	public final Point3 position;

	/**
	 * Constructs a new <code>PointLight</code> object with the specified parameters.
	 * @param color
	 * @param position
	 */
	public PointLight(final Color color, final Point3 position) {
		super(color);
		this.position = position;
	}
	
	/**
	 * Checks if the given point is illuminated by the light
	 * @param point A point to be processed by the light source
	 */
	@Override
	public boolean illuminates(final Point3 point) {
		// TODO Auto-generated method stub
		return false;
	}
	/**
	 * Returns the vector from the point to the light source
	 * @param point The point to be processed by the light source
	 */
	@Override
	public Vector3 directionFrom(final Point3 point) {
		return position.sub(point);
	}
}
