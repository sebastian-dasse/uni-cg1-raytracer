package raytracer.geometry;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import raytracer.Color;
import raytracer.Ray;
import raytracer.material.SingleColorMaterial;
import raytracer.math.Transform;

public class Node extends Geometry {

	public final Transform transform;
	public final Collection <Geometry> geos;

	public Node(Collection <Geometry> geos, Transform transform) {
		super(new SingleColorMaterial(new Color(0,0,0)));
		this.geos = geos;
		this.transform = transform;
	}
	
	public Node(Geometry geo, Transform transform) {
		this(transform);
		geos.add(geo);
	}
	
	public Node(Transform transform) {
		this(new LinkedList<Geometry>(), transform);
	}
	
	public void add(Geometry geo) {
		geos.add(geo);
	}
	
	public void addAll(Collection<Geometry> geos) {
		geos.addAll(geos);
	}
	
	public void remove(Geometry geo) {
		geos.remove(geo);
	}
	
	public void removeAll(Collection<Geometry> geos) {
		geos.removeAll(geos);
	}
	
	@Override
	public Hit hit(Ray ray) {
		final Ray processedRay = transform.mul(ray);
		double t = Double.POSITIVE_INFINITY;
		Hit nearestHit = null;
		for (Geometry geo : geos) {
			final Hit hit = geo.hit(processedRay);
			if (hit.t < t) {
				nearestHit = hit;
				t = hit.t;
			}
		}
		return nearestHit;
	}
}
