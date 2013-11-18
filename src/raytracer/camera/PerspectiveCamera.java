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
	 * 
	 */
	private double angle;
	
	/**
	 * @param e
	 * @param g
	 * @param t
	 * @param angle
	 */
	public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle) {
		super(e, g, t);
		this.angle = angle;
	}
	
	@Override
	public Ray rayFor(final int w, final int h, final int x, final int y) {
		// TODO Auto-generated method stub
		return null;
	}
}
