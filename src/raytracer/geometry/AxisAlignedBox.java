package raytracer.geometry;

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
		final LinkedList<Plane> planes = new LinkedList<Plane>();
		if (toRun.dot(TOP) > 0) {
			planes.add(new Plane(run, TOP, color));
		}
		if (toRun.dot(FRONT) > 0) {
			planes.add(new Plane(run, FRONT, color));
		}
		if (toRun.dot(RIGHT) > 0) {
			planes.add(new Plane(run, RIGHT, color));
		}
		if (toLbf.dot(LEFT) > 0) {
			planes.add(new Plane(lbf, LEFT, color));
		}
		if (toLbf.dot(BACK) > 0) {
			planes.add(new Plane(lbf, BACK, color));
		}
		if (toLbf.dot(BOTTOM) > 0) {
			planes.add(new Plane(lbf, BOTTOM, color));
		}
		
		// 2. calculate intersection point(s) - take the greatest t, i.e. the most distant point from the camera view
		// t_i = (a - o).dot(n_i) / d.dot(n_i)
		Hit hitMax = null;
		for (final Plane plane : planes) {
			final Hit h = plane.hit(ray);
			if (h == null) {
				continue;
			}
			if (hitMax == null || hitMax.t < h.t) {
				hitMax = h;
			}
		}
		
		// 3. check, if the found intersection point with the plane lies in/on the box
		// lbf <= p <= run, for all coordinates x, y, z
		final Plane face = (Plane) hitMax.geo;
		// no need to check the coordinate that lies in the in the direction of the normal of the examined plane  
		if (face.n.x == 0 && (face.a.x < lbf.x || run.x < face.a.x)) {
			return null;
		}
		if (face.n.y == 0 && (face.a.y < lbf.y || run.y < face.a.y)) {
			return null;
		}
		if (face.n.z == 0 && (face.a.z < lbf.z || run.z < face.a.z)) {
			return null;
		}
		return new Hit(hitMax.t, ray, this);
	}
	
	@Override
	public String toString() {
		return super.toString() + ",\n\tlbf = " + lbf + ",\n" 
								+ "\trun = " + run + "]";
	}
	
	public static void main(String[] args) {
		System.out.println(new AxisAlignedBox(new Point3(0, 0, 0), new Point3(1, 1, 1), new Color(0.5, 0.5, 0.5)));
	}
}
