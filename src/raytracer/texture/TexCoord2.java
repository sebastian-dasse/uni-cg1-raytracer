package raytracer.texture;

import static raytracer.math.MathUtil.isValid;

/**
 * This immutable class represents the coordinates of a texture. Textures using this class will be repeated as tiles.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class TexCoord2 {
	/**
	 * The u coordinate of this texture. Always in the range [0, 1].
	 */
	public double u;
	/**
	 * The v coordinate of this texture. Always in the range [0, 1].
	 */
	public final double v;
	
	/**
	 * Constructs a new <code>TexCoord2</code> object. The coordinates u and v will automatically be normalized to 
	 * values in the range from 0 to 1 by modulo operation. This results in a tiled repetition of the texture.
	 * 
	 * @param u	The u coordinate of the texture.
	 * @param v	The v coordinate of the texture.
	 */
	public TexCoord2(final double u, final double v) {
		if (!(isValid(u) && isValid(v))) {
			throw new IllegalArgumentException("Only a double value other than +-Infinity or NaN is allowed.");
		}
		this.u = u < 0 ? u % 1 + 1 : u % 1;
		this.v = v < 0 ? v % 1 + 1 : v % 1;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(u);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(v);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		final TexCoord2 other = (TexCoord2) obj;
		if (Double.doubleToLongBits(u) != Double.doubleToLongBits(other.u))
			return false;
		if (Double.doubleToLongBits(v) != Double.doubleToLongBits(other.v))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[\tu = " + u + ",\n"  
										  + "\tv =" + v + "]";
	}
}
