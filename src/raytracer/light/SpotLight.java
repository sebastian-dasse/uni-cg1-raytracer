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
		
		// Formula: cos(a, b) = a.dot(b), mit |a| = |b| = 1
<<<<<<< HEAD
//		System.err.println(direction.dot(directionFrom(point).mul(-1).asNormal()));

		return Math.acos(direction.dot(directionFrom(point).mul(-1).asNormal())) <= halfAngle;
=======
		return Math.acos(direction.normalized().dot(directionFrom(point).mul(-1))) <= halfAngle;
>>>>>>> e3cdce5e2d1bde000101c899ceb4b9515b9a541b
	}

	@Override
	public Vector3 directionFrom(final Point3 point) {
//		System.err.println(position.sub(point).normalized());
		return position.sub(point).normalized(); // normalized
	}
}
