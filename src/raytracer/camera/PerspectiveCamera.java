package raytracer.camera;

import raytracer.Ray;
import raytracer.math.Point3;
import raytracer.math.Vector3;

public class PerspectiveCamera extends Camera{
	private double angle;
	
	public PerspectiveCamera(final Point3 e,final Vector3 g,final Vector3 t,final Vector3 u, final
			Vector3 v, final Vector3 w, double angle) {
		super(e, g, t, u, v, w);
		this.angle = angle;
	}

	
	@Override
	public void Camera(Point3 e, Vector3 g, Vector3 t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ray rayFor(int w, int h, int x, int y) {
		// TODO Auto-generated method stub
		return null;
	}

}
