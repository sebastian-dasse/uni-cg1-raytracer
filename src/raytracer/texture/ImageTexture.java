package raytracer.texture;

import raytracer.Color;
/**
 * This immutable class represents an image texture. The image is loaded from a user specified file.
 * 
 * @author Simon Lischka
 */
public class ImageTexture extends AbstractImageTexture {
	
	/**
	 * Constructs a new <code>ImageTexture</code>. Loads the specified image file when instantiated.
	 *  
	 * @param path	The path of the image. Must not be <code>null</code>.
	 */
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
