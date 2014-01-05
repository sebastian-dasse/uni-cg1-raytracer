package raytracer.material;

import raytracer.Color;
import raytracer.Tracer;
import raytracer.World;
import raytracer.geometry.Hit;

/**
 * This immutable class implements the color of a material with a simple surface.
 * 
 * @author Maxim Novichkov
 */
public class SingleColorMaterial extends Material{
	/**
	 * The surface color of this material.
	 */
	private final Color color;

	/**
	 * Constructs a new <code>SingleColorMaterial</code> object with the specified surface color.
	 * 
	 * @param color The surface color. Must not be <code>null</code>.
	 */
	public SingleColorMaterial (final Color color){
		if (color == null) {
			throw new IllegalArgumentException("The parameter 'color' must not be null.");
		}
		this.color = color;
	}

	@Override
	public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
		return color;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
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
		final SingleColorMaterial other = (SingleColorMaterial) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[color = " + color + "]";
	}
}
