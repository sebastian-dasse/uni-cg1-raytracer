package raytracer.math;

/**
 * This utility class provides methods for convenient mathematical checks and operations.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public final class MathUtil {
	
	/**
	 * Checks if the specified number is a valid double other than NaN or +-Infinity. Returns <code>true</code> in this 
	 * case. 
	 * 
	 * @param d The double value to be checked for validity.
	 * @return	<code>true</code> if valid, otherwise <code>false</code>.
	 */
	public static boolean isValid(final double d) {
		return !Double.isNaN(d) && !Double.isInfinite(d);
	}
	
	// TODO never used
	/**
	 * Checks if the specified number is in the range from 0 to 1 including, i.e. 0 <= d <= 1.
	 * 
	 * @param d	The double value to be checked.
	 * @return	<code>true</code> if 0 <= d <= 1, otherwise <code>false</code>. 
	 */
//	public static boolean inRange0To1(final double d) {
//		return inRange(d, 0, 1);
//	}
	
	// TODO never used
	/**
	 * Checks if the specified number is in the specified range, i.e. lo <= d <= hi.
	 * 
	 * @param d		The double value to be checked.
	 * @param lo	The lower limit of the range.
	 * @param hi	The upper limit of the range.
	 * @return	<code>true</code> if 0 <= d <= 1, otherwise <code>false</code>. 
	 */
//	public static boolean inRange(final double d, final double lo, final double hi) {
//		return lo <= d && d <= hi;
//	}
}
