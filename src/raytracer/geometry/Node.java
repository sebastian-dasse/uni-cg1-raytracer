package raytracer.geometry;

import java.util.LinkedList;

import raytracer.Ray;
import raytracer.material.Material;
import raytracer.math.Transform;

public class Node extends Geometry{

	public final Transform transform;
	
	public final LinkedList<Geometry> geos;

	public Node(final Transform transform, final LinkedList<Geometry> geos, final Material material) {
		super(material);
		this.transform = transform;
		this.geos = geos;
	}

	@Override
	public Hit hit(Ray ray) {
		// TODO Auto-generated method stub
		return null;
	}

}
