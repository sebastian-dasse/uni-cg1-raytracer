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
	 * 
	 */
	public final Vector3 g;
	/**
	 * 
	 */
	public final Vector3 t;
	/**
	 * 
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
		
		// to be calculated from e, g and t:
		u = null;
		v = null;
		w = null;
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
