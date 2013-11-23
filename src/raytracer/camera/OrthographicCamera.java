package raytracer.camera;

import static raytracer.math.MathUtil.isValid;
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
	
	//---- Test
	public static void main(String[] args) {
		final Camera cam = new OrthographicCamera(new Point3(4, 4, 4), new Vector3(-4, -4, -4), new Vector3(0, 1, 0), 3);
		final Ray ray = cam.rayFor(1920, 1200, 1000, 800);
		final Point3 o = ray.o;
		final Vector3 d = ray.d;
		
		System.out.println(o);
		System.out.println(d);
		
		final Point3 center = new Point3(-1, -1, -1);
		final double r = 3;
		
		final double a = d.dot(d);
		final double b = d.dot((o.sub(center).mul(2)));
		final double c = o.sub(center).dot(o.sub(center)) - r * r;
		
		System.out.println("a = " + a);
		System.out.println("b = " + b);
		System.out.println("c = " + c);
		
		final double discriminant = b * b - 4 * a * c;
		if (discriminant < 0) {
			System.out.println("kein Schnittpunkt");
			System.exit(0);
		}
		final double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
		final double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);
		
		final Point3 p1 = o.add(d.mul(t1));
		if (t1 == t2) {
			System.out.println("ein Schnittpunkt:");
			System.out.println(p1);
			System.exit(0);
		}
		System.out.println("zwei Schnittpunkte:");
		final Point3 p2 = o.add(d.mul(t2));
		System.out.println(p1);
		System.out.println(p2);
		
		System.out.println();
		System.out.println(new OrthographicCamera(new Point3(0, 0, 0), new Vector3(0, 0, -1), new Vector3(0, 1, 0), 1));
	}
}
