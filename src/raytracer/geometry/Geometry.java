package raytracer.geometry;

import raytracer.Color;

import raytracer.Ray;

/**
 * This abstract base class represents a geometric object in three-dimensional space. 
 * <p>
 * It has a color and a method <code>hit</code>, which tests a <code>Ray</code> whether or not it hits the 
 * <code>Geometry</code>.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public abstract class Geometry {
	/**
	 * The color of the <code>Geometry</code>.
	 */
	public final Color color;
	
	/**
	 * Constructs a new <code>Geometry</code> with the specified color.
	 * 
	 * @param color	The color of the <code>Geometry</code>. Must not be <code>null</code>.
	 */
	public Geometry(final Color color) {
		if (color == null) {
			throw new IllegalArgumentException("The parameter 'color' must not be null.");
		}
		this.color = color;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Geometry other = (Geometry) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[color = " + color;
	}
}
