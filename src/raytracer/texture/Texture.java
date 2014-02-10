package raytracer.texture;

import raytracer.Color;
/**
 * This abstract class represents a specified color of the texture. 
 * @author Maxim Novichkov
 *
 */
public interface Texture {
	/**
	 * This method calculate the color of a pixel from specified separated texture coordinates.
	 * 
	 *  
	 * @ param The u coordinate of this texture.
	 * @ param The v coordinate of this texture.
	 */
	public Color getColor(final double u, final double v);
	/**
	 * This method calculate the color of a pixel from specified texture coordinate.
	 * @ param Coordinate of this texture.
	 */
	public Color getColor(final TexCoord2 texcoord);
}
