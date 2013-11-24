package raytracer;

import java.util.LinkedList;

import raytracer.geometry.Geometry;
import raytracer.geometry.Hit;

/**
 * TODO DOK IT ALL!
 * 
 * @author 
 *
 */
public class World {
	/**
	 * 
	 */
	public final Color backgroundColor; 
	/**
	 * 
	 */
	private LinkedList<Geometry> elements;
	
	/**
	 * @param backgroundColor
	 */
	public World(final Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		elements = new LinkedList<Geometry>();
	}
	
	/**
	 * @param g
	 */
	public void addElement(final Geometry g) {
		elements.add(g);
	}
	
	/**
	 * @param gg
	 */
	public void addElements(final Geometry[] gg) {
		for (Geometry g : gg) {
			elements.add(g);
		}
	}
	
	/**
	 * @param g
	 */
	public void removeElement(final Geometry g) {
		elements.remove(g);
	}
	
	/**
	 * @param ray
	 * @return hit
	 */
	public Hit hit(final Ray ray) {
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
