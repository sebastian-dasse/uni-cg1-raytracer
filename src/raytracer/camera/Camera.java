package raytracer.camera;

import raytracer.Ray;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * TODO DOK IT!
 * This immutable base class ...
 * 
 * @author 
 *
 */
public abstract class Camera {
	/**
	 * The eye position of this <code>Camera<code>.
	 */
	public final Point3 e;
	/**
	 * The gaze direction of this <code>Camera<code>.
	 */
	public final Vector3 g;
	/**
//	 * The up vector of this <code>Camera<code>.
	 */
	public final Vector3 t;
	/**
	 * The u vector of the camera coordinate system.
	 */
	public final Vector3 u;
	/**
	 * The v vector of the camera coordinate system.
	 */
	public final Vector3 v;
	/**
	 * The w vector of the camera coordinate system. 
	 */
	public final Vector3 w;
	
	/**
	 * Constructs a new <code>Camera</code> with the specified parameters.
	 * 
	 * @param e The eye position. Must not be <code>null</code>.
	 * @param g	The gaze direction. Must not be <code>null</code>.
	 * @param t	The up vector. Must not be <code>null</code>.
	 */
	public Camera(final Point3 e, final Vector3 g, final Vector3 t) {
		if (e == null || g == null || t == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.e = e;
		this.g = g;
		this.t = t;
		w = g.normalized().mul(-1.0);
		u = t.x(w).normalized();
		v = w.x(u);
	}
	
	/**
	 * Generates a <code>Ray</code> for the specified pixel on a screen with the specified resolution.
	 * 
	 * @param width		The screen height in pixels.
	 * @param height	The screen width in pixels.
	 * @param x			The x coordinate of the pixel.
	 * @param y			The y coordinate of the pixel.
	 * @return
	 */
	public abstract Ray rayFor(final int width, final int height, final int x, final int y);
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[\te = " + e + ",\n"
										  + "\tg = " + g + ",\n"
										  + "\tt = " + t + ",\n"
										  + "\tu = " + u + ",\n"
										  + "\tv = " + v +",\n"
										  + "\tw = " + w;
	}
}
