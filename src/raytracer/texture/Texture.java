package raytracer.texture;

import raytracer.Color;

public interface Texture {
	
	public Color getColor(final double u, final double v);

	public Color getColor(final TexCoord2 texcoord);
}
