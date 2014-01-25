package raytracer.geometry;

import java.util.Collection;
import java.util.LinkedList;

import raytracer.Ray;
import raytracer.math.Transform;

public class Node extends Geometry {

	public final Transform transform;
	public final Geometry geo;
	public final LinkedList<Geometry> geos;

	public Node(Geometry geo, Transform transform) {
		super(geo.material);
		this.geo = geo;
		this.transform = transform;
		geos = new LinkedList<Geometry>();
	}
	
	public void addChild(Geometry geo) {
		geos.add(geo);
	}
	
	public void addChildren(Collection<Geometry> geos) {
		geos.addAll(geos);
	}
	
	public void removeChild(Geometry geo) {
		geos.remove(geo);
	}
	
	public void removeChildren(Collection<Geometry> geos) {
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
