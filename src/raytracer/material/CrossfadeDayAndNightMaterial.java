package raytracer.material;

import raytracer.Color;
import raytracer.Tracer;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.texture.SingleColorTexture;

/**
 * This immutable class implements a material that crossfades softly between two
 * materials depending on the amount of light thrown at a specific point.
 * 
 * @author Sebastian Dass&eacute;
 */
public class CrossfadeDayAndNightMaterial extends Material {
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
	public CrossfadeDayAndNightMaterial (final Material dayMaterial, final Material nightMaterial) {
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
		
		final double dayAmount = Color.createNormalizedColorComponent(luma);
		final double nightAmount = 1 - dayAmount;
		
		final Color dayColor = dayMaterial.colorFor(hit, world, tracer);
		final Color nightColor = nightMaterial.colorFor(hit, world, tracer);		
		return nightColor.mul(nightAmount).add(dayColor.mul(dayAmount));
	}
}
