package raytracer.geometry;

import java.util.LinkedList;

import raytracer.Constants;
import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Point3;
import raytracer.math.Transform;

/**
 * This immutable class represents an axis aligned box in three-dimensional space. It has a default width, height and 
 * depth of 1. It is defined through its <em>low bottom far point</em> (lbf) at (-0.5, -0.5, -0.5) and its <em>right 
 * upper near point</em> (run) at (0.5, 0.5, 0.5).
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class AxisAlignedBox extends Geometry {
	
	/**
	 * The default low bottom far point of this <code>AxisAlignedBox</code>.
	 * Is part of the left, the back and the bottom plane.
	 */
	private static final Point3 lbf = new Point3(-0.5, -0.5, -0.5);
	/**
	 * The default right upper near point of this <code>AxisAlignedBox</code>.
	 * Is part of the top, the front and the right plane.
	 */
	private static final Point3 run = new Point3(0.5, 0.5, 0.5);

	private static final Transform topT = new Transform().translate(run);
	private static final Transform rightT = new Transform().translate(run).rotateZ(-Math.PI / 2.0);
	private static final Transform frontT = new Transform().translate(run).rotateZ(Math.PI).rotateX(Math.PI / 2.0);
	private static final Transform lefT = new Transform().translate(lbf).rotateZ(Math.PI / 2.0);
	private static final Transform bottomT = new Transform().translate(lbf).rotateX(Math.PI);
	private static final Transform backT = new Transform().translate(lbf).rotateZ(Math.PI).rotateX(-Math.PI / 2.0);
	
	private final Plane plane = new Plane(material);
	private final Node top = new Node(plane, topT);
	private final Node right = new Node(plane, rightT);
	private final Node front = new Node(plane, frontT);
	private final Node left = new Node(plane, lefT);
	private final Node bottom = new Node(plane, bottomT);
	private final Node back = new Node(plane, backT);
	
	/**
	 * Constructs a new <code>AxisAlignedBox</code> with the specified material.
	 * 
	 * @param material	The material of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 */
	public AxisAlignedBox(final Material material) {
		super(material);
	}
	
	@Override
	public Hit hit(final Ray ray) {
		final LinkedList<Hit> hits = new LinkedList<Hit>();
		final LinkedList<Hit> hitsOnPlane = new LinkedList<Hit>();
		final Hit rightHit = right.hit(ray);
		final Hit leftHit = left.hit(ray);
		if (rightHit != null) {
			hitsOnPlane.add(rightHit);
		}
		if (leftHit != null) {
			hitsOnPlane.add(leftHit);
		}
		for (Hit hit : hitsOnPlane) {
			Point3 p = ray.at(hit.t);
			 if( p.y >= lbf.y && p.y <= run.y && p.z >= lbf.z && p.z <= run.z) {
				 hits.add(hit);
			 }
		}
		hitsOnPlane.clear();
		
		final Hit topHit = top.hit(ray);
		final Hit bottomHit = bottom.hit(ray);
		if (topHit != null) {
			hitsOnPlane.add(topHit);
		}
		if (bottomHit != null) {
			hitsOnPlane.add(bottomHit);
		}
		for (Hit hit : hitsOnPlane) {
			Point3 p = ray.at(hit.t);
			 if( p.x >= lbf.x && p.x <= run.x && p.z >= lbf.z && p.z <= run.z ) {
				 hits.add(hit);
			 }
		}
		hitsOnPlane.clear();
		
		final Hit frontHit = front.hit(ray);
		final Hit backHit = back.hit(ray);
		if (frontHit != null) {
			hitsOnPlane.add(frontHit);
		}
		if (backHit != null) {
			hitsOnPlane.add(backHit);
		}
		for (Hit hit : hitsOnPlane) {
			Point3 p = ray.at(hit.t);
			 if( p.x >= lbf.x && p.x <= run.x && p.y >= lbf.y && p.y <= run.y ) {
				 hits.add(hit);
			 }
		}
		
		Hit nearestHit = null;
		//---- alternativ zu Zeile 139-141:
//		double t = Double.MAX_VALUE;
//		for (Hit hit : hits) {
//			if (hit.t > Constants.EPSILON && hit.t - t < Constants.EPSILON) {
		double t = -1;
		for (Hit hit : hits) {
			if ((hit.t < t || t == -1) && hit.t > Constants.EPSILON) {
				t = hit.t;
				nearestHit = hit;
			}
		}
		return nearestHit;
	}

	// TODO might be useless now
	@Override
	public String toString() {
		return super.toString() + ",\n\tlbf = " + lbf + ",\n" 
								+ "\trun = " + run + "]";
	}
}
