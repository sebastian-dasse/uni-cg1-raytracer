package raytracer.camera;

import raytracer.Ray;
import raytracer.math.Point3;
import raytracer.math.Vector3;

public abstract class Camera {
	public final Point3 e;
	public final Vector3 g;
	public final Vector3 t;
	public final Vector3 u;
	public final Vector3 v;
	public final Vector3 w;
	
	
	public abstract void Camera(final Point3 e, final Vector3 g, final Vector3 t);
	
	public abstract Ray rayFor(int w, int h, int x, int y);
	
}
