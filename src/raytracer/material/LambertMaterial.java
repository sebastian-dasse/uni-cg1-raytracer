package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.light.Light;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class implements the color of a material with a perfect diffuse reflecting surface.
 * 
 * @author Simon Lischka
 * @author Sebastian Dass&eacute;
 * @author Maxim Novichkov
 * 
 */
public class LambertMaterial extends Material {
	/**
	 * The color of this material.
	 */
	private final Color color;
	
	/**
	 * Constructs a new <code>LambertMaterial</code> object with the specified surface color.
	 * 
	 * @param color The surface color. Must not be <code>null</code>.
	 */
	public LambertMaterial (final Color color) {
		if (color == null) {
			throw new IllegalArgumentException("The parameter 'color' must not be null.");
		}
		this.color = color;
	}
	
	@Override
	public Color colorFor(final Hit hit, final World world) {
		if (hit == null || world == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		
		// Formula: c = cd[ca  +  cl * max(0, <n, l>)]
		Color c = color.mul(world.ambientLight);
		final Normal3 n = hit.normal;
		final Point3 p = hit.ray.at(hit.t);
		final Light[] lights = world.getLights();
		for (Light light : lights) {
			final Vector3 l = light.directionFrom(p);
			final double f = Math.max(0, n.dot(l));
			final Color temp = color.mul(light.color.mul(f));
			c = c.add(temp);
		}
		return c;
	}
}
