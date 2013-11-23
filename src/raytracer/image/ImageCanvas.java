package raytracer.image;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

/**
 * This class represents the canvas for a diagonal line on black background. It is initialized with size parameters. 
 * <p>
 * The class includes a paint method which is called to display the generated image.
 * It also includes methods to change its size and retrieve the embedded <code>BufferedImage</code> object.
 * 
 * @author Simon Lischka
 */
public final class ImageCanvas extends Canvas {
	/**
	 * Size definition, which is used to generate the <code>BufferedImage</code> object.
	 */
	private Dimension size;
	/**
	 *  Image object, contains generated image data.
	 */
	private BufferedImage image;
	
	/**
	 * Constructs a new <code>ImageCanvas</code> and initializes the <code>BufferedImage</code> object with the 
	 * specified size. Sets the <code>ColorModel</code> to <code>TYPE_INT_RGB</code>.
	 * 
	 * @param size	The specified size of the image in pixels.
	 */
	public ImageCanvas(final Dimension size) {
		this.size = size;
		image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * Constructs a new <code>ImageCanvas</code> and initializes the <code>BufferedImage</code> object with the 
	 * specified width and height. Sets the <code>ColorModel</code> to <code>TYPE_INT_RGB</code>.
	 * 
	 * @param width		The specified width of the image in pixels.
	 * @param height	The specified height of the image in pixels.
	 */
	public ImageCanvas(final int width, final int height) {
		this(new Dimension(width, height));
	}
	
	/**
	 * @return	The <code>BufferedImage</code> of this <code>ImageCanvas</code>.
	 */
	public BufferedImage getImage() {
		return image;
	}

	/**
	 * Changes the size of the <code>ImageCanvas</code> object by modifying the classes
	 * size parameter and recreating the <code>BufferedImage</code> object.
	 * 
	 * @param size	The specified new size of the image in pixels.
	 */
	public void setSize(final Dimension size) {
		this.size = size;
		image = new BufferedImage(size.width, size.height, 
				BufferedImage.TYPE_INT_RGB);
	}

	/**
	 * Generates a black background and a red diagonal line. The data is written into the raster of the classes 
	 * <code>BufferedImage</code> and then painted.
	 * 
	 * @param g	The specified Graphics context.
	 */
	public void paint(final Graphics g) {
		super.paint(g);
		final WritableRaster raster = image.getRaster();
		final ColorModel colorModel = image.getColorModel();
		
		final Object dataRed = colorModel.getDataElements(new float[] {0f, 0.0f, 0.5f}, 0, null);
		
		final Object dataBlack = colorModel.getDataElements(Color.black.getRGB(),
				null);
		for (int x = 0; x < size.width; x++) {
			for (int y = 0; y < size.height; y++) {
				raster.setDataElements(x, y, dataRed);
//				raster.setPixel(x, y, 0<<16 | 255<<8 | 0 );
				//0<<16 | 255<<8 | 0 
				
			}
		}
		int x = 0;
		for (int y = 0; y < size.height; y++) {
			raster.setDataElements(x, y, dataRed);
			x++;
			if (x >= size.width) {
				break;
			}
		}
		g.drawImage(image, 0, 0, null);
	}
}
