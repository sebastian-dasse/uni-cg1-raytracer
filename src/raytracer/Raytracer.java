package raytracer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import raytracer.camera.Camera;
import raytracer.geometry.Hit;

/**
 * This class represents a ray tracer. It has a world, containing all the objects in a specific scene, a camera and a 
 * screen size. The method <code>trace()</code> returns a <code>BufferedImage</code> of the ray tracer.
 * 
 * @author Simon Lischka
 *
 */
public class Raytracer {
	/**
	 * The world of this raytracer.
	 */
	private final World world;
	/**
	 * The camera of this raytracer.
	 */
	private final Camera cam;
	/**
	 * The screen size of this raytracer.
	 */
	private final Dimension size;
	
	/**
	 * Constructs a new <code>Raytracer</code> with the specified parameters.
	 * 
	 * @param world	The world of this <code>Raytracer</code>.
	 * @param cam	The camera of this <code>Raytracer</code>.
	 * @param size	The screen size of this <code>Raytracer</code>.
	 */
	public Raytracer(final World world, final Camera cam, Dimension size) {
		if (world == null || cam == null || size == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.world = world;
		this.cam = cam;
		this.size = size;
	}
	
	/**
	 * Returns a <code>BufferedImage</code> of a scene as defined by this world, this camera and this size.
	 * 
	 * @return	A <code>BufferedImage</code> of a scene.
	 */
	public BufferedImage trace() {
		final BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		final WritableRaster raster = image.getRaster();
		final ColorModel colorModel = image.getColorModel();
		final Object backgroundColor = dataElementsFromColor(world.backgroundColor, colorModel);

		for (int x = 0; x < image.getWidth()-1; x++) {
			for (int y = 0; y < image.getHeight()-1; y++) {
//				final Ray ray = cam.rayFor(size.width, size.height, x, y); // head over heels
				final Ray ray = cam.rayFor(size.width, size.height, x, size.height - y);
				final Hit hit = world.hit(ray);
				if (hit == null) {
					raster.setDataElements(x, y, backgroundColor);
				} else {
//					raster.setDataElements(x, y, dataElementsFromColor(hit.geo.color, colorModel)); // old version
					raster.setDataElements(x, y, dataElementsFromColor(hit.geo.material.colorFor(hit, world), colorModel));
				}
			}
		}	
		return image;
	}
	
	public Dimension getSize() {
		return size;
	}
	
	/**
	 * Returns a data element array representation of a pixel in the specified <code>ColorModel</code> from the specified 
	 * <code>raytracer.Color</code>.
	 * 
	 * @param color			The specified <code>raytracer.Color</code>.
	 * @param colorModel	The specified <code>ColorModel</code>.
	 * @return				An Object which is a primitive data array representation of a pixel.
	 */
	private static Object dataElementsFromColor(final Color color, final ColorModel colorModel) {
		return colorModel.getDataElements(new float[] {
					normalizeColorComponent(color.r),
					normalizeColorComponent(color.g),
					normalizeColorComponent(color.b)
				}, 0, null);
	}
	
	/**
	 * Converts a color component from a specified double value to a normalized float value between 0 and 1 (including).
	 * 
	 * @param colorComponent	The double value to be normalized.
	 * @return					The normalized float value between 0 and 1 (including).
	 */
	private static float normalizeColorComponent(double colorComponent) {
		return (colorComponent > 1) ?  1 : (float) colorComponent;
	}
}
