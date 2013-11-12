package raytracer.geometry;

import java.awt.Color;

import raytracer.math.Point3;

public class Sphere extends Geometry {
	private final Point3 c;
	private final double r;
	
	public Sphere(Point3 c, double r, Color color) {
		super(color);
		this.c = c;
		this.r = r;
	}
}
