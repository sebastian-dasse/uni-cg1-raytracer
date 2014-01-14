package raytracer.geometry;

import java.util.LinkedList;

import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class represents an axis aligned box in three-dimensional space. It is defined through its <em>low 
 * bottom far point</em> (lbf) and its <em>right upper near point</em> (run).
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class AxisAlignedBox extends Geometry {
	
	/**
	 * The low bottom far point of this <code>AxisAlignedBox</code>.
	 * Is part of the left, the back and the bottom plane.
	 */
	public final Point3 lbf;
	/**
	 * The right upper near point of this <code>AxisAlignedBox</code>.
	 * Is part of the top, the front and the right plane.
	 */
	public final Point3 run;
	/**
	 * The normal of the left face of the box.
	 */
	private static final Normal3 LEFT   = new Normal3(-1,  0,  0);
	/**
	 * The normal of the right face of the box.
	 */
	private static final Normal3 RIGHT  = new Normal3( 1,  0,  0);
	/**
	 * The normal of the top face of the box.
	 */
	private static final Normal3 TOP    = new Normal3( 0,  1,  0);
	/**
	 * The normal of the bottom face of the box.
	 */
	private static final Normal3 BOTTOM = new Normal3( 0, -1,  0);
	/**
	 * The normal of the front face of the box.
	 */
	private static final Normal3 FRONT  = new Normal3( 0,  0,  1);
	/**
	 * The normal of the back face of the box.
	 */
	private static final Normal3 BACK   = new Normal3( 0,  0, -1);
	
	/**
	 * Constructs a new <code>AxisAlignedBox</code> with the specified parameters.
	 * 
	 * @param lbf		The low bottom far point of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 * @param run		The right upper near point of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 * @param material	The material of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 */
	public AxisAlignedBox(final Point3 lbf, final Point3 run, final Material material) {
		super(material);
		if (lbf == null || run == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.lbf = lbf;
		this.run = run;
	}
	
	@Override
	public Hit hit(final Ray ray) {
		return new Hit(hitMax.t, ray, this, hitMax.normal);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((lbf == null) ? 0 : lbf.hashCode());
		result = prime * result + ((run == null) ? 0 : run.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AxisAlignedBox other = (AxisAlignedBox) obj;
		if (lbf == null) {
			if (other.lbf != null)
				return false;
		} else if (!lbf.equals(other.lbf))
			return false;
		if (run == null) {
			if (other.run != null)
				return false;
		} else if (!run.equals(other.run))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ",\n\tlbf = " + lbf + ",\n" 
								+ "\trun = " + run + "]";
	}
}
