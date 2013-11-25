package raytracer;

import java.util.LinkedList;

import raytracer.geometry.Geometry;
import raytracer.geometry.Hit;

/**
 * This class contains all <code>Geometry</code> objects of a scene. Those objects are stored in a list and can be 
 * added and removed. The world has a specific background color which must be set in the constructor.
 * 
 * @author Simon Lischka
 *
 */
public class World {
	/**
	 * The background color of this world.
	 */
	public final Color backgroundColor; 
	/**
	 * The list of all <code>Geometry</code> objects in this world.
	 */
	private LinkedList<Geometry> elements;
	
	/**
	 * Creates a new <code>World</code> with the specified background color.
	 * 
	 * @param backgroundColor	The background color of this world.
	 */
	public World(final Color backgroundColor) {
		if (backgroundColor == null) {
			throw new IllegalArgumentException("The parameter 'backgroundColor' must not be null.");
		}
		this.backgroundColor = backgroundColor;
		elements = new LinkedList<Geometry>();
	}
	
	/**
	 * Adds a <code>Geometry</code> obejct to this world.
	 * 
	 * @param g	The <code>Geometry</code> to be added to this world.
	 */
	public void addElement(final Geometry g) {
		elements.add(g);
	}
	
	/**
	 * Adds an array of <code>Geometry</code> objects to this world.
	 * 
	 * @param gg	The <code>Geometry</code> array to be added to this world.
	 */
	public void addElements(final Geometry[] gg) {
		for (Geometry g : gg) {
			elements.add(g);
		}
	}
	
	/**
	 * Removes a <code>Geometry</code> object from this world.
	 * 
	 * @param g	The <code>Geometry</code> to be removed.
	 */
	public void removeElement(final Geometry g) {
		elements.remove(g);
	}
	
	/**
	 * Returns a <code>Hit</code> object for the hit of the specified <code>Ray</code> with this <code>World</code>.
	 * If more than one object is hit, the <code>Hit</code> with the the smallest positive t (i.e. with object that is 
	 * closest to the camera) will be returned. For no hit <code>null</code> is returned.
	 * 
	 * @param ray	The <code>Ray</code> for which the hit with this <code>World</code> shall be calculated. Must 
	 * 				not be <code>null</code>.
	 * @return		The <code>Hit</code> or <code>null</code>.
	 */
	public Hit hit(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("The parameter 'ray' must not be null.");
		}
		Hit minHit = null;
		for (final Geometry element : elements) {
			if (element.hit(ray) == null) {
				continue;
			}
			if (minHit == null || element.hit(ray).t < minHit.t) {
				minHit = element.hit(ray);
			}
		}
		return minHit;
	}
}
