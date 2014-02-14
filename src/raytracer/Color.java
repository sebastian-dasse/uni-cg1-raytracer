package raytracer;

import static raytracer.math.MathUtil.isValid;

import java.awt.image.ColorModel;

/**
 * This immutable class represents a color in RGB color space.
 * <p>
 * A <code>Color</code> can be added to, subtracted from and multiplied with another one. Also a <code>Color</code> 
 * can be multiplied with a scalar. Color values should be in a range from 0 to 1.
 * <p>
 * There are also utility methods for normalizing a color and for creating a data element from a color for the 
 * <code>Raster</code> used in the <code>BufferedImage</code>.
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
	 * Constructs a new <code>Color</code> with the specified RGB components. Color values should be in a range from 0 
	 * to 1.
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
	 * Constructs a new <code>Color</code> with the RGB components specified in a double array.
	 * 
	 * @param rgb	The RGB components specified in a double array. The first element addresses the R component, the 
	 * 		element the G component and the third element the B component. Must not be <code>null</code>.
	 */
	public Color(final double[] rgb) {
		if (rgb == null || rgb.length < 3) {
			throw new IllegalArgumentException("Parameter 'rgb' must not be null and the array must have length 3.");
		}
		this.r = rgb[0];
		this.g = rgb[1];
		this.b = rgb[2];
	}
	
	/**
	 * Adds the specified <code>Color</code> c to this <code>Color</code>.
	 * 
	 * @param c	The <code>Color</code> to be added. Must not be <code>null</code>.
	 * @return	The resulting <code>Color</code>. Never contains negative components.
	 */
	public Color add(final Color c) {
		if (c == null) {
			throw new IllegalArgumentException("The parameter 'c' must not be null.");
		}
		return new Color(r + c.r, 
						 g + c.g, 
						 b + c.b);
	}
	
	/**
	 * Multiplies this <code>Color</code> with the specified <code>Color</code> c.
	 * 
	 * @param c	The <code>Color</code> to be multiplied with. Must not be <code>null</code>.
	 * @return	The resulting <code>Color</code>. Never contains negative components.
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
	 * Multiplies this <code>Color</code> with the specified scalar v.
	 * 
	 * @param v	The scalar to be multiplied with. Must be a positive double value other than Infinity or NaN.
	 * @return	The resulting <code>Color</code>. Never contains negative components.
	 */
	public Color mul(final double v) {
		if (v < 0 || !isValid(v)) {
			throw new IllegalArgumentException("The paramameter 'v' must be a positive double value other than Infinity or NaN.");
		}
		return new Color(r * v, 
						 g * v, 
						 b * v);
	}
	
	/**
	 * Returns a data element array representation of this color in the specified <code>ColorModel</code>.
	 * 
	 * @param colorModel	The specified <code>ColorModel</code>.
	 * @return				An Object which is a primitive data array representation of a color.
	 */
	public Object createDataElements(final ColorModel colorModel) {
		return colorModel.getDataElements(new float[] {
					createNormalizedColorComponent(r),
					createNormalizedColorComponent(g),
					createNormalizedColorComponent(b)
				}, 0, null);
	}
	
	/**
	 * Converts a color component from a specified double value to a normalized float value between 0 and 1 (including).
	 * 
	 * @param colorComponent	The double value to be normalized.
	 * @return					The normalized float value between 0 and 1 (including).
	 */
	public static float createNormalizedColorComponent(double colorComponent) {
		return (colorComponent > 1) ?  1 : (float) colorComponent;
	}
	
	public static int toInt(double colorComponent) {
		return (int) Math.round(colorComponent * 255);
	}
	

	/**
	 * Returns the normalized version of this color with values between 0 and 1.
	 * 
	 * @return	The normalized color.
	 */
	public Color normalized() {
		return new Color(createNormalizedColorComponent(r), 
						 createNormalizedColorComponent(g), 
						 createNormalizedColorComponent(b));
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
