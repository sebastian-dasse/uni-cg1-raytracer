package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

/**
 * This abstract base class represents the material of a <code>Geometry</code>. More specifically this means a color 
 * depending on the light sources in the scene.
 *  
 *  @author Maxim Novichkov;
 */
public abstract class Material {
	
	/**
	 * @param hit   The <code>Hit</code> object. Must not be <code>null</code>.
	 * @param world The type of a light source in world.
	 * @return      The (surface) color of the geometry.
	 */
	abstract public Color colorFor(final Hit hit, final World world);
}
