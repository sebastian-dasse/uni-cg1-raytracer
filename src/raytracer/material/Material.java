package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

/**
 * This abstract base class represents the material of a <code>Geometry</code>. More specifically this means a color 
 * depending on the light sources in the scene.
 *  
 *  @author Maxim Novichkov
 */
public abstract class Material {
	
	/**
	 * Returns the <code>Color</code> for a <code>Hit</code> object.
	 * 
	 * @param hit   The <code>Hit</code> object. Must not be <code>null</code>.
	 * @param world The world. Must not be <code>null</code>.
	 * @return      The <code>Color</code> for a hit.
	 */
	abstract public Color colorFor(final Hit hit, final World world);
}
