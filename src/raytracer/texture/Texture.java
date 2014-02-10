package raytracer.texture;

import raytracer.Color;

/**
 * This interface represents a texture for a material. It declares a method that returns a <code>Color</code> for 
 * any given texture coordinates.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public interface Texture {
	
	/**
	 * Calculates the color for the specified texture coordinates u and v.
	 * 
	 * @param u	The u coordinate of the texture. Must be in the range [0, 1] to avoid errors.
	 * @param v	The v coordinate of the texture. Must be in the range [0, 1] to avoid errors.
	 * @return	The <code>Color</code>.
	 */
	public Color getColor(final double u, final double v);

	/**
	 * Calculates the color for the specified <code>TexCoord2</code>.
	 * 
	 * @param texcoord	The texture coordinates as <code>TexCoord2</code>.
	 * @return			The <code>Color</code>.
	 */
	public Color getColor(final TexCoord2 texcoord);
}
