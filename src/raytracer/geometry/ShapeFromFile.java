package raytracer.geometry;

import java.io.File;

import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Transform;

public class ShapeFromFile extends Geometry {
	private final File file;
	private final Node node;

	public ShapeFromFile(final File file, final Material material) {
		super(material);
		this.file = file;
		node = new Node(new Transform());
	}
	
	public ShapeFromFile(final String filename, final Material material) {
		this(new File(filename), material);
	}
	
	@Override
	public Hit hit(final Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}
}
