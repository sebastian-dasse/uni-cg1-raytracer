package raytracer.camera;

import raytracer.Ray;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class represents a perspective camera. All rays have the same origin but a different direction. The 
 * opening angle defines how much of the scene is visible.
 * 
 * @author Maxim Novichkov
 * @author Sebastian Dass&eacute;
 * 
 */
public class PerspectiveCamera extends Camera{
	/**
	 * The half opening angle of this camera in radians.
	 */
	private final double angle;
	
	/**
	 * Constructs a new <code>PerspectiveCamera</code> with the specified parameters.
	 * 
	 * @param e		The eye position of the camera. Must not be <code>null</code>.
	 * @param g		The gaze direction of the camera. Must not be <code>null</code>.
	 * @param t		The up vector of the camera. Must not be <code>null</code>.
	 * @param angle	The opening angle of the camera in radians. Must be a double value between 0 (excluding) and PI 
	 * 				(including).
	 */
	public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle) {
		super(e, g, t);
		if (angle <= 0 || Math.PI < angle) {
			throw new IllegalArgumentException("The parameter 'angle' must be between 0 (excluding) and PI (including).");
		}
		this.angle = angle / 2.0;
	}
	
	@Override
	public Ray rayFor(final int width, final int height, final int x, final int y) {
		final double f1 = height / (-2.0 * Math.tan(angle));
		final double f2 = x - (width  - 1.0) / 2.0;
		final double f3 = y - (height - 1.0) / 2.0;
		final Vector3 r = w.mul(f1).add(u.mul(f2).add(v.mul(f3)));
		return new Ray(e, r.normalized());
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(angle);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PerspectiveCamera other = (PerspectiveCamera) obj;
		if (Double.doubleToLongBits(angle) != Double
				.doubleToLongBits(other.angle))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ",\n\tangle = " + Math.toDegrees(angle) + " deg]";
	}
}
