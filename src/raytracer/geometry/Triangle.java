package raytracer.geometry;

import raytracer.Constants;
import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Mat3x3;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.texture.TexCoord2;

/**
 * This immutable class represents a flat triangle in three-dimensional space. It is defined through its three corner 
 * points (vertices). Each of those vertices has its own normal and texture coordinates.
 * 
 * @author Maxim Novichkov
 * @author Sebastian Dass&eacute;
 */
public class Triangle extends Geometry {
	/**
	 * Vertex a of this triangle.
	 */
	public final Point3 a;
	/**
	 * Vertex b of this triangle.
	 */
	public final Point3 b;
	/**
	 * Vertex c of this triangle.
	 */
	public final Point3 c;
	/**
	 * The normal at vertex a.
	 */
	public final Normal3 na;
	/**
	 * The normal at vertex b.
	 */
	public final Normal3 nb;
	/**
	 * The normal at vertex c.
	 */
	public final Normal3 nc;
	/**
	 * The texture coordinates at vertex a.
	 */
	public final TexCoord2 ta;
	/**
	 * The texture coordinates at vertex b.
	 */
	public final TexCoord2 tb;
	/**
	 * The texture coordinates at vertex c.
	 */
	public final TexCoord2 tc;
	
	/**
	 * Constructs a new <code>Triangle</code> with the specified parameters.
	 * 
	 * @param a			Vertex a of the triangle. Must not be <code>null</code>.
	 * @param b			Vertex b of the triangle. Must not be <code>null</code>.
	 * @param c			Vertex c of the triangle. Must not be <code>null</code>.
	 * @param na		The normal at vertex a. Must not be <code>null</code>.
	 * @param nb		The normal at vertex b. Must not be <code>null</code>.
	 * @param nc		The normal at vertex c. Must not be <code>null</code>.
	 * @param material	The material of the triangle. Must not be <code>null</code>.
	 * @param ta		The texture coordinates at vertex a. Must not be <code>null</code>.
	 * @param tb		The texture coordinates at vertex b. Must not be <code>null</code>.
	 * @param tc		The texture coordinates at vertex c. Must not be <code>null</code>.
	 */
	public Triangle(final Point3 a, final Point3 b, final Point3 c, 
			final Normal3 na, final Normal3 nb, final Normal3 nc, final Material material, 
			final TexCoord2 ta, final TexCoord2 tb, final TexCoord2 tc) {
		super(material);
		if (a == null || b == null || c == null || na == null || nb == null || nc == null 
				|| ta == null || tb == null || tc == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}	
			this.a = a;
			this.b = b;
			this.c = c;
			this.na = na;
			this.nb = nb;
			this.nc = nc;
			this.ta = ta;
			this.tb = tb;
			this.tc = tc;
	}

	@Override
	public Hit hit(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("The parameter 'ray' must not be null.");
		}
		
		/*
		 * Formulas:
		 * 		Ax = dvector = a - o
		 * 		A_1 := matrix A with column 1 replaced by dvector
		 * 		beta  = detA_1 / detA
		 * 		gamma = detA_2 / detA
		 * 		t     = detA_3 / detA
		 * 		0 <= beta + gamma <= 1
		 */
		final Mat3x3 matrix = new Mat3x3(a.x - b.x, a.x - c.x, ray.d.x,
								   		 a.y - b.y, a.y - c.y, ray.d.y,
								   		 a.z - b.z, a.z - c.z, ray.d.z);
		final Vector3 dvector = a.sub(ray.o);
		if (matrix.determinant == 0) {
			return null; // no hit
		}
		final double beta  = matrix.changeCol1(dvector).determinant / matrix.determinant;
		final double gamma = matrix.changeCol2(dvector).determinant / matrix.determinant;
		final double t     = matrix.changeCol3(dvector).determinant / matrix.determinant;
		
		if (gamma < 0 || beta < 0 || beta + gamma > 1 || t < Constants.EPSILON) {
			return null; // no hit
		}
		final double alpha = 1 - beta - gamma;
		final Normal3 normal = na.mul(alpha).add(nb.mul(beta)).add(nc.mul(gamma)).asVector().normalized().asNormal(); // normalized normal
		
		// Calculation of texture coordinates
		final double u = ta.u * alpha + tb.u * beta + tc.u * gamma;
		final double v = ta.v * alpha + tb.v * beta + tc.v * gamma;
		return new Hit(t, ray, this, normal, new TexCoord2(u, v));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((a == null) ? 0 : a.hashCode());
		result = prime * result + ((b == null) ? 0 : b.hashCode());
		result = prime * result + ((c == null) ? 0 : c.hashCode());
		result = prime * result + ((na == null) ? 0 : na.hashCode());
		result = prime * result + ((nb == null) ? 0 : nb.hashCode());
		result = prime * result + ((nc == null) ? 0 : nc.hashCode());
		result = prime * result + ((ta == null) ? 0 : ta.hashCode());
		result = prime * result + ((tb == null) ? 0 : tb.hashCode());
		result = prime * result + ((tc == null) ? 0 : tc.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Triangle other = (Triangle) obj;
		if (a == null) {
			if (other.a != null)
				return false;
		} else if (!a.equals(other.a))
			return false;
		if (b == null) {
			if (other.b != null)
				return false;
		} else if (!b.equals(other.b))
			return false;
		if (c == null) {
			if (other.c != null)
				return false;
		} else if (!c.equals(other.c))
			return false;
		if (na == null) {
			if (other.na != null)
				return false;
		} else if (!na.equals(other.na))
			return false;
		if (nb == null) {
			if (other.nb != null)
				return false;
		} else if (!nb.equals(other.nb))
			return false;
		if (nc == null) {
			if (other.nc != null)
				return false;
		} else if (!nc.equals(other.nc))
			return false;
		if (ta == null) {
			if (other.ta != null)
				return false;
		} else if (!ta.equals(other.ta))
			return false;
		if (tb == null) {
			if (other.tb != null)
				return false;
		} else if (!tb.equals(other.tb))
			return false;
		if (tc == null) {
			if (other.tc != null)
				return false;
		} else if (!tc.equals(other.tc))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ",\n\ta = " + a + ",\n" 
								+ "\tb = " + b + ",\n" 
								+ "\tc = " + c + ",\n"
								+ "\tna = " + na + ",\n"
								+ "\tnb = " + nb + ",\n"
								+ "\tnc = " + nc + ",\n"
								+ "\tta = " + ta + ",\n"
								+ "\ttb = " + tb + ",\n"
								+ "\ttc = " + tc + "]";
	}
}
