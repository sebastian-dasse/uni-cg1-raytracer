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
	 * @param e Vector of the eye position.
	 * @param g Vector of the gaze-direction.
	 * @param t Vector of the up-direction.
	 * @param angle Perspective camera angle.
	 */
	public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle) {
		super(e, g, t);
		this.angle = angle;
	}
	
	/**
	 * 
	 */
	@Override
	public Ray rayFor(final int w, final int h, final int x, final int y) {
		Vector3 temp1 = (super.w.mul(-1.0).mul((h/2.0)/Math.tan(angle)));
		Vector3 temp2 = super.u.mul(x-(w-1.0/2));
		Vector3 temp3 = super.v.mul(y-(h-1.0/2));
		
		Vector3 helpvector = (temp1.add(temp2)).add(temp3);
		Vector3 d = helpvector.normalized();
		
		//o is the same as e 
		
		return new Ray(super.e, d);
	}
}
