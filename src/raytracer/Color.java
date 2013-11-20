package raytracer;

import static raytracer.math.MathUtil.inRange0To1;

/**
 * @author 
 *
 */
public class Color {
	/**
	 * 
	 */
	public final double r;
	/**
	 * 
	 */
	public final double g;
	/**
	 * 
	 */
	public final double b;
	
	/**
	 * @param r
	 * @param g
	 * @param b
	 */
	public Color(final double r, final double g, final double b) {
		if (!(inRange0To1(r) && inRange0To1(g) && inRange0To1(b))) {
			throw new IllegalArgumentException("Only double values between 0 and 1 (including) are allowed.");
		}
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	/**
	 * @param c
	 * @return
	 */
	public Color add(final Color c) {
		return null;
	}
	
	/**
	 * @param c
	 * @return
	 */
	public Color sub(final Color c) {
		return null;
	}
	
	/**
	 * @param c
	 * @return
	 */
	public Color mul(final Color c) {
		return null;
	}
	
	/**
	 * @param v
	 * @return
	 */
	public Color mul(final double v) {
		return null;
	}
}
