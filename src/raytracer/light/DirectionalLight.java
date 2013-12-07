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
public class DirectionalLight extends Light {
	/**
	 * 
	 */
	public final Vector3 direction;
	
	/**
	 * 
	 * @param color
	 * @param direction
	 */
	public DirectionalLight(final Color color, final Vector3 direction) {
		super(color);
		this.direction = direction; 
	}

	@Override
	public boolean illuminates(final Point3 point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector3 directionFrom(final Point3 point) {
		return direction.mul(-1).normalized();

	}
}
