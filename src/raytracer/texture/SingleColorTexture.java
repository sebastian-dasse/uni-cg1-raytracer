package raytracer.texture;

import raytracer.Color;
/**
 * This immutable class represents a color of the simple color texture. 
 * @author Maxim Novichkov
 *
 */
public class SingleColorTexture implements Texture{
	/**
	 * The color of this texture.
	 */
	private final Color color;
	/**
	 * Construct a texture with only one color.
	 * @param The specified color of this texture.
	 */
	public SingleColorTexture(final Color color){
		if (color == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.color = color;
	}
	
	@Override
	/**
	 * @ param The u coordinate of this texture.
	 * @ param The v coordinate of this texture.
	 */
	public Color getColor(final double u, final double v) {
		return color;
	}
	/**
	 * @ param Coordinate of this texture.
	 */
	public Color getColor(TexCoord2 texcoord) {
		return color;
	}

}
