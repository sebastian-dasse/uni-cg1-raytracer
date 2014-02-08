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
		int height = image.getHeight();
		int width = image.getWidth();
		int mappedU = (int) (u * (double) width);
	    int mappedV = (int) (v * (double) height);
	    int resultingX;
	    int resultingY;
	    if (originAtBottom) {
			resultingX = width - mappedU;
		    resultingY = height - mappedV;
	    } else {
	    	resultingX = mappedU;
	    	resultingY = mappedV;
	    }
	    int[] RGBValues = image.getData().getPixel(resultingX, resultingY, new int[3]);
	    return new Color(RGBValues[0], RGBValues[1], RGBValues[2]);
	}

	@Override
	public Color getColor(final TexCoord2 textcoord) {
		return getColor(textcoord.u, textcoord.v);
	}

	public static void main(String [] args) {
		
		ImageTexture texture = new ImageTexture("textures/earth1.jpg");
		texture.setOriginAtBottom(false);
		texture.getColor(0.1,0.1);
		System.out.println(texture.getColor(0.1,0.1).r);
		System.out.println(texture.getColor(0.1,0.1).g);
		System.out.println(texture.getColor(0.1,0.1).b);
	}
}
