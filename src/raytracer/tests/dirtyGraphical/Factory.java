package raytracer.tests.dirtyGraphical;

import raytracer.Color;
import raytracer.World;
import raytracer.camera.OrthographicCamera;
import raytracer.camera.PerspectiveCamera;
import raytracer.geometry.AxisAlignedBox;
import raytracer.geometry.Plane;
import raytracer.geometry.Sphere;
import raytracer.geometry.Triangle;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

public class Factory {
	
	/**
	 * Builds a new <code>World</code> with the specified parameters.
	 * Expects 1-Dimensional double Array
	 * <pre>
	 * Construct tuple by the following scheme:
	 * {color0,color1,color2}
	 * --> Color 
	 * </pre>
	 * @param Color: backgroundColor
	 */
	public static final World buildWorld(double [] p) {
		return new World(new Color(p[0],p[1],p[2]));
	}
	
	/**
	 * Builds a new <code>OrthographicCamera</code> with the specified parameters.
	 * Expects 2-Dimensional double Array
	 * <pre>
	 * Construct tuple by the following scheme:
	 * {{e0,e1,e2},{g0,g1,g2},{t0,t1,t2},{s0}}
	 * --> Point / Vector / Vector / double
	 * </pre>
	 * @param e: e The eye position. Must not be <code>null</code>.
	 * @param g	The gaze direction. Must not be <code>null</code>.
	 * @param t	The up vector. Must not be <code>null</code>.
	 * @param s	The scaling factor of the camera. Must be a double value other than +-Infinity or NaN.
	 * 
	 */
	public static final OrthographicCamera buildOrtographicCamera(double [][] p) {
		return new OrthographicCamera(new Point3(p[0][0],p[0][1],p[0][2]),
									 new Vector3(p[1][0],p[1][1],p[1][2]),
									 new Vector3(p[2][0],p[2][1],p[2][2]),
									 p[3][0]);
	}
	
	/**
	 * Constructs a new <code>PerspectiveCamera</code> with the specified parameters.
	 * Expects 2-Dimensional double Array
	 * <pre>
	 * Construct tuple by the following scheme:
	 * {{e0,e1,e2},{g0,g1,g2},{t0,t1,t2},{angle0}}
	 * --> Point / Vector / Vector / double
	 * </pre>
	 * @param e		The eye position of the camera. Must not be <code>null</code>.
	 * @param g		The gaze direction of the camera. Must not be <code>null</code>.
	 * @param t		The up vector of the camera. Must not be <code>null</code>.
	 * @param angle	The half opening angle of the camera in degrees. Must be a double value other between 0 and 90 
	 * 				(including).
	 */
	
	public static final PerspectiveCamera buildPerspectiveCamera(double [][] p) {
		return new PerspectiveCamera(new Point3(p[0][0],p[0][1],p[0][2]),
									 new Vector3(p[1][0],p[1][1],p[1][2]),
									 new Vector3(p[2][0],p[2][1],p[2][2]),
									 p[3][0]);
	}
	
	/**
	 * Constructs a new <code>AxisAlignedBox</code> with the specified parameters.
	 * Expects 2-Dimensional double Array
	 * <pre>
	 * Construct tuple by the following scheme:
	 * {{lbf0,lbf1,lbf2},{run0,run1,run2},{color0,color1,color2}}
	 * --> Point / Point / Color
	 * </pre>
	 * @param lbf	The low bottom far point of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 * @param run	The right upper near point of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 * @param color	The color of the <code>AxisAlignedBox</code>. Must not be <code>null</code>.
	 */
	public static final AxisAlignedBox buildAxisAlignedBox (double p[][]) {
		return new AxisAlignedBox(new Point3(p[0][0], p[0][1], p[0][2]),
								  new Point3(p[1][0], p[1][1], p[1][2]),
								  new Color(p[2][0], p[2][1], p[2][2]));
	}

	/**
	 * Constructs a new <code>Plane</code> with the specified parameters.
	 * Expects 2-Dimensional double Array
	 * <pre>
	 * Construct tuple by the following scheme:
	 * {{a0,a1,a2},{n0,n1,n2},{color0,color1,color2}}
	 * --> Point / Normal / Color
	 * </pre>
	 * @param a		A point on the plane. Must not be <code>null</code>.
	 * @param n		The normal of the plane. Must not be <code>null</code>.
	 * @param color	The color of the plane. Must not be <code>null</code>.
	 */
	public static final Plane buildPlane(double p [][]) {
		return new Plane(new Point3(p[0][0], p[0][1], p[0][2]),
				         new Normal3(p[1][0], p[1][1], p[1][2]),
				         new Color(p[2][0], p[2][1], p[2][2]));
	}
	
	/**
	 * Constructs a new <code>Sphere</code> with the specified parameters.
	 * Expects 2-Dimensional double Array
	 * <pre>
	 * Construct tuple by the following scheme:
	 * {{c0,c1,c2},{r0},{color0,color1,color2}}
	 * --> Point / double / Color
	 * </pre>
	 * @param c		Center of the sphere. Must not be <code>null</code>.
	 * @param r		Radius of the sphere. Must not be <code>null</code>.
	 * @param color	The color of the sphere. Must not be <code>null</code>.
	 */
	public static final Sphere buildSphere (double p[][]) {
		return new Sphere(new Point3(p[0][0], p[0][1], p[0][2]),
						  p[1][0],
						  new Color(p[2][0], p[2][1], p[2][2]));
	}
	
	/**
	 * Constructs a new <code>Triangle</code> with the specified parameters.
	 * Expects 2-Dimensional double Array
	 * <pre>
	 * Construct tuple by the following scheme:
	 * {{a0,a1,a2},{b0,b1,b2},{c0,c0,c1},{color0,color1,color2}}
	 * --> Point / Point / Point / Color
	 * </pre>
	 * @param a		Point A of the Triangle. Must not be <code>null</code>.
	 * @param b		Point B of the Triangle. Must not be <code>null</code>.
	 * @param c		Point C of the Triangle. Must not be <code>null</code>.
	 * @param color	The color of the sphere. Must not be <code>null</code>.
	 */
	public static final Triangle buildTriangle (double p[][]) {
		return new Triangle(new Point3(p[0][0], p[0][1], p[0][2]),
							new Point3(p[1][0], p[1][1], p[1][2]),
							new Point3(p[2][0], p[2][1], p[2][2]),
						    new Color(p[3][0], p[3][1], p[3][2]));
	}
	
}
