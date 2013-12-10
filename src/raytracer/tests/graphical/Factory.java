package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.World;
import raytracer.camera.OrthographicCamera;
import raytracer.camera.PerspectiveCamera;
import raytracer.geometry.AxisAlignedBox;
import raytracer.geometry.Plane;
import raytracer.geometry.Sphere;
import raytracer.geometry.Triangle;
import raytracer.material.Material;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This factory class generates the objects used in the raytracer
 * (World, Cameras, Geometries) from primitive double Arrays.
 * <p>
 * It requires careful attention concerning the order of the Array
 * fields. No additional checks are performed when the objects
 * are generated - this may lead to unexpected exceptions.
 * </p><p>
 * The class should only be used for testing purposes.
 * </p>
 * 
 * @author Simon Lischka
 *
 */
public final class Factory {
	
	/**
	 * Builds a new <code>World</code> with the specified parameters.
	 * Expects 2-Dimensional double Array
	 * <pre>
	 * Construct tuple by the following scheme:
	 * {{color0,color1,color2}, {color0,color1,color2}}
	 * --> Color / Color
	 * </pre>
	 */
	public static final World buildWorld(double[][] p, double indexOfRefraction) {
		return new World(new Color(p[0][0],p[0][1],p[0][2]), new Color(p[1][0],p[1][1],p[1][2]), indexOfRefraction);
	}
	
	/**
	 * Builds a new <code>OrthographicCamera</code> with the specified parameters.
	 * Expects 2-Dimensional double Array
	 * <pre>
	 * Construct tuple by the following scheme:
	 * {{e0,e1,e2},{g0,g1,g2},{t0,t1,t2},{s0}}
	 * --> Point3 / Vector3 / Vector3 / double
	 * </pre>
	 */
	public static final OrthographicCamera buildOrthographicCamera(double[][] p) {
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
	 * --> Point3 / Vector3 / Vector3 / double
	 * </pre>
	 */
	
	public static final PerspectiveCamera buildPerspectiveCamera(double[][] p) {
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
	 * {{lbf0,lbf1,lbf2},{run0,run1,run2},material}
	 * --> Point3 / Point3 / Material
	 * </pre>
	 */
	public static final AxisAlignedBox buildAxisAlignedBox(double[][] p, Material material) {
		return new AxisAlignedBox(new Point3(p[0][0], p[0][1], p[0][2]),
								  new Point3(p[1][0], p[1][1], p[1][2]),
//								  new Color(p[2][0], p[2][1], p[2][2]));
								  material);
	}

	/**
	 * Constructs a new <code>Plane</code> with the specified parameters.
	 * Expects 2-Dimensional double Array
	 * <pre>
	 * Construct tuple by the following scheme:
	 * {{a0,a1,a2},{n0,n1,n2},material}
	 * --> Point3 / Normal3 / Material
	 * </pre>
	 */
	public static final Plane buildPlane(double[][] p, Material material) {
		return new Plane(new Point3(p[0][0], p[0][1], p[0][2]),
				         new Normal3(p[1][0], p[1][1], p[1][2]),
//				         new Color(p[2][0], p[2][1], p[2][2]));
				         material);
	}
	
	/**
	 * Constructs a new <code>Sphere</code> with the specified parameters.
	 * Expects 2-Dimensional double Array
	 * <pre>
	 * Construct tuple by the following scheme:
	 * {{c0,c1,c2},{r0},material}
	 * --> Point3 / double / Material
	 * </pre>
	 */
	public static final Sphere buildSphere(double[][] p, Material material) {
		return new Sphere(new Point3(p[0][0], p[0][1], p[0][2]),
						  p[1][0],
//						  new Color(p[2][0], p[2][1], p[2][2]));
						  material);
	}
	
	/**
	 * Constructs a new <code>Triangle</code> with the specified parameters.
	 * Expects 2-Dimensional double Array
	 * <pre>
	 * Construct tuple by the following scheme:
	 * {{a0,a1,a2},{b0,b1,b2},{c0,c0,c1},{an0,an1,an2},{bn0,bn1,bn2},{cn0,cn0,cn1},material}
	 * --> Point3 / Point3 / Point3 / Normal3 / Normal3 / Normal3 / Material
	 * </pre>
	 */
	public static final Triangle buildTriangle(double[][] p, Material material) {
		return new Triangle(new Point3(p[0][0], p[0][1], p[0][2]),
							new Point3(p[1][0], p[1][1], p[1][2]),
							new Point3(p[2][0], p[2][1], p[2][2]),
							new Normal3(p[3][0], p[3][1], p[3][2]),
							new Normal3(p[4][0], p[4][1], p[4][2]),
							new Normal3(p[5][0], p[5][1], p[5][2]),
//						    new Color(p[3][0], p[3][1], p[3][2]));
							material);
	}
}
