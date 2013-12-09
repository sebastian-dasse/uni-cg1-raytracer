package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

/**
 * This immutable class implements the color of a material with a simple surface.
 * @author Novichkov Maxim
 */
public class SingleColorMaterial extends Material{
		/**
		 * The color of a surface material.
		 */
		private final Color color;
		
		/**
		 * @param color The surface color.
		 */
		public SingleColorMaterial (final Color color){
			this.color = color;
		}
	
		@Override
		public Color colorFor(final Hit hit, final World world) {
			return color;
		}
}
