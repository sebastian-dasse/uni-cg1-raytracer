package raytracer.geometry;

import java.util.Collection;
import java.util.LinkedList;

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
	 * The low bottom far point of the <code>AxisAlignedBox</code>.
	 */
	public final Point3 lbf;
	/**
	 * The right upper near point of this <code>AxisAlignedBox</code>.
	 */
	public final Point3 run;
	
	/**
	 * Constructs a new <code>AxisAlignedBox</code> with the specified parameters.
	 * 
	 * @param lbf	The low bottom far point of this <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 * @param run	The right upper near point of this <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 * @param color	The color of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 */
	public AxisAlignedBox(final Point3 lbf, final Point3 run, final Color color) {
		super(color);
		if (lbf == null || run == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.lbf = lbf;
		this.run = run;
	}

	// TODO
	@Override
	public Hit hit(final Ray ray) {
		
		// 1. which surfaces are visible?
		// (o - a).dot(n_i) > 0
//		final boolean[] res = new boolean[n.length];
//		for (int i = 0; i < n.length; i++) {
//			// welche von beiden Ecken?
////			res[i] = ray.o.sub(lbf).dot(n[i]) > 0;
//			res[i] = ray.o.sub(run).dot(n[i]) > 0;
//		}
		
		final Collection<Normal3> normals = new LinkedList<Normal3>();
		if (ray.o.sub(run).dot(top) > 0) {
			normals.add(top);
		}
		if (ray.o.sub(run).dot(front) > 0) {
			normals.add(front);
		}
		if (ray.o.sub(run).dot(right) > 0) {
			normals.add(right);
		}
		if (ray.o.sub(lbf).dot(left) > 0) {
			normals.add(left);
		}
		if (ray.o.sub(lbf).dot(back) > 0) {
			normals.add(back);
		}
		if (ray.o.sub(lbf).dot(bottom) > 0) {
			normals.add(bottom);
		}

		final double tMax = 0;
		for (Normal3 normal : normals) {
			
		}
		
		
		
		
		// 2. calculate intersection point(s) - take the greatest t, i.e. the most distant point from the camera view
		
		
		// 3. check, if the found intersection point lies on the right part of the box
		
		
		
		return null;
	}
}
