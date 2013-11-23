package raytracer;

import java.util.Collection;
import java.util.LinkedList;

import raytracer.geometry.Geometry;
import raytracer.geometry.Hit;
import raytracer.geometry.Sphere;

/**
 * @author 
 *
 */
public class World {
	/**
	 * 
	 */
	public final Color backgroundColor; 
	private LinkedList <Geometry> elements;
	
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
	public void addElement(Geometry g) {
		elements.add(g);
	}
	
	/**
	 * @param ray
	 * @return
	 */
	public Hit hit (final Ray ray) {
		Hit minHit =  elements.pop().hit(ray);
		for (Geometry element : elements) {
			if (element.hit(ray).t < minHit.t) {
				minHit = element.hit(ray);
			}
		}
		return minHit;
	}
}
