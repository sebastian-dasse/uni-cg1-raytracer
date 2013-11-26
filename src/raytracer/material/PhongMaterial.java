package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

public class PhongMaterial extends Material{
	private Color diffuse;
	private Color specular;
	private int exponent; 
	
	public PhongMaterial(Color diffuse, Color specular, int exponent){
		this.diffuse = diffuse;
		this.specular = specular;
		this.exponent = exponent;
	}

	@Override
	Color colorFor(Hit hit, World world) {
		// TODO Auto-generated method stub
		return null;
	}

}
