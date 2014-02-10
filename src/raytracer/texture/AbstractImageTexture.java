package raytracer.texture;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import raytracer.Color;
/**
 *	This abstract immutable class represents an image texture that
 *  provides the texture data by loading a user specified file.
 *
 * @author Simon Lischka
 * @author Sebastian Dass&eacute;
 *
 */
public abstract class AbstractImageTexture implements Texture {
	/**
	 * The BufferedImage that contains the pixel data of this texture
	 */
	private final BufferedImage image;
	/**
	 * The raster extracted from the BufferedImage
	 */
	protected final Raster imageRaster;
	/**
	 * Width in suitable form for looping arrays
	 */
	protected final int widthMinus1;
	/**
	 * Height in suitable form for looping arrays
	 */
	protected final int heightMinus1;
	
	/**
	 * Constructor of the texture. Loads file when instantiated. 
	 * @param path The path used to load the file used in the texture
	 */
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
