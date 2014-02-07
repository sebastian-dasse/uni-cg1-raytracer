package raytracer.texture;

import raytracer.Color;

public class SingleColorTexture implements Texture{
	
	private final Color color;
	
	public SingleColorTexture(final Color color){
		if (color == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.color = color;
	}
	
	@Override
	public Color getColor(double u, double v) {
		// TODO Auto-generated method stub
		return null;
	}

}
