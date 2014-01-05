package raytracer;

import java.awt.image.ColorModel;

public final class Util {
	/**
	 * Returns a data element array representation of a pixel in the specified <code>ColorModel</code> from the specified 
	 * <code>raytracer.Color</code>.
	 * 
	 * @param color			The specified <code>raytracer.Color</code>.
	 * @param colorModel	The specified <code>ColorModel</code>.
	 * @return				An Object which is a primitive data array representation of a pixel.
	 */
	public static Object dataElementsFromColor(final Color color, final ColorModel colorModel) {
		return colorModel.getDataElements(new float[] {
					normalizeColorComponent(color.r),
					normalizeColorComponent(color.g),
					normalizeColorComponent(color.b)
				}, 0, null);
	}
	
	/**
	 * Converts a color component from a specified double value to a normalized float value between 0 and 1 (including).
	 * 
	 * @param colorComponent	The double value to be normalized.
	 * @return					The normalized float value between 0 and 1 (including).
	 */
	 public static float normalizeColorComponent(double colorComponent) {
		return (colorComponent > 1) ?  1 : (float) colorComponent;
	}

}
