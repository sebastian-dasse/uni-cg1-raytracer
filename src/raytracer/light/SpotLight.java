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
public class SpotLight extends Light {
	/**
	 * 
	 */
	public final Point3 position;
	/**
	 * 
	 */
	public final Vector3 direction;
	/**
	 * 
	 */
	public final double halfAngle;
	
	/**
	 * 
	 * 
	 * @param color
	 * @param position
	 * @param direction
	 * @param halfAngle
	 */
	public SpotLight(final Color color, final Point3 position, final Vector3 direction, final double halfAngle) {
		super(color);
		this.position = position;
		this.direction = direction.normalized();
		this.halfAngle = halfAngle;
	}

	@Override
	public boolean illuminates(final Point3 point) {
		
		// Formula: cos(l, d) = <l, d>, with |l| = |d| = 1, l:= light direction, r: = vector from light position to point
		return Math.acos(direction.normalized().dot(directionFrom(point).mul(-1))) <= halfAngle;
	}

	@Override
	public Vector3 directionFrom(final Point3 point) {
//		System.err.println(position.sub(point).normalized());
		return position.sub(point).normalized(); // normalized
	}
}
