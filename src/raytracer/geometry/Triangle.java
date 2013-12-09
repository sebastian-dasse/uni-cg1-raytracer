package raytracer.geometry;

import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Mat3x3;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class represents a flat triangle in three-dimensional space. It is defined through its three corner 
 * points (vertices).
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
	 * The normal of point a in this <code>Triangle</code>.
	 */
	public final Normal3 na;
	/**
	 * The normal of point b in this <code>Triangle</code>.
	 */
	public final Normal3 nb;
	/**
	 * The mormal of point c in this <code>Triangle</code>.
	 */
	public final Normal3 nc;
	
	/**
	 * Constructs a new <code>Triangle</code> with the specified parameters.
	 * 
	 * @param a			Vertex a of the triangle. Must not be <code>null</code>.
	 * @param b			Vertex b of the triangle. Must not be <code>null</code>.
	 * @param c			Vertex c of the triangle. Must not be <code>null</code>.
	 * @param na		The Normal of point a.
	 * @param nb		The Normal of point b.
	 * @param nc		The Normal of point c.
	 * @param material	The material of the triangle. Must not be <code>null</code>.
	 */
	public Triangle(final Point3 a, final Point3 b, final Point3 c,
			final Normal3 na, final Normal3 nb, final Normal3 nc, final Material material) {
		super(material);
		if (a == null || b == null || c == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}	
			this.a = a;
			this.b = b;
			this.c = c;
			this.na = na;
			this.nb = nb;
			this.nc = nc;
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
		
		if (gamma < 0 || beta < 0 || beta + gamma > 1 || t < 0) {
			return null; // no hit
		}
		final double alpha = 1 - beta - gamma;
		final Normal3 normal = na.mul(alpha).add(nb.mul(beta)).add(nc.mul(gamma)).asVector().normalized().asNormal(); // normalized normal
		return new Hit(t, ray, this, normal);
	}

	@Override
	public String toString() {
		return super.toString() + ",\n\ta = " + a + ",\n" 
								+ "\tb = " + b + ",\n" 
								+ "\tc = " + c + ",\n"
								+ "\tna = " + na + ",\n"
								+ "\tnb = " + nb + ",\n"
								+ "\tnc = " + nc + "]";
	}
}
