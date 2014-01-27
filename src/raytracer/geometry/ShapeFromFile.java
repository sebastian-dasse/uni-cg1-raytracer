package raytracer.geometry;

import raytracer.ObjLoader;
import raytracer.Ray;
import raytracer.material.Material;

/**
 * This immutable class represents a geometry which is defined in an OBJ file. To be more specific, a 
 * <code>ShapeFromFile</code> object wraps a <code>TriangleMesh</code> as specified in the OBJ file.
 * <p>
 * As for now, only vertices, and faces can be loaded.
 * <p>
 * For further information on the OBJ format 
 * <a href="http://www.martinreddy.net/gfx/3d/OBJ.spec">check out the documentation</a>.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class ShapeFromFile extends Geometry {
	/**
	 * The geometry that was loaded from the file.
	 */
	private final Geometry geo;

	/**
	 * Constructs a new <code>ShapeFromFile</code> object from the specified with the specified material.
	 * 
	 * @param filename	The name of the file to load the shape from.
	 * @param material	The material of the shape.
	 */
	public ShapeFromFile(final String filename, final Material material) {
		super(material);
		geo = new ObjLoader().load(filename, material);
	}
	
	@Override
	public Hit hit(final Ray ray) {
		return geo.hit(ray);
	}
}
