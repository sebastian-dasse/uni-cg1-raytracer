package raytracer;

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
	private World world;
	private Camera cam;
	
	public Raytracer(final World world, final Camera cam) {
		if (world == null || cam == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.world = world;
		this.cam = cam;
	}
	
	public BufferedImage traceToImage(final int width, final int height) {
		final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		final WritableRaster raster = image.getRaster();
		final ColorModel colorModel = image.getColorModel();
		final Object backgroundColor = dataElementsFromColor(world.backgroundColor, colorModel);

		for (int x = 0; x < image.getWidth()-1; x++) {
			for (int y = 0; y < image.getHeight()-1; y++) {
				final Ray ray = cam.rayFor(width, height, x, y);
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
