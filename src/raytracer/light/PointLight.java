package raytracer.light;

import raytracer.Color;
import raytracer.math.Point3;
import raytracer.math.Vector3;

public class PointLight extends Light {
	public final Point3 position;

	public PointLight(Color color, Point3 position) {
		super(color);
		this.position = position;
	}
	
	@Override
	public boolean illuminates(Point3 point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector3 directionFrom(Point3 point) {
		// TODO Auto-generated method stub
		return null;
	}

}
