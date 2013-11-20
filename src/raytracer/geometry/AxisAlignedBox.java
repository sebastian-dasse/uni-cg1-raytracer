package raytracer.geometry;

import raytracer.Color;
import raytracer.Ray;
import raytracer.math.Normal3;
import raytracer.math.Point3;

/**
 * @author 
 *
 */
public class AxisAlignedBox extends Geometry {
	
	public static final Normal3 left   = new Normal3(-1,  0,  0);
	public static final Normal3 right  = new Normal3( 1,  0,  0);
	public static final Normal3 top    = new Normal3( 0,  1,  0);
	public static final Normal3 bottom = new Normal3( 0, -1,  0);
	public static final Normal3 front  = new Normal3( 0,  0,  1);
	public static final Normal3 back   = new Normal3( 0,  0, -1);
	private static final Normal3[] n = new Normal3[]{left, right, top, bottom, front, back};
	/**
	 * 
	 */
	public final Point3 lbf;
	/**
	 * 
	 */
	public final Point3 run;
	
	/**
	 * @param lbf	The low bottom far point of the <code>AxisAlignedBox</code>.
	 * @param run	The right uppper near point of the <code>AxisAlignedBox</code>.
	 * @param color	The color of the <code>AxisAlignedBox</code>.
	 */
	public AxisAlignedBox(final Point3 lbf, final Point3 run, final Color color) {
		super(color);
		this.lbf = lbf;
		this.run = run;
	}

	// TODO
	@Override
	public Hit hit(final Ray ray) {
		
		// 1. which surfaces are visible?
		// (o - a).dot(n_i) > 0
		final boolean[] res = new boolean[n.length];
		for (int i= 0; i < n.length; i++) {
			res[i] = ray.o.sub(lbf).dot(n[i]) > 0;
		}
		
		// 2. calculate intersection point(s) - take the greatest t, i.e. the most distant point from the camera view
		
		
		// 3. check, if the found intersection point lies on the right part of the box
		
		
		
		return null;
	}
}
