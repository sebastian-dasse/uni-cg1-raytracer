package raytracer.texture;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import raytracer.Color;

public abstract class AbstractImageTexture implements Texture {
	private final BufferedImage image;
	protected final Raster imageRaster;
	protected final int widthMinus1;
	protected final int heightMinus1;
	
	public AbstractImageTexture(final String path){
		if (path == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.err.println("Problem reading file.");
			throw new RuntimeException("Could not construct image texture from '" + path + "'.");
		}
		imageRaster = image.getData();
		heightMinus1 = imageRaster.getHeight() - 1;
		widthMinus1 = image.getWidth() - 1;
	}
	
	@Override
	public abstract Color getColor(double u, double v);

	@Override
	public abstract Color getColor(TexCoord2 texcoord);
}
