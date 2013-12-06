package raytracer.geometry;

import raytracer.Ray;
import raytracer.material.Material;

/**
 * This abstract base class represents a geometric object in three-dimensional space. 
 * <p>
 * It has a color and a method <code>hit(Ray ray)</code>, which tests a <code>Ray</code> whether or not it hits the 
 * <code>Geometry</code>.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public abstract class Geometry {
	/**
	 * The material of the <code>Geometry</code>.
	 */
	public final Material material;
	
	/**
	 * Constructs a new <code>Geometry</code> with the specified color.
	 * 
	 * @param material The material of the <code>Geometry</code>. Must not be <code>null</code>.
	 */
	public Geometry(final Material material) {
		if (material == null) {
			throw new IllegalArgumentException("The parameter 'material' must not be null.");
		}
		this.material = material;
	}
	
	/**
	 * Returns a <code>Hit</code> object for the hit of the specified <code>Ray</code> with this <code>Geometry</code>. 
	 * If the ray hits the <code>Geometry</code> in more than one point, the <code>Hit</code> with the smallest 
	 * positive t will be returned (i.e. the t for the point that is closest to the origin of the specified ray). For 
	 * no hit <code>null</code> is returned.
	 * 
	 * @param ray	The <code>Ray</code> for which the hit with this <code>Geometry</code> shall be calculated. Must 
	 * 				not be <code>null</code>.
	 * @return		The <code>Hit</code> or <code>null</code>.
	 */
	public abstract Hit hit(final Ray ray);
	
	@Override
	public String toString() {
		return getClass().getSimpleName() + "[material = " + material;
	}
}
