package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

public abstract class Material {
	
	abstract Color colorFor(Hit hit, World world);
}
