package raytracer.geometry;

import java.util.zip.DataFormatException;

import raytracer.ObjLoader;
import raytracer.Ray;
import raytracer.material.Material;

public class ShapeFromFile extends Geometry {
	private final ObjLoader parser;
	private Geometry geo;

	public ShapeFromFile(final String filename, final Material material) {
		super(material);
		parser = new ObjLoader();
		parser.load(filename);
		geo = null;
		
		try {
			parser.parseBasicData();
//			parser.listAll(); // for debugging
			geo = parser.createTriangleMash(material);
		} catch (DataFormatException e) {
			System.err.println("The specified file does not match the required data format.");
		}
	}
	
	@Override
	public Hit hit(final Ray ray) {
		return geo.hit(ray);
	}
}
