package raytracer;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static raytracer.math.MathUtil.isValid;

/**
 * This immutable class represents a color in RGB color space.
 * <p>
 * A <code>Color</code> can be added to, subtracted from and multiplied with another one. Also a <code>Color</code> 
 * can be multiplied with a scalar.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class Color{
	/**
	 * The red component of the <code>Color</code>. Must be a positive double value other than Infinity or NaN.
	 */
	public final double r;
	/**
	 * The green component of the <code>Color</code>. Must be a positive double value other than Infinity or NaN.
	 */
	public final double g;
	/**
	 * The blue component of the <code>Color</code>. Must be a positive double value other than Infinity or NaN.
	 */
	public final double b;
	
	/**
	 * Constructs a new <code>Color</code> with the specified RGB components.
	 * 
	 * @param r	The red component. Must be a positive double value other than Infinity or NaN.
	 * @param g	The green component. Must be a positive double value other than Infinity or NaN.
	 * @param b	The blue component. Must be a positive double value other than Infinity or NaN.
	 */
	public Color(final double r, final double g, final double b) {
		if (r < 0 || !isValid(r) || g < 0 || !isValid(g) || b < 0 || !isValid(b)) {
			throw new IllegalArgumentException("Only positive double values other than Infinity or NaN are allowed.");
		}
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	/**
	 * Adds the specified <code>Color</code> c to this <code>Color</code>. Each of the resulting components will be 
	 * truncated to a maximum value of 1.
	 * 
	 * @param c	The <code>Color</code> to be added. Must not be <code>null</code>.
	 * @return	The resulting <code>Color</code>. Never contains components greater than 1.
	 */
	public Color add(final Color c) {
		if (c == null) {
			throw new IllegalArgumentException("The parameter 'c' must not be null.");
		}
		return new Color(min(1, r + c.r), 
						 min(1, g + c.g), 
						 min(1, b + c.b));
	}
	
	/**
	 * Subtracts the specified <code>Color</code> c from this <code>Color</code>. Each of the resulting components will 
	 * be truncated to a minimum value of 0.
	 * 
	 * @param c	The <code>Color</code> to be subtracted. Must not be <code>null</code>.
	 * @return	The resulting <code>Color</code>. Never contains components smaller than 0.
	 */
	public Color sub(final Color c) {
		if (c == null) {
			throw new IllegalArgumentException("The parameter 'c' must not be null.");
		}
		return new Color(max(0, r - c.r), 
						 max(0, g - c.g), 
						 max(0, b - c.b));
	}
	
	/**
	 * Multiplies this <code>Color</code> with the specified <code>Color</code> c.
	 * 
	 * @param c	The <code>Color</code> to be multiplied with. Must not be <code>null</code>.
	 * @return	The resulting <code>Color</code>.
	 */
	public Color mul(final Color c) {
		if (c == null) {
			throw new IllegalArgumentException("The parameter 'c' must not be null.");
		}
		return new Color(r * c.r, 
						 g * c.g, 
						 b * c.b);
	}
	
	/**
	 * Multiplies this <code>Color</code> with the specified scalar v. Each of the resulting components will be 
	 * truncated to a minimum value of 0 or a maximum value of 1, if necessary, so they will never leave this range.
	 * 
	 * @param v	The scalar to be multiplied with. Must be a double value other than +-Infinity or NaN.
	 * @return	The resulting <code>Color</code>. Never contains components smaller than 0 or greater than 1.
	 */
	public Color mul(final double v) {
		if (!isValid(v)) {
			throw new IllegalArgumentException("Only a double value other than +-Infinity or NaN is allowed.");
		}
		return new Color(max(0, min(1, r * v)), 
						 max(0, min(1, g * v)), 
						 max(0, min(1, b * v)));
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(b);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(g);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(r);
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
		final Color other = (Color) obj;
		if (Double.doubleToLongBits(b) != Double.doubleToLongBits(other.b))
			return false;
		if (Double.doubleToLongBits(g) != Double.doubleToLongBits(other.g))
			return false;
		if (Double.doubleToLongBits(r) != Double.doubleToLongBits(other.r))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[r = " + r + ", g = " + g + ", b = " + b + "]";
	}
}
