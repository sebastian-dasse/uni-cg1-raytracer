package raytracer.math;

import raytracer.Ray;

/**
 * TODO comment everything!
 * 
 * @author Maxim Novichkov
 *
 */
public class Transform {	
	/**
	 * The transformation matrix of this transformation.
	 */
	public final Mat4x4 m;
	/**
	 * The inverse transformation matrix of this transformation.
	 */
	public final Mat4x4 i;
	
	/**
	 * TODO comment
	 */
	public Transform(){
		m = new Mat4x4(1, 0, 0, 0, 
					   0, 1, 0, 0, 
					   0, 0, 1, 0, 
					   0, 0, 0, 1);
		i = new Mat4x4(1, 0, 0, 0, 
					   0, 1, 0, 0, 
					   0, 0, 1, 0, 
					   0, 0, 0, 1);
	}
	
	/**
	 * TODO comment
	 * 
	 * @param m	The transformation matrix of the transformation.
	 * @param i	The inverse transformation matrix of the transformation.
	 */
	private Transform(final Mat4x4 m, final Mat4x4 i){
		this.m = m;
		this.i = i;
	}
	

	public Transform translate(final Point3 point) {
		return translate(point.x, point.y, point.z);
	}
	
	/**
	 * Appends a translation and returns a new <code>Transform</code> object.
	 * 
	 * @param x	The x value for the translation.
	 * @param y The y value for the translation.
	 * @param z The z value for the translation.
	 * @return	A new <code>Transform</code> object with the appended translation.
	 */
	public Transform translate(final double x, final double y, final double z) {
		final Mat4x4 tm = new Mat4x4(1, 0, 0, x, 
						             0, 1, 0, y, 
						             0, 0, 1, z, 
						             0, 0, 0, 1);
		final Mat4x4 ti = new Mat4x4(1, 0, 0, -x, 
								     0, 1, 0, -y, 
								     0, 0, 1, -z, 
								     0, 0, 0,  1);
		return new Transform(m.mul(tm), ti.mul(i));

	}
	
	/**
	 * Appends a scale transformation and returns a new <code>Transform</code> object.
	 * 
	 * @param x	The scale factor for the x axis.
	 * @param y The scale factor for the y axis.
	 * @param z	The scale factor for the z axis.
	 * @return	A new <code>Transform</code> object with the appended scale transformation.
	 */
	public Transform scale(final double x, final double y, final double z) {
		if (x == 0 || y == 0 || z == 0) {
			throw new IllegalArgumentException("The parameters must not be 0.");
		}
		final Mat4x4 tm = new Mat4x4(x, 0, 0, 0, 
									 0, y, 0, 0, 
									 0, 0, z, 0, 
									 0, 0, 0, 1);
		final Mat4x4 ti = new Mat4x4(1/x, 0, 0, 0, 
									 0, 1/y, 0, 0, 
									 0, 0, 1/z, 0, 
									 0, 0, 0, 1);
		return new Transform(m.mul(tm), ti.mul(i));
	}
	
	/**
	 * Appends a rotation around the x axis to the transformation and returns a new <code>Transform</code> object.
	 * 
	 * @param angle	The rotation angle in radians.
	 * @return		A new <code>Transform</code> object with the appended rotation around the x axis.
	 */
	public Transform rotateX(final double angle) {
		final Mat4x4 tm = new Mat4x4( 1, 0,                0,              0,
									  0, Math.cos(angle),-Math.sin(angle), 0,
									  0, Math.sin(angle), Math.cos(angle), 0,
									  0, 0,                0,              1);
		final Mat4x4 ti = new Mat4x4( 1, 0,                0,              0,
									  0, Math.cos(angle), Math.sin(angle), 0,
									  0,-Math.sin(angle), Math.cos(angle), 0,
									  0, 0,                0,              1);
		return new Transform(m.mul(tm), ti.mul(i));
	  }
	
	/**
	 * Appends a rotation around the y axis to the transformation and returns a new <code>Transform</code> object.
	 * 
	 * @param angle	The rotation angle in radians.
	 * @return		A new <code>Transform</code> object with the appended rotation around the y axis.
	 */
	public Transform rotateY(final double angle) {
		final Mat4x4 tm = new Mat4x4( Math.cos(angle) , 0,-Math.sin(angle), 0,
									  0, 				1, 0,               0,
									  Math.sin(angle),  0, Math.cos(angle), 0,
									  0,                0, 0,               1);
		final Mat4x4 ti = new Mat4x4( Math.cos(angle) , 0,Math.sin(angle),  0,
									  0, 				1, 0,               0,
									-Math.sin(angle),   0, Math.cos(angle), 0,
									  0,                0, 0,               1);
		return new Transform(m.mul(tm), ti.mul(i));
	  }
	
	/**
	 * Appends a rotation around the z axis to the transformation and returns a new <code>Transform</code> object.
	 * 
	 * @param angle	The rotation angle in radians.
	 * @return		A new <code>Transform</code> object with the appended rotation around the z axis.
	 */
	public Transform rotateZ(final double angle) {
		final Mat4x4 tm = new Mat4x4( Math.cos(angle),-Math.sin(angle), 0, 0,
									  Math.sin(angle), Math.cos(angle), 0, 0,
									  0, 				  0,              1, 0,
									  0,                0,              0, 1);
		final Mat4x4 ti = new Mat4x4( Math.cos(angle), Math.sin(angle), 0, 0,
									 -Math.sin(angle), Math.cos(angle), 0, 0,
									  0, 				  0,              1, 0,
									  0,                0,              0, 1);
		return new Transform(m.mul(tm), ti.mul(i));
	  }
	
	/**
	 * Transforms the specified <code>Ray</code> by multiplying its origin and direction with the inverse transformation matrix.
	 * 
	 * @param ray	The <code>Ray</code> to be transformed. Must not be <code>null</code>.
	 * @return		The transformed <code>Ray</code>.
	 */
	public Ray mul(final Ray ray) {
		if (ray == null) {
			throw new IllegalArgumentException("The parameter 'ray' must not be null.");
		}
		return new Ray(i.mul(ray.o), i.mul(ray.d));
	  }
	
	/**
	 * Transforms the specified <code>Normal3</code> by multiplying it with the transposed matrix with.
	 * 
	 * @param normal	The <code>Normal3</code> to be transformed. Must not be <code>null</code>.
	 * @return			The transformed <code>Normal3</code>.
	 */
	public Normal3 mul(final Normal3 normal) {
		if (normal == null) {
			throw new IllegalArgumentException("The parameter 'normal' must not be null.");
		}
		return (i.transposed().mul(normal.asVector())).normalized().asNormal();
	}
}
