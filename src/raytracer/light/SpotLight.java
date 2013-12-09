package raytracer.light;

import raytracer.Color;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * This immutable class represents a spot light in a three-dimensional scene. It has a position, a direction and an 
 * opening angle, which defines the cone inside of which everything is illuminated by the spot light.
 * 
 * @author Sebastian Dass&eacute; 
 *
 */
public class SpotLight extends Light {
	/**
	 * The position of this <code>SpotLight</code>.
	 */
	public final Point3 position;
	/**
	 * The direction of this <code>SpotLight</code>.
	 */
	public final Vector3 direction;
	/**
	 * The half opening angle of this <code>SpotLight</code> in radians.
	 */
	public final double halfAngle;
	
	/**
	 * Constructs a new <code>SpotLight</code> object with the specified parameters.
	 * 
	 * @param color		The color of the light. Must not be <code>null</code>.
	 * @param position	The position of the light. Must not be <code>null</code>.
	 * @param direction	The direction of the light. Must not be <code>null</code>.
	 * @param halfAngle	The half opening angle of the spot light in radians. Must be a double value between 0 (excluding) and PI (including).
	 */
	public SpotLight(final Color color, final Point3 position, final Vector3 direction, final double halfAngle) {
		super(color);
		if (position == null || direction == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		if (halfAngle < 0 || Math.PI < halfAngle) {
			throw new IllegalArgumentException("The parameter 'halfAngle' must be between 0 (excluding) and PI (including).");
		} 
		this.position = position;
		this.direction = direction.normalized();
		this.halfAngle = halfAngle;
	}

	/**
	 * Returns <code>true</code> if the specified point is illuminated by this light, i.e. if it is in the cone of light.
	 * 
	 * @param point	The point to be checked.
	 * @return		<code>true</code> if the specified point is illuminated, otherwise <code>false</code>.
	 */
	@Override
	public boolean illuminates(final Point3 point) {
		
		// Formula: cos(l, d) = <l, d>, with |l| = |d| = 1, l:= light direction, r: = vector from light position to point
		return Math.acos(direction.normalized().dot(directionFrom(point).mul(-1))) <= halfAngle;
	}

	@Override
	public Vector3 directionFrom(final Point3 point) {
//		System.err.println(position.sub(point).normalized());
		if (point == null) {
			throw new IllegalArgumentException("The parameter 'point' must not be null.");
		}
		return position.sub(point).normalized(); // normalized
	}
}
