package raytracer.texture;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import raytracer.Color;

public class ImageTexture implements Texture{
	protected final Raster imageRaster;
	protected final int widthMinus1;
	protected final int heightMinus1;
	
	public ImageTexture(final String path){
		if (path == null) {
			throw new IllegalArgumentException("The parameter 'path' must not be null.");
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
		final int mappedU = (int) (u * widthMinus1);
	    final int mappedV = (int) (v * heightMinus1);
	    final double[] RGBValues = imageRaster.getPixel(mappedU, heightMinus1 - mappedV, new double[3]);
//	    final long // TODO try bitshifting or remove
	    return new Color(RGBValues[0]/255, RGBValues[1]/255, RGBValues[2]/255);
	    
	}

	@Override
	public Color getColor(final TexCoord2 textcoord) {
		return getColor(textcoord.u, textcoord.v);
	}

	//---- Test TODO remove later
	public static void main(String [] args) {
		ImageTexture texture = new ImageTexture("textures/colorTest.jpg");
		texture.getColor(0.1,0.1);
		final double x = 0.5;
		final double y = 0.1;
		System.out.println(texture.getColor(x, y).r);
		System.out.println(texture.getColor(x, y).g);
		System.out.println(texture.getColor(x, y).b);
	}
}
