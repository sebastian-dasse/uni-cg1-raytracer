package raytracer.texture;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import raytracer.Color;

public class ImageTexture implements Texture{
//	private final BufferedImage image;
	private final Raster imageRaster;
	private final int widthMinus1;
	private final int heightMinus1;
	private boolean originAtBottom;
	
	public ImageTexture(final String path){
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
		originAtBottom = true;
	}
	
	public void setOriginAtBottom(boolean originAtBottom) {
		this.originAtBottom = originAtBottom;
	}
	
	@Override
	public Color getColor(final double u, final double v) {
		final int mappedU = (int) (u * widthMinus1);
	    final int mappedV = (int) (v * heightMinus1);
	    final int resultingX = mappedU;
	    final int resultingY;
	    if (originAtBottom) {
		    resultingY = heightMinus1 - mappedV;
	    } else {
	    	resultingY = mappedV;
	    }
	    final int[] RGBValues = imageRaster.getPixel(resultingX, resultingY, new int[3]);
	    
//	    final long
	    
	    return new Color(RGBValues[0], RGBValues[1], RGBValues[2]);
	}

	@Override
	public Color getColor(final TexCoord2 textcoord) {
		return getColor(textcoord.u, textcoord.v);
	}

	//---- Test
	public static void main(String [] args) {
		ImageTexture texture = new ImageTexture("textures/earth1.jpg");
		texture.setOriginAtBottom(false);
		texture.getColor(0.1,0.1);
		System.out.println(texture.getColor(1.0, 1.0).r);
		System.out.println(texture.getColor(1.0, 1.0).g);
		System.out.println(texture.getColor(1.0, 1.0).b);
	}
}
