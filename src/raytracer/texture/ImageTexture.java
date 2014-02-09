package raytracer.texture;

import raytracer.Color;

public class ImageTexture extends AbstractImageTexture {
	
	public ImageTexture(final String path){
		super(path);
	}
	
	@Override
	public Color getColor(final double u, final double v) {
		final int x = (int) (u * widthMinus1);
	    final int y = heightMinus1 - (int) (v * heightMinus1);
	    
	    final double[] RGBValues = imageRaster.getPixel(x, y, new double[3]);
	    return new Color(RGBValues[0]/255, RGBValues[1]/255, RGBValues[2]/255);
	}

	@Override
	public Color getColor(final TexCoord2 textcoord) {
		return getColor(textcoord.u, textcoord.v);
	}
}
