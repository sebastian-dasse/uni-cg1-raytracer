package raytracer.camera;

import raytracer.Ray;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * @author 
 *
 */
public class OrthographicCamera extends Camera{
	/**
	 * 
	 */
	private double s;
	
	/**
	 * @param e
	 * @param g
	 * @param t
	 * @param s
	 */
	public OrthographicCamera(final Point3 e, final Vector3 g, final Vector3 t, final double s){
		super(e, g, t);
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
		
		final double disriminant = b * b - 4 * a * c;
		if (disriminant < 0) {
			System.out.println("kein Schnittpunkt");
			System.exit(0);
		}
		final double t1 = (-b - Math.sqrt(disriminant)) / (2 * a);
		final double t2 = (-b + Math.sqrt(disriminant)) / (2 * a);
		
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
	}
}
