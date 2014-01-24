package raytracer.math;

public class Mat4x4 {
	/**
	 * The m11 component of this <code>Mat4x4</code>.
	 */
	public final double m11;
	/**
	 * The m12 component of this <code>Mat4x4</code>.
	 */
	public final double m12;
	/**
	 * The m13 component of this <code>Mat4x4</code>.
	 */
	public final double m13;
	/**
	 * The m14 component of this <code>Mat4x4</code>.
	 */
	public final double m14;
	/**
	 * The m21 component of this <code>Mat4x4</code>.
	 */
	public final double m21;
	/**
	 * The m22 component of this <code>Mat4x4</code>.
	 */
	public final double m22;
	/**
	 * The m23 component of this <code>Mat4x4</code>.
	 */
	public final double m23;
	/**
	 * The m24 component of this <code>Mat4x4</code>.
	 */
	public final double m24;
	/**
	 * The m31 component of this <codeMat4x4</code>.
	 */
	public final double m31;
	/**
	 * The m32 component of this <code>Mat4x4</code>.
	 */
	public final double m32;
	/**
	 * The m33 component of this <code>Mat4x4</code>.
	 */
	public final double m33;
	/**
	 * The m34 component of this <code>Mat4x4</code>.
	 */
	public final double m34;
	/**
	 * The m41 component of this <code>Mat4x4</code>.
	 */
	public final double m41;
	/**
	 * The m42 component of this <code>Mat4x4</code>.
	 */
	public final double m42;
	/**
	 * The m43 component of this <code>Mat4x4</code>.
	 */
	public final double m43;
	/**
	 * The m44 component of this <code>Mat4x4</code>.
	 */
	public final double m44;

	public Mat4x4(final double m11, final double m12, final double m13, final double m14,
			  final double m21, final double m22, final double m23, final double m24, 
			  final double m31, final double m32, final double m33, final double m34,
			  final double m41, final double m42, final double m43, final double m44) {
	this.m11 = m11;
	this.m12 = m12;
	this.m13 = m13;
	this.m14 = m14;
	this.m21 = m21;
	this.m22 = m22;
	this.m23 = m23;
	this.m24 = m24;
	this.m31 = m31;
	this.m32 = m32;
	this.m33 = m33;
	this.m34 = m34;
	this.m41 = m41;
	this.m42 = m42;
	this.m43 = m43;
	this.m44 = m44;
	}
	
	public Vector3 mul(final Vector3 v){
		return null;
	}
	
	public Point3 mul(final Point3 p){
		return null;
	}
	
	public Mat4x4 mul(final Mat4x4 m){
		return null;
	}
	
	
	
}
