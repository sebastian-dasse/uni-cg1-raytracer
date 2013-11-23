package raytracer.geometry;

import java.io.File;
import java.util.Collection;
import java.util.LinkedList;

import raytracer.Color;
import raytracer.Ray;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * TODO HASCODE & EQUALS
 * TODO DOK IT ALL!
 * This immutable class represents an axis aligned box. ...
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class AxisAlignedBox extends Geometry {
	
	/**
	 * The normal of the left face of the box.
	 */
	private static final Normal3 LEFT   = new Normal3(-1,  0,  0);
	/**
	 * The normal of the right face of the box.
	 */
	private static final Normal3 RIGHT  = new Normal3( 1,  0,  0);
	/**
	 * The normal of the top face of the box.
	 */
	private static final Normal3 TOP    = new Normal3( 0,  1,  0);
	/**
	 * The normal of the bottom face of the box.
	 */
	private static final Normal3 BOTTOM = new Normal3( 0, -1,  0);
	/**
	 * The normal of the front face of the box.
	 */
	private static final Normal3 FRONT  = new Normal3( 0,  0,  1);
	/**
	 * The normal of the back face of the box.
	 */
	private static final Normal3 BACK   = new Normal3( 0,  0, -1);
	/**
	 * The low bottom far point of this <code>AxisAlignedBox</code>.
	 * Is part of the left, the back and the bottom plane.
	 */
	public final Point3 lbf;
	/**
	 * The right upper near point of this <code>AxisAlignedBox</code>.
	 * Is part of the top, the front and the right plane.
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
	}
	
	@Override
	public Hit hit(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("The parameter 'ray' must not be null.");
		}
		
		// 1. which surfaces are visible?
		// (o - a).dot(n_i) > 0
		final Vector3 toLbf = ray.o.sub(lbf);
		final Vector3 toRun = ray.o.sub(run);
		final Collection<Plane> planes = new LinkedList<Plane>();
		Hit hitMax = new Hit(0, null, null); // Alternative
		if (toRun.dot(TOP) > 0) {
//			planes.add(new Plane(run, TOP, color));
			hitMax = check(run, TOP, ray, hitMax);
		}
		if (toRun.dot(FRONT) > 0) {
//			planes.add(new Plane(run, FRONT, color));
			hitMax = check(run, FRONT, ray, hitMax);
		}
		if (toRun.dot(RIGHT) > 0) {
//			planes.add(new Plane(run, RIGHT, color));
			hitMax = check(run, RIGHT, ray, hitMax);
		}
		if (toLbf.dot(LEFT) > 0) {
//			planes.add(new Plane(lbf, LEFT, color));
			hitMax = check(run, LEFT, ray, hitMax);
		}
		if (toLbf.dot(BACK) > 0) {
//			planes.add(new Plane(lbf, BACK, color));
			hitMax = check(run, BACK, ray, hitMax);
		}
		if (toLbf.dot(BOTTOM) > 0) {
//			planes.add(new Plane(lbf, BOTTOM, color));
			hitMax = check(run, BOTTOM, ray, hitMax);
		}
		
		// 2. calculate intersection point(s) - take the greatest t, i.e. the most distant point from the camera view
		// t_i = (a - o).dot(n_i) / d.dot(n_i)
//		Hit hitMax = new Hit(0, ray, this); // TODO das könnte funktionieren
//		double tMax = 0;
//		Plane plane = null;
//		Normal3 norm = null;
		
//		for (Plane pl: planes) {
//			
//			Hit h = pl.hit(ray);
//			
////			hitMax = new Hit(t, ray, pl);
////			tMax = Math.max(tMax, t);
////			if (tMax < t) {
//			if (tMax < h.t) {
//				tMax = h.t;
////				plane = pl;
//				norm = ((Plane) h.geo).n;
//			}
//		}
		
//		Point3 pt = ray.at(tMax);
		
		// 3. check, if the found intersection point with the plane lies in/on the box
		// lbf <= p <= run, for all coordinates x, y, z
//		final Plane plane = (Plane) hitMax.geo;
//		final Point3 p = plane.a;
		// no need to check the coordinate that lies in the in the direction of the normal of the examined plane  
//		if (plane.n.x == 0 && (p.x < lbf.x || run.x < p.x)) {
		if (plane.n.x == 0 && (pt.x < lbf.x || run.x < pt.x)) {
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
	
	@Override
	public String toString() {
		return super.toString() + ",\n\tlbf = " + lbf + ",\n" 
								+ "\trun = " + run + "]";
	}
	
	private Hit check(Point3 a, Normal3 n, Ray ray, Hit hitMax){
		Hit hit = new Plane(a, n, color).hit(ray);
		return (hitMax.t < hit.t) ? hit : hitMax;
	}
	
	public static void main(String[] args) {
		System.out.println(new AxisAlignedBox(new Point3(0, 0, 0), new Point3(1, 1, 1), new Color(0.5, 0.5, 0.5)));
	}
}
