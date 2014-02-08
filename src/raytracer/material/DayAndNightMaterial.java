package raytracer.material;

import raytracer.Color;
import raytracer.Constants;
import raytracer.Tracer;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.texture.SingleColorTexture;

public class DayAndNightMaterial extends Material {
	
	/**
	 * The texture of this material.
	 */
	private final double THRESHOLD = 0.5;
	private Material dayMaterial;
	private Material nightMaterial;
	/**
	 * Constructs a new <code>LambertMaterial</code> object with the specified surface texture.
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
	public Color colorFor(Hit hit, World world, Tracer tracer) {
		Material testMaterial = new SingleColorMaterial(
				new SingleColorTexture(
						new Color(1, 1, 1)
				)
		);
		
		Color testColor = testMaterial.colorFor(hit, world, tracer);
		final double endLuma = testColor.r * 0.299 + testColor.g * 0.587 + testColor.b * 0.114;
		System.out.println(endLuma);
		if (Math.abs(endLuma) > THRESHOLD) {
			return dayMaterial.colorFor(hit, world, tracer);
		}
		return nightMaterial.colorFor(hit, world, tracer);		
	}
	
	public void setDayMaterial(Material material) {
		this.dayMaterial =  material;
	}
	
	public void setNightMaterial(Material nightMaterial) {
		this.nightMaterial = nightMaterial;
	}

}
