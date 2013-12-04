
package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

/**
 * @author
 *
 */
public class SingleColorMaterial extends Material{
		private final Color color;
		
		public SingleColorMaterial (final Color color){
			this.color = color;
		}
	
		@Override
		public Color colorFor(final Hit hit, final World world) {
			return color;
		}
}
