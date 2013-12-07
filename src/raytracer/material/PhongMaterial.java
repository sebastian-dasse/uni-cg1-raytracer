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
		
		// Formula: c = cd * ca + cd * cl * max(0, n.dot(l)) + cs * cl * max(0, e.dot(r))^p
		final Normal3 n = hit.normal;
		final Point3 p = hit.ray.at(hit.t);
		final Vector3 e = hit.ray.d.mul(-1).normalized();
		Color c = diffuse.mul(world.ambientLight);
		final Light[] lights = world.getLights();
		for (Light light : lights) {
			final Vector3 l = light.directionFrom(p);
			final Vector3 r = l.reflectedOn(n);
			final double f1 = Math.max(0, n.dot(l));
			final double f2 = Math.max(0, e.dot(r));
			final Color s1 = diffuse.mul(light.color).mul(f1);
			final Color s2 = specular.mul(light.color).mul(Math.pow(f2, exponent));
			c = c.add(s1).add(s2); 
		}
		// TODO in Raytracer.normalizeColorComponent(...) verschieben
		final double max1 = Math.max(world.ambientLight.r, world.ambientLight.g);
		final double max2 = Math.max(max1, world.ambientLight.b);
		c = (c.mul(1 / (lights.length + max2)));
//		if (c.r > 1 || c.g >= 1 || c.b >= 1) {
//			System.err.println(c);
//		}
		return c;
	}
}
