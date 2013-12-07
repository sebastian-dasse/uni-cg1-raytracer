package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;
import raytracer.math.Normal3;
import raytracer.math.Vector3;

/**
 * 
 * 
 * @author 
 *
 */
public class PhongMaterial extends Material{
	/**
	 * 
	 */
	private final Color diffuse;
	/**
	 * 
	 */
	private final Color specular;
	/**
	 * 
	 */
	private final int exponent; 
	
	/**
	 * 
	 * 
	 * @param diffuse
	 * @param specular
	 * @param exponent
	 */
	public PhongMaterial(final Color diffuse, final Color specular, final int exponent){
		this.diffuse = diffuse;
		this.specular = specular;
		this.exponent = exponent;
	}

	@Override
	public Color colorFor(final Hit hit, final World world) {
		
		//c = cr[ca + cl max(n*l) + cl max(0, r*e))]
		Normal3 normal = hit.normal;
		
		
		
		return null;
	}
}
