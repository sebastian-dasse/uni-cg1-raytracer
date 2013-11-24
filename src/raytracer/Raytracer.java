package raytracer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import raytracer.camera.Camera;
import raytracer.geometry.Hit;

/**
 * TODO DOK IT ALL!
 * 
 * @author 
 *
 */
public class Raytracer {
	private final World world;
	private final Camera cam;
	private final Dimension size;
	
	public Raytracer(final World world, final Camera cam, Dimension size) {
		if (world == null || cam == null || size == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.world = world;
		this.cam = cam;
		this.size = size;
	}
	
	public BufferedImage trace() {
		final BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		final WritableRaster raster = image.getRaster();
		final ColorModel colorModel = image.getColorModel();
		final Object backgroundColor = dataElementsFromColor(world.backgroundColor, colorModel);

		for (int x = 0; x < image.getWidth()-1; x++) {
			for (int y = 0; y < image.getHeight()-1; y++) {
				final Ray ray = cam.rayFor(size.width, size.height, x, y);
				final Hit hit = world.hit(ray);
				if (hit == null) {
					raster.setDataElements(x, y, backgroundColor);
				} else {
					raster.setDataElements(x, y, dataElementsFromColor(hit.geo.color, colorModel));
				}
			}
		}	
		return image;
	}
	
	public Dimension getSize() {
		return size;
	}
	
	/**
	 * 
	 * 
	 * @param color
	 * @param colorModel
	 * @return
	 */
	private static Object dataElementsFromColor(final Color color, final ColorModel colorModel) {
		return colorModel.getDataElements(new float[] {
					(float) color.r,
					(float) color.g,
					(float) color.b
				}, 0, null);
	}
}
