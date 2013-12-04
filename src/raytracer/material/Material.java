package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

/**
 *  This immutable class is a basis for implementing of the color's geometry in depending on light sources.
 *  @author Novichkov Maxim; 
 */
public abstract class Material {
	/**
	 * Returns the color of the geometry that was hit.
	 * 
	 * @param hit   The <code>Hit</code> object.
	 * @param world The type of a light source.
	 * @return      The color of the geometry.
	 */
	abstract public Color colorFor(final Hit hit, final World world);
}
