package raytracer.geometry;

import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Point3;
import raytracer.parser.ObjLoader;

/**
 * This immutable class represents a geometry which is defined in an OBJ file. To be more specific, a 
 * <code>ShapeFromFile</code> object wraps a <code>TriangleMesh</code> as specified in the OBJ file.
 * <p>
 * As for now, only vertices, and faces can be loaded.
 * <p>
 * For further information on the OBJ format 
 * <a href="http://www.martinreddy.net/gfx/3d/OBJ.spec">check out the documentation</a>.
 * 
 * TODO add comments on bounding box
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class ShapeFromFile extends Geometry {
	/**
	 * The geometry that was loaded from the file.
	 */
	private final TriangleMesh mesh;
	
	private final AxisAlignedBoundingBox bbox;

	/**
	 * Constructs a new <code>ShapeFromFile</code> object from the specified with the specified material.
	 * 
	 * @param filename	The name of the file to load the shape from.
	 * @param material	The material of the shape.
	 */
	public ShapeFromFile(final String filename, final Material material) {
		super(material);
		mesh = new ObjLoader().load(filename, material);
		
		final Point3 lbf = mesh.getMins();
		final Point3 run = mesh.getMaxs();
		bbox = new AxisAlignedBoundingBox(lbf, run);
		
//		System.out.println("lbf = " + lbf + "\nrun = " + run); // for debugging
	}
	
	@Override
	public Hit hit(final Ray ray) {
		return bbox.isHit(ray) ? mesh.hit(ray) : null;
	}
	
	//  -- without bounding box (the old way to do it)
//	@Override
//	public Hit hit(final Ray ray) {
//		return mesh.hit(ray);
//	}
}
