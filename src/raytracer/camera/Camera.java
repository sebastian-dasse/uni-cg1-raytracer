package raytracer.camera;

import raytracer.Ray;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * @author 
 *
 */
public abstract class Camera {
	/**
	 * 
	 */
	public final Point3 e;
	/**
	 * This vector represents eye position
	 */
	public final Vector3 g;
	/**
	 * This vector represents gaze-direction.
	 */
	public final Vector3 t;
	/**
	 * This vector represents up-direction.
	 */
	public final Vector3 u;
	/**
	 * 
	 */
	public final Vector3 v;
	/**
	 * 
	 */
	public final Vector3 w;
	
	/**
	 * @param e
	 * @param g
	 * @param t
	 */
	public Camera(final Point3 e, final Vector3 g, final Vector3 t) {
		this.e = e;
		this.g = g;
		this.t = t;
		w = g.normalized().mul(-1.0);
		u = t.x(w).normalized();
		v = w.x(u);
	}
	
	/**
	 * @param w
	 * @param h
	 * @param x
	 * @param y
	 * @return
	 */
	public abstract Ray rayFor(final int w, final int h, final int x, final int y);
}
