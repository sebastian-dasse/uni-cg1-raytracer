package raytracer;

import java.util.Collection;
import java.util.LinkedList;
import raytracer.geometry.Geometry;
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
	private Collection <Geometry> elements;
	
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
	 * @param inputRay
	 * @return
	 */
	public Hit hit (final Ray inputRay) {
		
		return null;
	}
}
