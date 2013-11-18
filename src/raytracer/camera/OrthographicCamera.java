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
	public Ray rayFor(final int w, final int h, final int x, final int y) {
		// TODO Auto-generated method stub
		return null;
	}
}
