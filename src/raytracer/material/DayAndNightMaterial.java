package raytracer.material;

import raytracer.Color;
import raytracer.Tracer;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.texture.SingleColorTexture;
import raytracer.texture.Texture;

public class DayAndNightMaterial extends Material {
	
	/**
	 * The texture of this material.
	 */
	private final double THRESHOLD = 0.5;
	private final Material dayMaterial;
	private final Material nightMaterial;
	private Texture dayTexture;
	private Texture nightTexture;
	/**
	 * Constructs a new <code>LambertMaterial</code> object with the specified surface texture.
	 * 
	 * @param texture	The surface texture. Must not be <code>null</code>.
	 */
	public DayAndNightMaterial (final Texture dayTexture, final Texture nightTexture) {
		if (dayTexture == null || nightTexture == null) {
			throw new IllegalArgumentException("The parameters 'texture' must not be null.");
		}
		this.dayTexture = dayTexture;
		this.nightTexture = nightTexture;
		this.dayMaterial = new SingleColorMaterial(dayTexture);
		this.nightMaterial = new SingleColorMaterial(nightTexture);
	}
	
	@Override
	public Color colorFor(Hit hit, World world, Tracer tracer) {
		final Color startColor = new Color(1, 1, 1);
		final double startLuma = startColor.r * 0.299 + startColor.g * 0.587 + startColor.b * 0.114;
		
		Material testMaterial = new SingleColorMaterial(
				new SingleColorTexture(
						new Color(1, 1, 1)
				)
		);
		
		Color endColor = testMaterial.colorFor(hit, world, tracer);
		
		final double endLuma = endColor.r * 0.299 + endColor.g * 0.587 + endColor.b * 0.114;
		
		if (endLuma - startLuma > THRESHOLD) {
			return dayMaterial.colorFor(hit, world, tracer);
		}
		return nightMaterial.colorFor(hit, world, tracer);		
	}
	
	public void setDayTexture(Texture dayTexture) {
		this.dayTexture = dayTexture;
	}
	
	public void setNightTexture(Texture nightTexture) {
		this.nightTexture = nightTexture;
	}
	

}
