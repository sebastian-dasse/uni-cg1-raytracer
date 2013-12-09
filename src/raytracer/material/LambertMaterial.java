package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.light.Light;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class implements the color of a material with a perfect diffuse surface.
 * @author Simon Lischka;
 * @author Sebastian Dass&eacute;
 * @author Maxim Novichkov;
 */
public class LambertMaterial extends Material{
	/**
	 * The color of a surface material.
	 */
	private final Color color;
	
	/**
	 * @param color The surface color.
	 */
	public LambertMaterial (final Color color){
		this.color = color;
	}
	
	@Override
	public Color colorFor(final Hit hit, final World world) {
		
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
		// TODO in Raytracer.normalizeColorComponent(...) verschieben 
		final double max1 = Math.max(world.ambientLight.r, world.ambientLight.g);
		final double max2 = Math.max(max1, world.ambientLight.b);
		return (c.mul(1 / (lights.length + max2)));
	}
}
