package raytracer.math;

import raytracer.Ray;

/**
<<<<<<< HEAD
 * This immutable class represents a transformation and is meant to transform <code>Node</code>s. There are methods to 
 * append other transformations on the right side.
=======
 * This immutable class provides methods to transform the geometries in three dimensional space.
>>>>>>> efefa0e1476fd2c54e575c658b575f28703afe96
 * 
 * @author Maxim Novichkov
 * @author Sebastian Dass&eacute;
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
<<<<<<< HEAD
	 * Constructs a new <code>Transform</code> object. To actually perform a transformation, one of the transformation 
	 * methods must be appended to this transformation.
=======
	 * Construct a new <code>Transformation</code> object and initialize it with the unit matrix.
>>>>>>> efefa0e1476fd2c54e575c658b575f28703afe96
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
<<<<<<< HEAD
	 * This private method constructs a new <code>Transform</code> object as specified by the given matrices.
=======
	 * Construct a new <code>Transformation</code> object with transformation and inverse matrix.
>>>>>>> efefa0e1476fd2c54e575c658b575f28703afe96
	 * 
	 * @param m	The transformation matrix of the transformation.
	 * @param i	The inverse transformation matrix of the transformation.
	 */
	private Transform(final Mat4x4 m, final Mat4x4 i){
		this.m = m;
		this.i = i;
	}
	
	/**
	 * Appends a translation and returns a new <code>Transform</code> object.
	 * 
	 * @param point	The x, y and z values as <code>Point3</code> for the translation.
	 * @return		A new <code>Transform</code> object with the appended translation.
	 */
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
									  0, 			    0,              1, 0,
									  0,                0,              0, 1);
		final Mat4x4 ti = new Mat4x4( Math.cos(angle), Math.sin(angle), 0, 0,
									 -Math.sin(angle), Math.cos(angle), 0, 0,
									  0, 			    0,              1, 0,
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
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((i == null) ? 0 : i.hashCode());
		result = prime * result + ((m == null) ? 0 : m.hashCode());
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
		Transform other = (Transform) obj;
		if (i == null) {
			if (other.i != null)
				return false;
		} else if (!i.equals(other.i))
			return false;
		if (m == null) {
			if (other.m != null)
				return false;
		} else if (!m.equals(other.m))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return getClass().getSimpleName()
				+ "[\n\tm = " + m + "," 
				+ "\n\ti = " + i + "]";
	}
}
