package raytracer.material;

import raytracer.Color;
import raytracer.Tracer;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.texture.SingleColorTexture;

/**
 * This immutable class implements ...
 *
 */
public class DayAndNightMaterial extends Material {

	private final double THRESHOLD = 0.5;
	private final Material dayMaterial;
	private final Material nightMaterial;
	
	/**
	 * Constructs a new <code>DayAndNightMaterial</code> object ...
	 * 
	 * @param texture	The surface texture. Must not be <code>null</code>.
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
		/* No Y-Compensation --> seems to yield the same result
		 * final double luma = (resulting.r + resulting.g + resulting.b) / 3;
		 */
		
		if (luma > THRESHOLD) {
			return dayMaterial.colorFor(hit, world, tracer);
		}
		return nightMaterial.colorFor(hit, world, tracer);		
	}
}
