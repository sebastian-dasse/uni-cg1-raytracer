package raytracer.camera;

import raytracer.Ray;
import raytracer.math.Normal3;
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
		double ratio = width/height;
		double width_com = (x-(width-1.0)/2.0) / (width-1.0);
		double height_com =  (x-(height-1.0)/2.0) / (height-1.0);
		Point3 o = e.add(u.mul(width_com).mul(s).mul(ratio)).add(v.mul(s).mul(height_com));
		return new Ray(o,w.mul(-1));
	}
}
