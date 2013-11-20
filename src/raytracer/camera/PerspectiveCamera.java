package raytracer.camera;

import raytracer.Ray;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * @author 
 *
 */
public class PerspectiveCamera extends Camera{
	/**
	 * The camera angle in radians.
	 */
	private double angle;
	
	/**
	 * @param e Vector of the eye position.
	 * @param g Vector of the gaze-direction.
	 * @param t Vector of the up-direction.
	 * @param angle Perspective camera angle in degrees.
	 */
	public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle) {
		super(e, g, t);
		this.angle = Math.toRadians(angle);
	}
	
	/**
	 * 
	 */
	@Override
	public Ray rayFor(final int width, final int height, final int x, final int y) {
		final double f1 = height / (-2.0 * Math.tan(angle));
		final double f2 = x - (width - 1.0) / 2.0;
		final double f3 = y - (height - 1.0) / 2.0;
		final Vector3 r = w.mul(f1).add(u.mul(f2).add(v.mul(f3)));
		return new Ray(e, r.normalized());
	}
}
