package raytracer;

import raytracer.geometry.Hit;

/**
 *  This class represents a tracer object to enable 
 *  recursive raytracing. It generates new tracer objects until the
 *  recursion depth specified in the constructor is reached.
 *  <br>
 *  It calls the colorFor method with its parents hit object and is used e.g. 
 *  for reflections.
 * 
 * @author Sebastian Dass&eacute;
 */
public class Tracer {
	/** 
	 * Recursion depth
	 */
	private int recursions;
    /**
     * Constructor of the tracer object, recursion depth is specified
     * @param recursions The recursion depth of the tracer object
     */
	public Tracer(final int recursions) {
		this.recursions = recursions;
	}
	/**
     * Trace method, generates a hit using the world's hit function and
     * uses the resulting hit to generate new Tracer objects.
     * @param ray    Input ray to be ray traced
     * @param world  World used for generating hits
     */
	public Color trace(final Ray ray, final World world) {
		if (recursions < 0) {
			return world.backgroundColor;
		}
		final Hit hit = world.hit(ray);
		if (hit == null) {
			return world.backgroundColor;
		}
		return hit.geo.material.colorFor(hit, world, new Tracer(recursions - 1));
	}

}
