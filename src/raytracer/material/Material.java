package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

public abstract class Material {
	
	abstract public Color colorFor(final Hit hit, final World world);
}
