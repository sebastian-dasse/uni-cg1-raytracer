package raytracer.texture;

import raytracer.Color;

public class InterpolatedImageTexture implements Texture{
	
	private final String path;

	public InterpolatedImageTexture(final String path){
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

	@Override
	public Color getColor(TexCoord2 texcoord) {
		// TODO Auto-generated method stub
		return null;
	}

}
