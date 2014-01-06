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
	private final int y;
	
	public RenderTask(final int y, final Dimension size, 
			final World world, final Camera cam,  final BufferedImage image, final int recursion) {
		this.image = image;
		this.cam = cam;
		this.world = world;
		this.size = size;
		this.recursion = recursion;
		this.y = y;
	}
	@Override
	public void run() {
		final WritableRaster raster = image.getRaster();
		final ColorModel colorModel = image.getColorModel();
		for (int x = 0; x < size.width; x++) {		
			final Ray ray = cam.rayFor(size.width, size.height, x, size.height - y);
			raster.setDataElements(x,y,Util.dataElementsFromColor(new Tracer(recursion).trace(ray, world), colorModel));
		}
	}
}
