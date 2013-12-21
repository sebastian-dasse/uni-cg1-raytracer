package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.light.Light;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class implements the color of a material with a perfect diffuse reflecting surface and a specular point.
 * 
 * @author Simon Lischka
 *
 */
public class PhongMaterial extends Material {
	/**
	 * The surface color of this material.
	 */
	private final Color diffuse;
	/**
	 * The color of the specular point of this material.
	 */
	private final Color specular;
	/**
	 * The scale of the specular point of this material.
	 */
	private final int exponent; 
	
	/**
	 * Constructs a new <code>PhongMaterial</code> object with the specified parameters.
	 * 
	 * @param diffuse   The surface color. Must not be <code>null</code>.
	 * @param specular	The color of the specular point. Must not be <code>null</code>.
	 * @param exponent	The scale of the specular point. Must be a positive value below 1024.
	 */
	public PhongMaterial(final Color diffuse, final Color specular, final int exponent) {
		if (diffuse == null || specular == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		if (exponent < 0 || Double.MAX_EXPONENT < exponent) {
			throw new IllegalArgumentException("The parameter 'exponent' must be a positive int value below 1024.");
		}
		this.diffuse = diffuse;
		this.specular = specular;
		this.exponent = exponent;
	}

	@Override
	public Color colorFor(final Hit hit, final World world) {
		if (hit == null || world == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		
		// Formula: c = cd * ca  +  cd * cl * max(0, <n, l>)  +  cs * cl * pow(max(0, <e, r>), p)
		final Normal3 n = hit.normal;
		final Point3 p = hit.ray.at(hit.t);
		final Vector3 e = hit.ray.d.mul(-1).normalized();
		Color c = diffuse.mul(world.ambientLight);
		final Light[] lights = world.getLights();
		for (Light light : lights) {
			if (light.illuminates(p)) {
				final Vector3 l = light.directionFrom(p);
				final Vector3 r = l.reflectedOn(n);
				final double f1 = Math.max(0, n.dot(l));
				final double f2 = Math.max(0, e.dot(r));
				final Color s1 = diffuse.mul(light.color).mul(f1);
				final Color s2 = specular.mul(light.color).mul(Math.pow(f2, exponent));
				c = c.add(s1).add(s2); 
			}
		}
		return c;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((diffuse == null) ? 0 : diffuse.hashCode());
		result = prime * result + exponent;
		result = prime * result
				+ ((specular == null) ? 0 : specular.hashCode());
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
		if (diffuse == null) {
			if (other.diffuse != null)
				return false;
		} else if (!diffuse.equals(other.diffuse))
			return false;
		if (exponent != other.exponent)
			return false;
		if (specular == null) {
			if (other.specular != null)
				return false;
		} else if (!specular.equals(other.specular))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[diffuse = " + diffuse + ",\n" 
						+ "\tspecular = " + specular + ",\n" 
						+ "\texponent = " + exponent + "]";
	}
}
