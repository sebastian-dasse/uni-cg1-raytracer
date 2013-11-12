package raytracer.geometry;

import java.awt.Color;

import raytracer.math.Point3;

public class Triangle extends Color {
	private final Point3 a;
	private final Point3 b;
	private final Point3 c;
	
	public Triangle(Point3 a, Point3 b, Point3 c, Color color) {
		super(color);
		this.a = a;
		this.b = b;
		this.c = c;
	}
}
