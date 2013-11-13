package raytracer.camera;

import raytracer.Ray;
import raytracer.math.Point3;
import raytracer.math.Vector3;

public class OrthographicCamera extends Camera{
	private final double s;
	
	public OrthographicCamera(double s, final Point3 e, final Vector3 g, final Vector3 t, final Vector3 u, final Vector3 v, final Vector3 w){
		super(e, g, t, u, w, v);
		this.s = s;
	}
	
	public void OrthographicCamera(Point3 e, Vector3 g, Vector3 t, double s){
		
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
