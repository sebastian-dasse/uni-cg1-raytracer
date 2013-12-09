package raytracer.geometry;

import java.util.LinkedList;

import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class represents an axis aligned box in three-dimensional space. It is defined through its <em>low 
 * bottom far point</em> (lbf) and its <em>right upper near point</em> (run).
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class AxisAlignedBox extends Geometry {
	
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
	 * Constructs a new <code>AxisAlignedBox</code> with the specified parameters.
	 * 
	 * @param lbf		The low bottom far point of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 * @param run		The right upper near point of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 * @param material	The material of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 */
	public AxisAlignedBox(final Point3 lbf, final Point3 run, final Material material) {
		super(material);
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
		// Formula: <o - a, n_i>  > 0
		final Vector3 toLbf = ray.o.sub(lbf);
		final Vector3 toRun = ray.o.sub(run);
		final LinkedList<Plane> planes = new LinkedList<Plane>();
		if (toRun.dot(TOP) > 0) {
			planes.add(new Plane(run, TOP, material));
		}
		if (toRun.dot(FRONT) > 0) {
			planes.add(new Plane(run, FRONT, material));
		}
		if (toRun.dot(RIGHT) > 0) {
			planes.add(new Plane(run, RIGHT, material));
		}
		if (toLbf.dot(LEFT) > 0) {
			planes.add(new Plane(lbf, LEFT, material));
		}
		if (toLbf.dot(BACK) > 0) {
			planes.add(new Plane(lbf, BACK, material));
		}
		if (toLbf.dot(BOTTOM) > 0) {
			planes.add(new Plane(lbf, BOTTOM, material));
		}
		
		// 2. calculate intersection point(s) - take the greatest t, i.e. the most distant point from the camera view
		// Formula: t_i = <a - o, n_i> / <d, n_i>
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
		if (hitMax == null) {
			return null; // no hit
		}
		
		// 3. check, if the found intersection point with the plane lies in/on the box
		// Formula: lbf <= p <= run, for all coordinates x, y, z
		final Plane face = (Plane) hitMax.geo;
		final Point3 p = ray.at(hitMax.t);
		// no need to check the coordinate that lies in the in the direction of the normal of the examined plane
		if (face.n.x == 0 && (p.x < lbf.x || run.x < p.x)) {
			return null;
		}
		if (face.n.y == 0 && (p.y < lbf.y || run.y < p.y)) {
			return null;
		}
		if (face.n.z == 0 && (p.z < lbf.z || run.z < p.z)) {
			return null;
		}
		return new Hit(hitMax.t, ray, this, hitMax.normal);
	}

	@Override
	public String toString() {
		return super.toString() + ",\n\tlbf = " + lbf + ",\n" 
								+ "\trun = " + run + "]";
	}
}
