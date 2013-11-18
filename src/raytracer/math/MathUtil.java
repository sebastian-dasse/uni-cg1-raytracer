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
}
