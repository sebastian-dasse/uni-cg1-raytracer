package raytracer.camera;

import static raytracer.math.MathUtil.inRange;
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
	 * @param angle	The half opening angle of the camera in degrees. Must be a double value other between 0 and 90 
	 * 				(including).
	 */
	public PerspectiveCamera(final Point3 e, final Vector3 g, final Vector3 t, final double angle) {
		super(e, g, t);
		if (!inRange(angle, 0, 90)) {
			throw new IllegalArgumentException("The parameter 'angle' must be between 0 and 90 (including).");
		}
		this.angle = Math.toRadians(angle);
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
	
	//---- Test
	public static void main(String[] args) {
		PerspectiveCamera cam = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-4, -4, -4), new Vector3(0, 1, 0), 45);
		Ray ray = cam.rayFor(1920, 1200, 1000, 800);
		final Point3 o = ray.o;
		final Vector3 d = ray.d;
		System.out.println(o);
		System.out.println(d);
		
		System.out.println();
		System.out.println(new PerspectiveCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), 45));
	}
}
