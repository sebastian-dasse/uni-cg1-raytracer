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
	private final Texture texture;
	
	/**
	 * Constructs a new <code>LambertMaterial</code> object with the specified surface color.
	 * 
	 * @param color The surface color. Must not be <code>null</code>.
	 */
	public LambertMaterial (final Texture texture) {
		if (texture == null) {
			throw new IllegalArgumentException("The parameter 'color' must not be null.");
		}
		this.texture = texture;
	}
	
	@Override
	public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
		if (hit == null || world == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		
		// Formula: c = cd[ca  +  cl * max(0, <n, l>)]
		//Color texcolor = texture.getColor(hit.texcoord.u, hit.texcoord.v);
		Color texcolor = texture.getColor(hit.texcoord);
		Color c = texcolor.mul(world.ambientLight);
		final Normal3 n = hit.normal;
		final Point3 p = hit.ray.at(hit.t);
		final Light[] lights = world.getLights();
		for (Light light : lights) {
			if (light.illuminates(p, world)){
				final Vector3 l = light.directionFrom(p);
				final double f = Math.max(0, n.dot(l));
				final Color temp = texcolor.mul(light.color.mul(f));
				c = c.add(temp);
			}
		}
		return c;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((texture == null) ? 0 : texture.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LambertMaterial other = (LambertMaterial) obj;
		if (texture == null) {
			if (other.texture != null)
				return false;
		} else if (!texture.equals(other.texture))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[texture = " + texture + "]";
	}
}
