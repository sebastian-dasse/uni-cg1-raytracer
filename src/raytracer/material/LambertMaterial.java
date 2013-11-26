package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

public class LambertMaterial extends Material{
	private Color color;
	
	public LambertMaterial (Color color){
		this.color = color;
	}
	@Override
	Color colorFor(Hit hit, World world) {
		// TODO Auto-generated method stub
		return null;
	}

}
