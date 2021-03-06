package raytracer.texture;

import raytracer.Color;
/**
 * This immutable class represents an image texture which is interpolated using bilinear interpolation for improved 
 * quality. The image is loaded from a user specified file.
 * 
 * @author Maxim Novichkov
 * @author Sebastian Dass&eacute;
 */
public class InterpolatedImageTexture extends AbstractImageTexture {
	/**
	 * Constructs a new <code>InterpolatedImageTexture</code>. Loads the specified image file when instantiated.
	 * 
	 * @param path	The path of the image. Must not be <code>null</code>.
	 */
	public InterpolatedImageTexture(final String path){
		super(path);
	}
	
	@Override
	public Color getColor(final double u, final double v) {

		final double x = u * widthMinus1;
	    final double y = heightMinus1 - v * heightMinus1;
	    
	    final int x1 = (int) Math.floor(x);
	    final int x2 = (int) Math.ceil(x);
	    final int y1 = (int) Math.floor(y);
	    final int y2 = (int) Math.ceil(y);
	    
	    /*
	     * Formulas:
	     * 		nx = x - x1
	     * 		ny = y - y1
	     * 		a = I(x1, y1)(1 - nx) + I(x2, y1)nx
	     * 		b = I(x2, y1)(1 - nx) + I(x2, y2)nx
	     * 		c = a(1 - nx) + b ny
	     */
	    double nx = x - x1;
	    double ny = y - y1;
	    
	    Color x1y1 = new Color(imageRaster.getPixel(x1, y1, new double[3]));
	    Color x2y1 = new Color(imageRaster.getPixel(x2, y1, new double[3]));
	    Color x1y2 = new Color(imageRaster.getPixel(x1, y2, new double[3]));
	    Color x2y2 = new Color(imageRaster.getPixel(x2, y2, new double[3]));
	    
	    final Color a = x1y1.mul(1.0 - nx).add(x2y1.mul(nx));
	    final Color b = x1y2.mul(1.0 - nx).add(x2y2.mul(nx));
	    
	    return a.mul(1.0 - ny).add(b.mul(ny)).mul(1.0 / 255);
	}
	
	@Override
	public Color getColor(final TexCoord2 textcoord) {
		return getColor(textcoord.u, textcoord.v);
	}
}
