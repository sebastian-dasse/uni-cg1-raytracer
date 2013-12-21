package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

public class ReflectiveMaterial extends Material{
	
	private final Color diffuse;
	private final Color specular;
	private final int exponent;
	private final Color reflection;
	
	public ReflectiveMaterial(final Color diffuse, final Color specular, final int exponent, final Color reflection){
		this.diffuse = diffuse;
		this.specular = specular;
		this.exponent = exponent;
		this.reflection = reflection;
	}
	
	public void ReflectiveMaterial(final Color diffuse, final Color specular, final int exponent, final Color reflection){
		
	}
	
	@Override
	public Color colorFor(Hit hit, World world) {
		// TODO Auto-generated method stub
		return null;
	}

}
