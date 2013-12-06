package raytracer;

import java.util.LinkedList;

import raytracer.geometry.Geometry;
import raytracer.geometry.Hit;
import raytracer.light.Light;

/**
 * This class contains all <code>Geometry</code> objects of a scene. Those objects are stored in a list and can be 
 * added and removed. The world has a specific background color and ambient light color which must be set in the 
 * constructor. There is also a list of all lights of the scene. Lights can be added and removed to the list. Finally 
 * it is possible to access this list.
 * 
 * @author Simon Lischka
 * @author Sebastian Dass&eacute;
 *
 */
public class World {
	/**
	 * The background color of this world.
	 */
	public final Color backgroundColor; 
	/**
	 * The color of the ambient light of this world.
	 */
	public final Color ambientLight;
	/**
	 * The list of all <code>Geometry</code> objects in this world.
	 */
	private LinkedList<Geometry> elements;
	/**
	 * The list of all <code>Light</code> objects in this world.
	 */
	private LinkedList<Light> lights;
	
	/**
	 * Creates a new <code>World</code> with the specified background color and ambient light color.
	 * 
	 * @param backgroundColor	The background color of the world.
	 * @param ambientLight		The color of the ambient light of the world.
	 */
	public World(final Color backgroundColor, final Color ambientLight) {
		if (backgroundColor == null || ambientLight == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.backgroundColor = backgroundColor;
		this.ambientLight = ambientLight;
		elements = new LinkedList<Geometry>();
		lights = new LinkedList<Light>();
	}
	
	/**
	 * Adds a <code>Geometry</code> object to this world.
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
	 * Removes a <code>Light</code> object from this world.
	 * 
	 * @param l	The <code>Light</code> to be removed.
	 */
	public void removeElement(final Light l) {
		elements.remove(l);
	}
	
	/**
	 * Adds a <code>Light</code> object to this world.
	 * 
	 * @param l	The <code>Light</code> to be added to this world.
	 */
	public void addLight(final Light l) {
		lights.add(l);
	}
	
	/**
	 * Adds an array of <code>Light</code> objects to this world.
	 * 
	 * @param ll	The <code>Light</code> array to be added to this world.
	 */
	public void addLights(final Light[] ll) {
		for (Light l : ll) {
			lights.add(l);
		}
	}
	
	/**
	 * Removes a <code>Light</code> object from this world.
	 * 
	 * @param l	The <code>Light</code> to be removed.
	 */
	public void removeLight(final Light l) {
		lights.remove(l);
	}
	
	/**
	 * @return	An array containing all the <code>Light</code>s in this world.
	 */
	public Light[] getLights() {
		return lights.toArray(new Light[lights.size()]);
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
