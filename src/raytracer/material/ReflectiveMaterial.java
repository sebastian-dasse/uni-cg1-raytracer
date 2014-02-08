package raytracer.material;

import raytracer.Color;
import raytracer.Ray;
import raytracer.Tracer;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.light.Light;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.texture.Texture;

/**
 * This immutable class implements the color of a material with a perfect reflecting surface.
 * 
 * @author Maxim Novichkov
 * @author Sebastian Dass&eacute;
 */
public class ReflectiveMaterial extends Material{
	/**
	 * The surface texture of this material.
	 */
	private final Texture diffuseTexture;
	/**
	 * The texture of the specular point of this material.
	 */
	private final Texture specularTexture;
	/**
	 * The scale of the specular point of this material.
	 */
	private final int exponent;
	/**
	 * The reflection texture of this material.
	 */
	private final Texture reflectionTexture;
	
	/**
	 * Constructs a new <code>ReflectiveMaterial</code> object with the specified parameters.
	 * 
	 * @param diffuseTexture    The surface texture. Must not be <code>null</code>.
	 * @param specularTexture   The texture of the specular point. Must not be <code>null</code>.
	 * @param exponent   		The scale of the specular point. Must be a positive value below 1024.
	 * @param reflectionTexture The reflection texture of the surface. Must not be <code>null</code>.
	 */
	public ReflectiveMaterial(final Texture diffuseTexture, final Texture specularTexture, final int exponent, 
			final Texture reflectionTexture){
		this.diffuseTexture = diffuseTexture;
		this.specularTexture = specularTexture;
		this.exponent = exponent;
		this.reflectionTexture = reflectionTexture;
	}
	
	@Override
	public Color colorFor(final Hit hit, final World world, final Tracer tracer) {

		// Formula: c = cd * ca  +  cd * cl * max(0, <n, l>)  +  cs * cl * pow(max(0, <e, rl>), p)  +  cr * fr[pr, rd]
		final Normal3 n = hit.normal;
		final Ray ray = hit.ray;
		final Point3 p = ray.at(hit.t);
		final Vector3 e = ray.d.mul(-1).normalized();
		final Color diffuseColor = diffuseTexture.getColor(hit.texcoord);
		final Color specularColor = specularTexture.getColor(hit.texcoord);
		final Color reflectionColor = reflectionTexture.getColor(hit.texcoord);
		Color c = diffuseColor.mul(world.ambientLight);
		final Light[] lights = world.getLights();
		for (Light light : lights) {
			if (light.illuminates(p, world)) {
				final Vector3 l = light.directionFrom(p);
				final Vector3 rl = l.reflectedOn(n);
				final double f1 = Math.max(0, n.dot(l));
				final double f2 = Math.max(0, e.dot(rl));
				final Color s1 = diffuseColor.mul(light.color).mul(f1);
				final Color s2 = specularColor.mul(light.color).mul(Math.pow(f2, exponent));
				c = c.add(s1).add(s2); 
			}
		}
		final Vector3 rd = ray.d.mul(-1).reflectedOn(n);
		final Color s3 = reflectionColor.mul(tracer.trace(new Ray(p, rd), world));
		return c.add(s3);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diffuseTexture == null) ? 0 : diffuseTexture.hashCode());
		result = prime * result + exponent;
		result = prime * result
				+ ((reflectionTexture == null) ? 0 : reflectionTexture.hashCode());
		result = prime * result
				+ ((specularTexture == null) ? 0 : specularTexture.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ReflectiveMaterial other = (ReflectiveMaterial) obj;
		if (diffuseTexture == null) {
			if (other.diffuseTexture != null)
				return false;
		} else if (!diffuseTexture.equals(other.diffuseTexture))
			return false;
		if (exponent != other.exponent)
			return false;
		if (reflectionTexture == null) {
			if (other.reflectionTexture != null)
				return false;
		} else if (!reflectionTexture.equals(other.reflectionTexture))
			return false;
		if (specularTexture == null) {
			if (other.specularTexture != null)
				return false;
		} else if (!specularTexture.equals(other.specularTexture))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[diffuseTexture = " + diffuseTexture + ",\n" 
						+ "\tspecularTexture = " + specularTexture + ",\n" 
						+ "\texponent = " + exponent + ",\n" 
						+ "\treflectionTexture = " + reflectionTexture + "]";
	}
}
