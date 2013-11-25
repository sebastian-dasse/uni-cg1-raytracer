package raytracer.camera;

import raytracer.Ray;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable base class provide a basis for implementing various cameras.
 * 
 * @author Sebastian Dass&eacute;
 * @author Maxim Novichkov;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((e == null) ? 0 : e.hashCode());
		result = prime * result + ((g == null) ? 0 : g.hashCode());
		result = prime * result + ((t == null) ? 0 : t.hashCode());
		result = prime * result + ((u == null) ? 0 : u.hashCode());
		result = prime * result + ((v == null) ? 0 : v.hashCode());
		result = prime * result + ((w == null) ? 0 : w.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Camera other = (Camera) obj;
		if (e == null) {
			if (other.e != null)
				return false;
		} else if (!e.equals(other.e))
			return false;
		if (g == null) {
			if (other.g != null)
				return false;
		} else if (!g.equals(other.g))
			return false;
		if (t == null) {
			if (other.t != null)
				return false;
		} else if (!t.equals(other.t))
			return false;
		if (u == null) {
			if (other.u != null)
				return false;
		} else if (!u.equals(other.u))
			return false;
		if (v == null) {
			if (other.v != null)
				return false;
		} else if (!v.equals(other.v))
			return false;
		if (w == null) {
			if (other.w != null)
				return false;
		} else if (!w.equals(other.w))
			return false;
		return true;
	}

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
