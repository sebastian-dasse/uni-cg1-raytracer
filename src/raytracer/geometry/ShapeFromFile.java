package raytracer.geometry;

import java.io.File;

import raytracer.Ray;
import raytracer.material.Material;

public class ShapeFromFile extends Geometry {
	private final File file;
	
	public ShapeFromFile(String filename, Material material) {
		super(material);
		this.file = new File(filename);
	}
	
	public ShapeFromFile(File filename, Material material) {
		super(material);
		this.file = filename;
	}
	
	@Override
	public Hit hit(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
