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
	 * This vector represents eye position.
	 */
	public final Point3 e;
	/**
	 * This vector represents gaze-direction.
	 */
	public final Vector3 g;
	/**
	 * This vector represents up-direction.
	 */
	public final Vector3 t;
	/**
	 * This vector represents a new coordinate system and was calculated from vector t and w.
	 */
	public final Vector3 u;
	/**
	 * This vector represents a new coordinate system and was calculated from vector u and w.
	 */
	public final Vector3 v;
	/**
	 * This vector represents a new coordinate system and was calculated by normalizing of vector g and then multiplied by -1. 
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
	public abstract Ray rayFor(final int width, final int height, final int x, final int y);
}
