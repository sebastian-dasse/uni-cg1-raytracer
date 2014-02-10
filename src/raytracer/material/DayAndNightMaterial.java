package raytracer.material;

import raytracer.Color;
import raytracer.Tracer;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.texture.SingleColorTexture;

/**
 * This immutable class implements a material that switches between two
 * materials depending on the amount of light thrown at a specific point.
 * 
 * @author Simon Lischka
 */
public class DayAndNightMaterial extends Material {
	/**
	 * The threshold for the day/night switch.
	 */
	private final double THRESHOLD = 0.5;
	/**
	 * The material for highly illuminated zones.
	 */
	private final Material dayMaterial;
	/**
	 * The material for poorly illuminated zones.
	 */
	private final Material nightMaterial;
	
	/**
	 * Constructs a new <code>DayAndNightMaterial</code> object with the two specified materials.
	 * 
	 * @param dayMaterial 	The material used for highly illuminated zones. Must not be <code>null</code>.
	 * @param nightMaterial	The material used for poorly illuminated zones. Must not be <code>null</code>.
	 */
	public DayAndNightMaterial (final Material dayMaterial, final Material nightMaterial) {
		if (dayMaterial == null || nightMaterial == null) {
			throw new IllegalArgumentException("The parameters 'material' must not be null.");
		}
		this.dayMaterial = dayMaterial;
		this.nightMaterial = nightMaterial;
	}
	
	@Override
	public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
		final Material testMaterial = new LambertMaterial(new SingleColorTexture(new Color(1, 1, 1)));
		final Color resulting = testMaterial.colorFor(hit, world, tracer);
		
		final double luma = resulting.r * 0.299 + resulting.g * 0.587 + resulting.b * 0.114;
		
		if (luma > THRESHOLD) {
			return dayMaterial.colorFor(hit, world, tracer);
		}
		return nightMaterial.colorFor(hit, world, tracer);		
	}
}
