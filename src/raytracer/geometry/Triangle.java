package raytracer.geometry;

import raytracer.Color;
import raytracer.Ray;
import raytracer.math.Mat3x3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * @author 
 *
 */
public class Triangle extends Geometry {
	/**
	 * 
	 */
	public final Point3 a;
	/**
	 * 
	 */
	public final Point3 b;
	/**
	 * 
	 */
	public final Point3 c;
	
	/**
	 * @param a
	 * @param b
	 * @param c
	 * @param color
	 */
	public Triangle(final Point3 a, final Point3 b, final Point3 c, final Color color) {
		super(color);
		if (a == null || b == null || c == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}	
			this.a = a;
			this.b = b;
			this.c = c;
	}

	@Override
	public Hit hit(final Ray ray) {
		
		Mat3x3 matrix = new Mat3x3(a.x - b.x, a.x - c.x, ray.d.x,
								   a.y - b.y, a.y - c.y, ray.d.y,
								   a.z - b.z, a.z - c.z, ray.d.z);
		
		final Vector3 dvector = a.sub(ray.o);
		
		final double determinanta = matrix.determinant;
			if(determinanta == 0) {
				throw new IllegalArgumentException("The parameter must not be null.");
			}
			
		final double betta = matrix.changeCol1(dvector).determinant/determinanta;
		final double gamma = matrix.changeCol2(dvector).determinant/determinanta;
		final double t = matrix.changeCol3(dvector).determinant/determinanta;
		
		if(gamma < 0.0 || betta < 0.0 || betta + gamma > 1.0 || t < 0.0){
			return null;
		} else {
			return new Hit(t, ray, this);
		}
		
	}
}
