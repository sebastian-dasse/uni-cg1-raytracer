package raytracer.texture;

import raytracer.Color;

/**
 * This immutable class represents a texture showing a single color.
 * 
 * @author Maxim Novichkov
 *
 */
public class SingleColorTexture implements Texture{
	/**
	 * The color of this texture.
	 */
	private final Color color;
	
	/**
	 * Constructs a new <code>SingleColorTexture</code> with the specified color.
	 * 
	 * @param color	The color of the texture.
	 */
	public SingleColorTexture(final Color color){
		if (color == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.color = color;
	}
	
	@Override
	public Color getColor(final double u, final double v) {
		return color;
	}
	
	@Override
	public Color getColor(TexCoord2 texcoord) {
		return color;
	}

}
