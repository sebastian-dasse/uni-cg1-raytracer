package raytracer.geometry;

import raytracer.Ray;
import raytracer.material.Material;
import raytracer.parser.ObjLoader;

/**
 * This immutable class represents a geometry which is defined in an OBJ file. To be more specific, a 
 * <code>ShapeFromFile</code> object wraps a <code>TriangleMesh</code> as specified in the OBJ file.
 * <p>
 * Vertices, texture coordinates, normals and faces can be loaded.
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
	private final TriangleMesh mesh;

	/**
	 * Constructs a new <code>ShapeFromFile</code> object from the specified with the specified material.
	 * 
	 * @param filename	The name of the file to load the shape from.
	 * @param material	The material of the shape.
	 */
	public ShapeFromFile(final String filename, final Material material) {
		super(material);
		mesh = new ObjLoader().load(filename, material);
	}
	
	@Override
	public Hit hit(final Ray ray) {
		return mesh.hit(ray);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((mesh == null) ? 0 : mesh.hashCode());
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
		final ShapeFromFile other = (ShapeFromFile) obj;
		if (mesh == null) {
			if (other.mesh != null)
				return false;
		} else if (!mesh.equals(other.mesh))
			return false;
		return true;
	}
}
