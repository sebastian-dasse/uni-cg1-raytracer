package raytracer.image;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

/**
 * TODO UPDATE DOK!
 * This class represents the canvas for a diagonal line on black background. It is initialized with size parameters. 
 * <p>
 * The class includes a paint method which is called to display the generated image.
 * It also includes methods to change its size and retrieve the embedded <code>BufferedImage</code> object.
 * 
 * @author Simon Lischka
 */
public final class TracerCanvas extends Canvas {
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
	public TracerCanvas(BufferedImage image) {
		this.image = image;
	}


	/**
	 * @return	The <code>BufferedImage</code> of this <code>ImageCanvas</code>.
	 */
	public BufferedImage getImage() {
		return image;
	}


	/**
	 * Generates a black background and a red diagonal line. The data is written into the raster of the classes 
	 * <code>BufferedImage</code> and then painted.
	 * 
	 * @param g	The specified Graphics context.
	 */
	public void paint(final Graphics g) {
		super.paint(g);
		g.drawImage(image, 0, 0, null);
	}
}
