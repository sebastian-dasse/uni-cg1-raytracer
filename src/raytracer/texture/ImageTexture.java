package raytracer.texture;

import raytracer.Color;

public class ImageTexture implements Texture{
	
	private final String path;
	
	public ImageTexture(final String path){
		if (path == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.path = path;
	}
	
	@Override
	public Color getColor(final double u, final double v) {
		// TODO Auto-generated method stub
		return null;
	}

}
