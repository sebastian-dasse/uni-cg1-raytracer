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
public class PhongMaterial extends Material{
	/**
	 * 
	 */
	private final Color diffuse;
	/**
	 * 
	 */
	private final Color specular;
	/**
	 * 
	 */
	private final int exponent; 
	
	/**
	 * 
	 * 
	 * @param diffuse
	 * @param specular
	 * @param exponent
	 */
	public PhongMaterial(final Color diffuse, final Color specular, final int exponent){
		this.diffuse = diffuse;
		this.specular = specular;
		this.exponent = exponent;
	}

	@Override
	public Color colorFor(final Hit hit, final World world) {

		final Vector3 e = hit.ray.d.mul(-1).normalized();
//		Color c = color.mul(world.ambientLight);
		final Light[] lights = world.getLights();
		for (Light light : lights) {
			final Normal3 n = hit.normal;
			final Point3 p = hit.ray.at(hit.t);
			final Vector3 l = light.directionFrom(p);
			final Vector3 r = l.reflectedOn(n);
			final double f = Math.max(0, n.dot(l));
//			final Color temp = color.mul(light.color.mul(f));
//			c = c.add(temp);
		}
		
		final double max1 = Math.max(world.ambientLight.r, world.ambientLight.g);
		final double max2 = Math.max(max1, world.ambientLight.b);
//		c = (c.mul(1 / (lights.length + max2)));
//		return c;
		return null;
	}
}
