package raytracer.light;

import raytracer.Color;
import raytracer.Ray;
import raytracer.World;
import raytracer.geometry.Hit;
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
	 * @param color			The color of the light. Must not be <code>null</code>.
	 * @param position		The position of the light. Must not be <code>null</code>.
	 * @param castsShadow	If <code>true</code> the <code>PointLight</code> casts a shadow.
	 */
	public PointLight(final Color color, final Point3 position, final boolean castsShadow) {
		super(color, castsShadow);
		if (position == null) {
			throw new IllegalArgumentException("The parameter 'position' must not be null.");
		}
		this.position = position;
	}
	
	public PointLight(final Color color, final Point3 position) {
		this(color, position, true);
	}
	
	@Override
	public boolean illuminates(final Point3 point, final World world) {
		if (!castsShadow) {
			return true;
		}
		final Ray ray = new Ray(point, directionFrom(point));
		final Hit hit = world.hit(ray);
		return hit == null || hit.t >= ray.tOf(position);
	}
	
	@Override
	public Vector3 directionFrom(final Point3 point) {
		if (point == null) {
			throw new IllegalArgumentException("The parameter 'point' must not be null.");
		}
		return position.sub(point).normalized();
	}
}
