package raytracer.light;

import raytracer.Color;
import raytracer.Ray;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class represents a directional light (sunlight) in a three-dimensional scene. A directional light, as 
 * the name suggests, has a direction - but no position.
 * 
 * @author Sebastian Dass&eacute; 
 *
 */
public class DirectionalLight extends Light {
	/**
	 * The direction of this <code>DirectionalLight</code>.
	 */
	public final Vector3 direction;
	
	/**
	 * Constructs a new <code>DirectionalLight</code> object with the specified parameters.
	 * 
	 * @param color			The color of the light. Must not be <code>null</code>.
	 * @param direction		The direction of the light. Must not be <code>null</code>.
	 * @param castsShadow	If <code>true</code> the <code>DirectionalLight</code> casts a shadow.
	 */
	public DirectionalLight(final Color color, final Vector3 direction, final boolean castsShadow) {
		super(color, castsShadow);
		if (direction == null) {
			throw new IllegalArgumentException("The parameter 'direction' must not be null.");
		}
		this.direction = direction; 
	}
	
	public DirectionalLight(final Color color, final Vector3 direction) {
		this(color, direction, true);
	}
	
	public boolean illuminates(final Point3 point, World world) {
		final Ray ray = new Ray(point, direction.mul(-1.0).normalized());
		final Hit hit = world.hit(ray);
		return hit == null;
	}
	
	@Override
	public Vector3 directionFrom(final Point3 point) {
		return direction.mul(-1).normalized();
	}
}
