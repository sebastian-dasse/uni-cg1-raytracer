package raytracer.texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import raytracer.Color;

public class ImageTexture implements Texture{
	private BufferedImage image;
	private boolean originAtBottom;
	
	public ImageTexture(final String path){
		if (path == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.err.println("Problem reading file.");
		}
		originAtBottom = true;
	}
	
	public void setOriginAtBottom(boolean originAtBottom) {
		this.originAtBottom = originAtBottom;
	}
	
	@Override
	public Color getColor(final double u, final double v) {
		int height = image.getHeight() - 1;
		int width = image.getWidth() - 1;
		int mappedU = (int) (u * width);
	    int mappedV = (int) (v * height);
	    int resultingX;
	    int resultingY;
	    resultingX = mappedU;
	    if (originAtBottom) {
		    resultingY = height - mappedV;
	    } else {
	    	resultingY = mappedV;
	    }
//	    val argb = image.getRGB(math.round( x ).asInstanceOf[Int], math.round( y ).asInstanceOf[Int])
//	    	    val r = (argb & 0xff0000) >> 16
//	    	    val g = (argb & 0xff00) >> 8
//	    	    val b = argb & 0xff
	    	    
//	    int[] RGBValues = image.getData().getPixel(resultingX, resultingY, new int[3]);
//	    return new Color(RGBValues[0], RGBValues[1], RGBValues[2]);
	    
	    final int rgb = image.getRGB(Math.round(resultingX), Math.round(resultingY));
	    final int r = (rgb & 0xff0000) >> 16;
	    final int g = (rgb & 0xff00) >> 8;
	    final int b = rgb & 0xff;
	    return new Color(r, g, b);
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
