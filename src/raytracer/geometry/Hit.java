package raytracer.geometry;

import raytracer.Ray;
import raytracer.math.Normal3;
import raytracer.texture.TexCoord2;

/**
 * This immutable class represents a hit of a <code>Ray</code> with a <code>Geometry</code>. Therefore it stores the 
 * value t, which parameterizes the hit point, the normal of the hit point, the <code>Ray</code> itself and the 
 * <code>Geometry</code> that was hit. The parameter t may never be negative, because a hit of the ray with an object 
 * lies always in the direction d of the ray.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class Hit /*implements Comparable<Hit>*/ {
	/**
	 * The parameter for this hit point. Never is smaller than 0.
	 */
	public final double t;
	/**
	 * The <code>Ray</code> that hit this <code>Geometry</code>.
	 */
	public final Ray ray;
	/**
	 * The <code>Geometry</code> that was hit.
	 */
	public final Geometry geo;
	/**
	 * The normal of this hit.
	 */
	public final Normal3 normal;
	/**
	 * The texture coordinates of this hit.
	 */
	public final TexCoord2 texcoord;
	
	/**
	 * Constructs a new <code>Hit</code> object with the specified parameters.
	 * 
	 * @param t			The parameter for the hit point. Must be a positive double value other than Infinity or NaN.
	 * @param ray		The <code>Ray</code> that hit the <code>Geometry</code>. Must not be <code>null</code>.
	 * @param geo		The <code>Geometry</code> that was hit. Must not be <code>null</code>.
	 * @param normal	The normal of the hit point. Must not be <code>null</code>.
	 * @param texcoord	The texture coordinates of the hit point. Most not be <code>null</code>.
	 */
	public Hit(final double t, final Ray ray, final Geometry geo, final Normal3 normal, final TexCoord2 texcoord) {
		if (ray == null || geo == null || normal == null || texcoord == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.t = t;
		this.ray = ray;
		this.geo = geo;
		this.normal = normal;
		this.texcoord = texcoord;
	}

	

	@Override
	public String toString() {
		return getClass().getSimpleName() + "[\tt = " + t + ",\n" 
										  + "\tray = " + ray + ",\n" 
										  + "\tgeo = " + geo + ",\n" 
										  + "\tnormal = " + normal + ",\n"  
										  + "\ttexcoord =" + texcoord + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((geo == null) ? 0 : geo.hashCode());
		result = prime * result + ((normal == null) ? 0 : normal.hashCode());
		result = prime * result + ((ray == null) ? 0 : ray.hashCode());
		long temp;
		temp = Double.doubleToLongBits(t);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((texcoord == null) ? 0 : texcoord.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hit other = (Hit) obj;
		if (geo == null) {
			if (other.geo != null)
				return false;
		} else if (!geo.equals(other.geo))
			return false;
		if (normal == null) {
			if (other.normal != null)
				return false;
		} else if (!normal.equals(other.normal))
			return false;
		if (ray == null) {
			if (other.ray != null)
				return false;
		} else if (!ray.equals(other.ray))
			return false;
		if (Double.doubleToLongBits(t) != Double.doubleToLongBits(other.t))
			return false;
		if (texcoord == null) {
			if (other.texcoord != null)
				return false;
		} else if (!texcoord.equals(other.texcoord))
			return false;
		return true;
	}

//	@Override
//	public int compareTo(Hit o) {
//		double diff = t - o.t;
//		return Math.abs(diff) < Constants.EPSILON ? 0 : (int) Math.signum(diff);
//	}
}
