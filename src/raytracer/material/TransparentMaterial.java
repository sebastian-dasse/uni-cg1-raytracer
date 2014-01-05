package raytracer.material;

import raytracer.Color;
import raytracer.Tracer;
import raytracer.World;
import raytracer.geometry.Hit;

public class TransparentMaterial extends Material{
	private final double indexOfRefraction;
	
	public TransparentMaterial(final double indexOfRefraction){
		this.indexOfRefraction = indexOfRefraction;
	}
	
	@Override
	public Color colorFor(final Hit hit, final World world, final Tracer tracer) {
		// TODO Auto-generated method stub
		return null;
	}

}
