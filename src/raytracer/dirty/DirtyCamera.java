package raytracer.dirty;

import raytracer.Ray;
import raytracer.math.Point3;
import raytracer.math.Vector3;

public class DirtyCamera {
	public final Point3 e;
	public final Vector3 g;
	public final Vector3 t;
	public final Vector3 u;
	public final Vector3 v;
	public final Vector3 w;
	
	public DirtyCamera(final Point3 e, final Vector3 g, final Vector3 t) {
		this.e = e;
		this.g = g;
		this.t = t;
		
		w = g.normalized().mul(-1.0);
		u = t.x(w).normalized();
		v = w.x(u);
	}
	
	public Ray rayFor(final int w, final int h, final int x, final int y) {
		return null;
	}
	
	public static void main(String[] args) {
		DirtyCamera cam = new DirtyCamera(new Point3(4, 4, 4), 
										  new Vector3(-4, -4, -4), 
										  new Vector3(0, 1, 0));
		System.out.println("u = " + cam.u);
		System.out.println("v = " + cam.v);
		System.out.println("w = " + cam.w);
		System.out.println();
		
		Vector3 d = cam.w.mul(-1);
		System.out.println("d = -w = " + d);
		
		final int width = 1920;
		final int height = 1200;
		final double aspectRatio = (double) width / height;
		final int s = 3;
		System.out.println("width = " + width + ", height = " + height + ", aspectRatio = " + aspectRatio + ", s = " + s);
		
		final int px = 1000;
		final int py = 800;
		
		final double f1 = aspectRatio * s * (px - (width  - 1.0) / 2) / (width  - 1.0);
		final double f2 = 				s * (py - (height - 1.0) / 2) / (height - 1.0);
		
		Point3 o = cam.e.add(cam.u.mul(f1).add(cam.v.mul(f2)));
		System.out.println("o = " + o);
		
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
