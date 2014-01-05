package raytracer.material;

import raytracer.Color;
import raytracer.Tracer;
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
	 * @param hit   The <code>Hit</code> object. Must not be <code>null</code>.
	 * @param world The world. Must not be <code>null</code>.
	 * @return      The color for a <code>Hit</code> object.
	 */
	abstract public Color colorFor(final Hit hit, final World world, final Tracer tracer);
}
