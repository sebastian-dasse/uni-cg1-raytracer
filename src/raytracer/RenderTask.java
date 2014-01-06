package raytracer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import raytracer.camera.Camera;

public class RenderTask implements Runnable {
	private final World world;
	private final Camera cam;
	private final BufferedImage image;
	private final Dimension size;
	private final int recursion;
	private final int yStart;
	private final int interval;
	
	public RenderTask(final int yStart, final int interval, final Dimension size, 
			final World world, final Camera cam,  final BufferedImage image, final int recursion) {
		this.image = image;
		this.cam = cam;
		this.world = world;
		this.size = size;
		this.recursion = recursion;
		this.yStart = yStart;
		this.interval = interval;
	}
	@Override
	public void run() {
		final WritableRaster raster = image.getRaster();
		final ColorModel colorModel = image.getColorModel();
		for (int y = yStart; y < yStart + interval; y++) {
			for (int x = 0; x < size.width; x++) {		
				final Ray ray = cam.rayFor(size.width, size.height, x, size.height - y);
				raster.setDataElements(x,y,Util.dataElementsFromColor(new Tracer(recursion).trace(ray, world), colorModel));
			}
		}
	}
}
