package raytracer;

import static java.lang.Math.min;
import static java.lang.Math.max;
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
		return new Color(min(1, r + c.r), 
						 min(1, g + c.g), 
						 min(1, b + c.b));
	}
	
	/**
	 * @param c
	 * @return
	 */
	public Color sub(final Color c) {
		return new Color(max(0, r - c.r), 
						 max(0, g - c.g), 
						 max(0, b - c.b));
	}
	
	/**
	 * @param c
	 * @return
	 */
	public Color mul(final Color c) {
		return new Color(r * c.r, 
						 g * c.g, 
						 b * c.b);
	}
	
	/**
	 * @param v
	 * @return
	 */
	public Color mul(final double v) {
		return new Color(max(0, min(1, r * v)), 
						 max(0, min(1, g * v)), 
						 max(0, min(1, b * v)));
	}
	
	public String toString() {
		return getClass().getSimpleName() + "[r = " + r + ", g = " + g + ", b = " + b + "]";
	}
	
	//---- Test
	public static void main(String[] args) {
//		Color c01 = new Color(1, 1, -1);
//		Color c02 = new Color(1, 1, 2);
//		Color c03 = new Color(1, 1, Double.NaN);
//		Color c04 = new Color(1, 1, Double.POSITIVE_INFINITY);
		Color c0 = new Color(0, 0, 0);
		Color c1 = new Color(1, 1, 1);
		Color c2 = new Color(0.9, 0.9, 0.9);
		Color c3 = new Color(0.009, 0.009, 0.009);
		
		System.out.println(c2.add(c1));
		System.out.println(c2.add(c3));
		System.out.println();
		
		System.out.println(c2.sub(c1));
		System.out.println(c2.sub(c3));
		System.out.println();
		
		System.out.println(c0.mul(c0));
		System.out.println(c0.mul(c1));
		System.out.println(c1.mul(c1));
		System.out.println(c2.mul(c1));
		System.out.println(c2.mul(c3));
		System.out.println();
		
		System.out.println(c2.mul(0));
		System.out.println(c2.mul(1));
		System.out.println(c2.mul(0.9));
		System.out.println(c2.mul(1.1));
		System.out.println(c2.mul(10000));
		System.out.println(c2.mul(-10000));
		System.out.println();
	}
}
