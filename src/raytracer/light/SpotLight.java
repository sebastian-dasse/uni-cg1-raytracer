package raytracer.light;

import raytracer.Color;
import raytracer.math.Point3;
import raytracer.math.Vector3;

public class SpotLight extends Light {
	public final Point3 position;
	public final Vector3 direction;
	public final double halfAngle;
	
	public SpotLight(final Color color, final Point3 position, final Vector3 direction, final double halfAngle) {
		super(color);
		this.position = position;
		this.direction = direction;
		this.halfAngle = halfAngle;
	}

	@Override
	public boolean illuminates(final Point3 point) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Vector3 directionFrom(final Point3 point) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
