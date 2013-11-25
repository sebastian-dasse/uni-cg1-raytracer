package raytracer.camera;

import static raytracer.math.MathUtil.isValid;
import raytracer.Ray;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class represents an orthographic camera. All rays have the same direction but a different origin. The 
 * scaling factor defines the size of the scene.
 * 
 * @author Simon Lischka
 * @author Sebastian Dass&eacute;
 *
 */
public class OrthographicCamera extends Camera{
	/**
	 * The scaling factor of this camera.
	 */
	private final double s;
	
	/**
	 * Constructs a new <code>OrthographicCamera</code> with the specified parameters.
	 * 
	 * @param e	The eye position. Must not be <code>null</code>.
	 * @param g	The gaze direction. Must not be <code>null</code>.
	 * @param t	The up vector. Must not be <code>null</code>.
	 * @param s	The scaling factor of the camera. Must be a double value other than +-Infinity or NaN.
	 */
	public OrthographicCamera(final Point3 e, final Vector3 g, final Vector3 t, final double s){
		super(e, g, t);
		if (!isValid(s)) {
			throw new IllegalArgumentException("Only a double value other than +-Infinity or NaN is allowed.");
		}
		this.s = s;
	}
	
	@Override
	public Ray rayFor(final int width, final int height, final int x, final int y) {
		final double ratio = (double) width / height;
		final double f1 = ratio * s * (x - (width  - 1.0) / 2.0) / (width  - 1.0);
		final double f2 =         s * (y - (height - 1.0) / 2.0) / (height - 1.0);
		final Point3 o = e.add(u.mul(f1).add(v.mul(f2)));
		return new Ray(o, w.mul(-1));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(s);
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
		final OrthographicCamera other = (OrthographicCamera) obj;
		if (Double.doubleToLongBits(s) != Double.doubleToLongBits(other.s))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ",\n\ts = " + s + "]";
	}
}
