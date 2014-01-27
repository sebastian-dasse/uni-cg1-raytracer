package raytracer.texture;

/**
 * This immutable class represents the coordinates of a texture.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class TextureCoord {
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
	public TextureCoord(double u, double v) {
		this.u = u;
		this.v = v;
	}
}
