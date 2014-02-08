package raytracer.texture;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import raytracer.Color;

public class InterpolatedImageTexture implements Texture{
	private final Raster imageRaster;
	private final int widthMinus1;
	private final int heightMinus1;
	
	public InterpolatedImageTexture(final String path){
		if (path == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		try {
			BufferedImage image = ImageIO.read(new File(path));
			imageRaster = image.getData();
			heightMinus1 = imageRaster.getHeight() - 1;
			widthMinus1 = image.getWidth() - 1;
		} catch (IOException e) {
			System.err.println("Problem reading file.");
			throw new RuntimeException("Could not construct image texture from the specified path.");
		}
	}
	
	@Override
	public Color getColor(final double u, final double v) {
		final double x = u * widthMinus1;
	    final double y = heightMinus1 - v * heightMinus1;
	    
	    final int x1 = (int) Math.floor(x);
	    final int x2 = (int) Math.ceil(x);
	    final int y1 = (int) Math.floor(y);
	    final int y2 = (int) Math.ceil(y);
	    
	    double nx = x - x1;
	    double ny = y - y1;
	    
	    Color x1y1 = new Color(imageRaster.getPixel(x1, y1, new double[3]));
	    Color x2y1 = new Color(imageRaster.getPixel(x2, y1, new double[3]));
	    Color x1y2 = new Color(imageRaster.getPixel(x1, y2, new double[3]));
	    Color x2y2 = new Color(imageRaster.getPixel(x2, y2, new double[3]));
	    
	    final Color a = x1y1.mul(1.0 - nx).add(x2y1.mul(nx));
	    final Color b = x1y2.mul(1.0 - nx).add(x2y2.mul(nx));
	    
	    return a.mul(1.0 - ny).add(b.mul(ny)).mul(1.0 / 255);

//	    return new Color(c.r/255, c.g/255, c.b/255);
	}

	@Override
	public Color getColor(final TexCoord2 textcoord) {
		return getColor(textcoord.u, textcoord.v);
	}
}
