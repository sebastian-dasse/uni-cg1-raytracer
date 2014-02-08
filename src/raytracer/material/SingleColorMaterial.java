package raytracer.material;

import raytracer.Color;
import raytracer.Tracer;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.texture.Texture;

/**
 * This immutable class implements the color of a material with a simple surface.
 * 
 * @author Maxim Novichkov
 */
public class SingleColorMaterial extends Material{
	/**
	 * The surface texture of this material.
	 */
	private final Texture texture;

	/**
	 * Constructs a new <code>SingleColorMaterial</code> object with the specified surface texture.
	 * 
	 * @param texture	The surface texture. Must not be <code>null</code>.
	 */
	public SingleColorMaterial (final Texture texture){
		if (texture == null) {
			throw new IllegalArgumentException("The parameter 'texture' must not be null.");
		}
		this.texture = texture;
	}

	@Override
	public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
		return texture.getColor(hit.texcoord);
	}


	@Override
	public String toString() {
		return getClass().getSimpleName() + "[texture = " + texture + "]";
	}
}
