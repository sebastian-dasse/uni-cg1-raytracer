package raytracer.texture;

import raytracer.Color;
/**
<<<<<<< HEAD
 * This immutable class represents a texture showing a single color.
 * 
=======
 * This immutable class represents a color of the simple color texture. 
>>>>>>> 56f5eb6eb178cb2cdb725cca5ae2fa48b673ee02
 * @author Maxim Novichkov
 *
 */
public class SingleColorTexture implements Texture{
	/**
	 * The color of this texture.
	 */
	private final Color color;
	/**
<<<<<<< HEAD
	 * Constructs a new <code>SingleColorTexture</code> with the specified color.
	 * 
	 * @param color	The color of the texture.
=======
	 * Construct a texture with only one color.
	 * @param color The specified color of this texture.
>>>>>>> 56f5eb6eb178cb2cdb725cca5ae2fa48b673ee02
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
