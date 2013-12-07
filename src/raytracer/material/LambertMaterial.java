package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.light.Light;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * 
 * 
 * @author 
 *
 */
public class LambertMaterial extends Material{
	/**
	 * 
	 */
	private final Color color;
	
	/**
	 * 
	 * 
	 * @param color
	 */
	public LambertMaterial (final Color color){
		this.color = color;
	}
	
	@Override
	public Color colorFor(final Hit hit, final World world) {
		
		// Formula: c = cr[ca + cl * max(0, n.dot(l))]
		Color c = color.mul(world.ambientLight);
		final Light[] lights = world.getLights();
		for (Light light : lights) {
			final Normal3 n = hit.normal;
			final Point3 p = hit.ray.at(hit.t);
			final Vector3 l = light.directionFrom(p);
			final double f = Math.max(0, n.dot(l));
			final Color temp = color.mul(light.color.mul(f));
			c = c.add(temp);
		}
<<<<<<< HEAD
//		return c.mul(0.07);
//		return c;
		return c.mul(1 / (lights.length + 0.0));
=======
		
		final double max1 = Math.max(world.ambientLight.r, world.ambientLight.g);
		final double max2 = Math.max(max1, world.ambientLight.b);
		c = (c.mul(1 / (lights.length + max2)));
		return c;
>>>>>>> f11eb3d3fae72d263c2cce2e30fb01a08a1de464
	}
}
