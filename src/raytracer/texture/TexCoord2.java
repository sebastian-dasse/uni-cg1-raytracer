package raytracer.texture;

import static raytracer.math.MathUtil.isValid;

/**
 * This immutable class represents the coordinates of a texture.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class TexCoord2 {
	/**
	 * The u coordinate of this texture.
	 */
	public final double u;
	/**
	 * The v coordinate of this texture.
	 */
	public final double v;
	
	/**
	 * Constructs a new <code>TextureCoord</code> object.
	 * 
	 * @param u	The u coordinate of the texture.
	 * @param v	The v coordinate of the texture.
	 */
	public TexCoord2(final double u, final double v) {
		this.u = u;
		this.v = v;
	}
}
