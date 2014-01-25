package raytracer.geometry;

import java.util.Collection;
import java.util.LinkedList;

import raytracer.Color;
import raytracer.Ray;
import raytracer.material.SingleColorMaterial;
import raytracer.math.Transform;

/**
 * TODO comment everything
 */
public class Node extends Geometry {

	public final Transform transform;
	public final Collection <Geometry> geos;

	public Node(final Collection <Geometry> geos, final Transform transform) {
		super(new SingleColorMaterial(new Color(0, 0, 0)));
		this.geos = geos;
		this.transform = transform;
	}
	
	public Node(final Geometry geo, final Transform transform) {
		this(transform);
		geos.add(geo);
	}
	
	public Node(final Transform transform) {
		this(new LinkedList<Geometry>(), transform);
	}
	
	public void add(final Geometry geo) {
		geos.add(geo);
	}
	
	public void addAll(final Collection<Geometry> geos) {
		geos.addAll(geos);
	}
	
	public void remove(final Geometry geo) {
		geos.remove(geo);
	}
	
	public void removeAll(final Collection<Geometry> geos) {
		geos.removeAll(geos);
	}
	
	@Override
	public Hit hit(final Ray ray) {
		final Ray processedRay = transform.mul(ray);
		double t = Double.POSITIVE_INFINITY;
		Hit nearestHit = null;
		for (Geometry geo : geos) {
			final Hit hit = geo.hit(processedRay);
			if (hit != null && hit.t < t) {
				nearestHit = hit;
				t = hit.t;
			}
		}
		if (nearestHit == null) {
			return null;
		}
		return new Hit(
				t, 
				processedRay, 
				nearestHit.geo, 
				transform.mul(
						nearestHit.normal));
//		return nearestHit;
	}
}
