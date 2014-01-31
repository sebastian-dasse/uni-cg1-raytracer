package raytracer.geometry;

import java.util.LinkedList;

import raytracer.Color;
import raytracer.Ray;
import raytracer.material.Material;
import raytracer.material.SingleColorMaterial;
import raytracer.math.Point3;
import raytracer.math.Transform;

/**
 * TODO comment everything!
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class AxisAlignedBoundingBox {
	
	private static final Material material = new SingleColorMaterial(new Color(0, 0, 0));
	
	private final Point3 lbf;
	private final Point3 run;
	
	private final Plane plane;
	
	private final Node top;
	private final Node right;
	private final Node front;
	private final Node left;
	private final Node bottom;
	private final Node back;
	
	public AxisAlignedBoundingBox(final Point3 lbf, final Point3 run) {
		this.lbf = lbf;
		this.run = run;
		
		plane = new Plane(material);
		
		top = new Node(plane, new Transform().translate(run));
		right = new Node(plane, new Transform().translate(run).rotateZ(-Math.PI / 2.0));
		front = new Node(plane, new Transform().translate(run).rotateZ(Math.PI).rotateX(Math.PI / 2.0));
		left = new Node(plane, new Transform().translate(lbf).rotateZ(Math.PI / 2.0));
		bottom = new Node(plane, new Transform().translate(lbf).rotateX(Math.PI));
		back = new Node(plane, new Transform().translate(lbf).rotateZ(Math.PI).rotateX(-Math.PI / 2.0));
	}
	
	public boolean isHit(final Ray ray) {
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
			final Point3 p = ray.at(hit.t);
			if( p.y >= lbf.y && p.y <= run.y && p.z >= lbf.z && p.z <= run.z) {
				return true;
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
			final Point3 p = ray.at(hit.t);
			if( p.x >= lbf.x && p.x <= run.x && p.z >= lbf.z && p.z <= run.z ) {
				return true;
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
			final Point3 p = ray.at(hit.t);
			if( p.x >= lbf.x && p.x <= run.x && p.y >= lbf.y && p.y <= run.y ) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return getClass().getSimpleName() 
				+ "[\n\tlbf = " + lbf + "\n"
				+ "\trun = " + run + "]";
	}
}
