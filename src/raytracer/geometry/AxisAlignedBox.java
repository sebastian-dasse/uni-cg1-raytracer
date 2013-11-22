package raytracer.geometry;

import java.util.Collection;
import java.util.LinkedList;

import raytracer.Color;
import raytracer.Ray;
import raytracer.math.Normal3;
import raytracer.math.Point3;

/**
 * TODO HASCODE & EQUALS
 * TODO DOK IT ALL!
 * This immutable class represents an axis aligned box. ...
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class AxisAlignedBox extends Geometry {
	
	private static final Normal3 LEFT   = new Normal3(-1,  0,  0);
	private static final Normal3 RIGHT  = new Normal3( 1,  0,  0);
	private static final Normal3 TOP    = new Normal3( 0,  1,  0);
	private static final Normal3 BOTTOM = new Normal3( 0, -1,  0);
	private static final Normal3 FRONT  = new Normal3( 0,  0,  1);
	private static final Normal3 BACK   = new Normal3( 0,  0, -1);
	// TODO not used?
	private static final Normal3[] n = new Normal3[]{LEFT, RIGHT, TOP, BOTTOM, FRONT, BACK};
	
	private final Plane left_p;
	private final Plane right_p;
	private final Plane top_p;
	private final Plane bottom_p;
	private final Plane front_p;
	private final Plane back_p;
	/**
	 * The low bottom far point of this <code>AxisAlignedBox</code>.
	 */
	public final Point3 lbf;
	/**
	 * The right upper near point of this <code>AxisAlignedBox</code>.
	 */
	public final Point3 run;
	
	/**
	 * Constructs a new <code>AxisAlignedBox</code> with the specified parameters.
	 * 
	 * @param lbf	The low bottom far point of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 * @param run	The right upper near point of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 * @param color	The color of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 */
	public AxisAlignedBox(final Point3 lbf, final Point3 run, final Color color) {
		super(color);
		if (lbf == null || run == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.lbf = lbf;
		this.run = run;
		
		left_p = new Plane(run, LEFT, color);
		right_p = new Plane(lbf, RIGHT, color);
		top_p = new Plane(run, TOP, color);
		bottom_p = new Plane(lbf, BOTTOM, color);
		front_p = new Plane(run, FRONT, color);
		back_p = new Plane(lbf, BACK, color);
	}

	// TODO
	@Override
	public Hit hit(final Ray ray) {
		
		// 1. which surfaces are visible?
		// (o - a).dot(n_i) > 0
		final Collection<Plane> planes = new LinkedList<Plane>();
		if (ray.o.sub(run).dot(TOP) > 0) {
			planes.add(top_p);
		}
		if (ray.o.sub(run).dot(FRONT) > 0) {
			planes.add(front_p);
		}
		if (ray.o.sub(run).dot(RIGHT) > 0) {
			planes.add(right_p);
		}
		if (ray.o.sub(lbf).dot(LEFT) > 0) {
			planes.add(left_p);
		}
		if (ray.o.sub(lbf).dot(BACK) > 0) {
			planes.add(back_p);
		}
		if (ray.o.sub(lbf).dot(BOTTOM) > 0) {
			planes.add(bottom_p);
		}
		
		// 2. calculate intersection point(s) - take the greatest t, i.e. the most distant point from the camera view
		// t_i = (a - o).dot(n_i) / d.dot(n_i)
//		Hit hitMax = new Hit(0, ray, this);
		double tMax = 0;
		Plane plane = null;
		for (Plane pl: planes) {
			double denominator = ray.d.dot(pl.n);
			if (denominator == 0) {
				continue;
			}
			final double t = pl.a.sub(ray.o).dot(pl.n);
//			hitMax = new Hit(t, ray, pl);
//			tMax = Math.max(tMax, t);
			if (tMax < t) {
				tMax = t;
				plane = pl;
			}
		}
		
		// 3. check, if the found intersection point with the plane lies in/on the box
		// lbf <= p <= run, for all coordinates x, y, z
//		final Plane plane = (Plane) hitMax.geo;
//		final Point3 p = plane.a;
		final Point3 p = plane.a;
		// no need to check the coordinate that lies in the in the direction of the normal of the examined plane  
		if (plane.n.x == 0 && (p.x < lbf.x || run.x < p.x)) {
			return null;
		}
		if (plane.n.y == 0 && (p.y < lbf.y || run.y < p.y)) {
			return null;
		}
		if (plane.n.z == 0 && (p.z < lbf.z || run.z < p.z)) {
			return null;
		}
//		return new Hit(hitMax.t, ray, this);
		return new Hit(tMax, ray, this);
	}
	
	public String toString() {
		return super.toString() + ",\n\tlbf = " + lbf + ",\n" 
								+ "\trun = " + run + "]";
	}
	
	public static void main(String[] args) {
		System.out.println(new AxisAlignedBox(new Point3(0, 0, 0), new Point3(1, 1, 1), new Color(0.5, 0.5, 0.5)));
	}
}
