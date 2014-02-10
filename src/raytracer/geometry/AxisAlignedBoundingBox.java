package raytracer.geometry;

import java.util.LinkedList;

import raytracer.Color;
import raytracer.Ray;
import raytracer.material.Material;
import raytracer.material.SingleColorMaterial;
import raytracer.math.Point3;
import raytracer.math.Transform;
import raytracer.texture.SingleColorTexture;

/**
 * TODO comment everything!
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class AxisAlignedBoundingBox {
	
	private static final Material material = new SingleColorMaterial(new SingleColorTexture(new Color(0, 0, 0)));
	
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((back == null) ? 0 : back.hashCode());
		result = prime * result + ((bottom == null) ? 0 : bottom.hashCode());
		result = prime * result + ((front == null) ? 0 : front.hashCode());
		result = prime * result + ((lbf == null) ? 0 : lbf.hashCode());
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((plane == null) ? 0 : plane.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		result = prime * result + ((run == null) ? 0 : run.hashCode());
		result = prime * result + ((top == null) ? 0 : top.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AxisAlignedBoundingBox other = (AxisAlignedBoundingBox) obj;
		if (back == null) {
			if (other.back != null)
				return false;
		} else if (!back.equals(other.back))
			return false;
		if (bottom == null) {
			if (other.bottom != null)
				return false;
		} else if (!bottom.equals(other.bottom))
			return false;
		if (front == null) {
			if (other.front != null)
				return false;
		} else if (!front.equals(other.front))
			return false;
		if (lbf == null) {
			if (other.lbf != null)
				return false;
		} else if (!lbf.equals(other.lbf))
			return false;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (plane == null) {
			if (other.plane != null)
				return false;
		} else if (!plane.equals(other.plane))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		if (run == null) {
			if (other.run != null)
				return false;
		} else if (!run.equals(other.run))
			return false;
		if (top == null) {
			if (other.top != null)
				return false;
		} else if (!top.equals(other.top))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() 
				+ "[\n\tlbf = " + lbf + "\n"
				+ "\trun = " + run + "]";
	}
}
