package raytracer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import raytracer.camera.Camera;

public class RenderTask implements Runnable {
	private final int xOffset;
	private final int yOffset;
	private final World world;
	private final Camera cam;
	private final BufferedImage image;
	private final Dimension size;
	private final int nThreads;
	
	public RenderTask(final int xOffset, final int yOffset, final int nThreads, final Dimension size, 
			final World world, final Camera cam,  final BufferedImage image) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		this.image = image;
		this.cam = cam;
		this.world = world;
		this.size = size;
		this.nThreads = nThreads;
	}
	@Override
	public void run() {
		final WritableRaster raster = image.getRaster();
		final ColorModel colorModel = image.getColorModel();
		final int hBlock = image.getHeight() / nThreads * 2;
		final int wBlock = image.getWidth() / nThreads * 2;
		
		for (int x = xOffset; x < xOffset + wBlock && x < image.getWidth()-1; x++) {
			for (int y = yOffset; y < yOffset + hBlock && y < image.getHeight()-1; y++) {
				final Ray ray = cam.rayFor(size.width, size.height, x, size.height - y);
				raster.setDataElements(x,y,Util.dataElementsFromColor(new Tracer(1).trace(ray, world), colorModel));
			}
		}
	}
}
