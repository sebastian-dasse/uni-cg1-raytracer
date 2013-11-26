
package raytracer.material;

import raytracer.Color;
import raytracer.World;
import raytracer.geometry.Hit;

/**
 * @author Max
 *
 */
public class SingleColorMaterial extends Material{
		private Color color;
		
		public SingleColorMaterial (Color color){
			this.color = color;
		}
	
		@Override
		Color colorFor(Hit hit, World world) {
			// TODO Auto-generated method stub
			return null;
		}
}
