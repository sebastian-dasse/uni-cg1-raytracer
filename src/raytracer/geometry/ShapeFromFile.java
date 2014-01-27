package raytracer.geometry;

import raytracer.ObjLoader;
import raytracer.Ray;
import raytracer.material.Material;

/**
 * TODO comment everything.
 * 
 * @author Sebastian Dass&eacute;
 *
 */
public class ShapeFromFile extends Geometry {
	private Geometry geo;

	public ShapeFromFile(final String filename, final Material material) {
		super(material);
		geo = new ObjLoader().load(filename, material);
	}
	
	@Override
	public Hit hit(final Ray ray) {
		return geo.hit(ray);
	}
}
