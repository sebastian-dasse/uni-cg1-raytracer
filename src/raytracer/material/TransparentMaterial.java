package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

public class TransparentMaterial extends Material{
	private final double indexOfRefraction;
	
	public TransparentMaterial(final double indexOfRefraction){
		this.indexOfRefraction = indexOfRefraction;
	}
	
	public void TransparentMaterial(final double indexOfRefraction){
		
	}
	
	@Override
	public Color colorFor(Hit hit, World world) {
		// TODO Auto-generated method stub
		return null;
	}

}
