package raytracer;

import raytracer.geometry.Hit;

/**
 * @author 
 *
 */
public class World {
	/**
	 * 
	 */
	public final Color backgroundColor; 
	
	/**
	 * @param backgroundColor
	 */
	public World(final Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}
	
	/**
	 * @param inputRay
	 * @return
	 */
	public Hit hit (final Ray inputRay) {
		return null;
	}
}
