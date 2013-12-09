package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

/**
 * This immutable class implements the color of a material with a simple surface.
 * 
 * @author Maxim Novichkov
 */
public class SingleColorMaterial extends Material{
		/**
		 * The surface color of this material.
		 */
		private final Color color;
		
		/**
		 * Constructs a new <code>SingleColorMaterial</code> object with the specified surface color.
		 * 
		 * @param color The surface color. Must not be <code>null</code>.
		 */
		public SingleColorMaterial (final Color color){
			if (color == null) {
				throw new IllegalArgumentException("The parameter 'color' must not be null.");
			}
			this.color = color;
		}
	
		@Override
		public Color colorFor(final Hit hit, final World world) {
			return color;
		}
}
