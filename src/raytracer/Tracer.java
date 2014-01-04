package raytracer;

import raytracer.geometry.Hit;

public class Tracer {
	
	private int recursions;

	public Tracer(final int recursions) {
		this.recursions = recursions;
	}
	
	public Color trace(final Ray ray, final World world) {
		if (recursions < 0) {
			return world.backgroundColor;
		}
		final Hit hit = world.hit(ray);
		if (hit == null) {
			return world.backgroundColor;
		}
		return hit.geo.material.colorFor(hit, world/*, new Tracer(recursions-1)*/);
	}

}
