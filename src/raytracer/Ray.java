package raytracer;

import raytracer.math.Point3;
import raytracer.math.Vector3;

public class Ray {
	private final Point3 o;
	private final Vector3 d;
	
	public Ray(Point3 o, Vector3 d) {
		this.o = o;
		this.d = d;
	}
	
	public Point3 at(Double t) {
		return null;
	}
	
	public double tOf(Point3 p) {
		return 0;
	}
}
