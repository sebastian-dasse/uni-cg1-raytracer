package raytracer.material;

import raytracer.Color;
import raytracer.Tracer;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.light.Light;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.texture.Texture;

/**
 * This immutable class implements the color of a material with a perfect diffuse reflecting surface and a specular point.
 * 
 * @author Simon Lischka
 *
 */
public class PhongMaterial extends Material {
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
	 * Constructs a new <code>PhongMaterial</code> object with the specified parameters.
	 * 
	 * @param diffuse   The surface texture. Must not be <code>null</code>.
	 * @param specular	The texture of the specular point. Must not be <code>null</code>.
	 * @param exponent	The scale of the specular point. Must be a positive value below 1024.
	 */
	public PhongMaterial(final Texture diffuseTexture, final Texture specularTexture, final int exponent) {
		if (diffuseTexture == null || specularTexture == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		if (exponent < 0 || Double.MAX_EXPONENT < exponent) {
			throw new IllegalArgumentException("The parameter 'exponent' must be a positive int value below 1024.");
		}
		this.diffuseTexture = diffuseTexture;
		this.specularTexture = specularTexture;
		this.exponent = exponent;
	}

	@Override
	public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
		if (hit == null || world == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		
		// Formula: c = cd * ca  +  cd * cl * max(0, <n, l>)  +  cs * cl * pow(max(0, <e, r>), p)
		final Normal3 n = hit.normal;
		final Point3 p = hit.ray.at(hit.t);
		final Vector3 e = hit.ray.d.mul(-1).normalized();
		final Color diffuseColor = diffuseTexture.getColor(hit.texcoord);
		final Color specularColor = specularTexture.getColor(hit.texcoord);
		Color c = diffuseColor.mul(world.ambientLight);
		final Light[] lights = world.getLights();
		for (Light light : lights) {
			if (light.illuminates(p, world)) {
				final Vector3 l = light.directionFrom(p);
				final Vector3 r = l.reflectedOn(n);
				final double f1 = Math.max(0, n.dot(l));
				final double f2 = Math.max(0, e.dot(r));
				final Color s1 = diffuseColor.mul(light.color).mul(f1);
				final Color s2 = specularColor.mul(light.color).mul(Math.pow(f2, exponent));
				c = c.add(s1).add(s2); 
			}
		}
		return c;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diffuseTexture == null) ? 0 : diffuseTexture.hashCode());
		result = prime * result + exponent;
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
		final PhongMaterial other = (PhongMaterial) obj;
		if (diffuseTexture == null) {
			if (other.diffuseTexture != null)
				return false;
		} else if (!diffuseTexture.equals(other.diffuseTexture))
			return false;
		if (exponent != other.exponent)
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
		return getClass().getSimpleName() + "[matcoord = " + diffuseTexture + ",\n" 
						+ "\tspeccoord = " + specularTexture + ",\n" 
						+ "\texponent = " + exponent + "]";
	}
}
