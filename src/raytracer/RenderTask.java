package raytracer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import raytracer.camera.Camera;

public class RenderTask implements Runnable {
	private final int x;
	private final int y;
	private final int blockSize;
	private final World world;
	private final Camera cam;
	private final BufferedImage image;
	private final Dimension size;
	
	public RenderTask(final int x, final int y, final int blockSize, final Dimension size, 
			final World world, final Camera cam,  final BufferedImage image) {
		this.x = x;
		this.y = y;
		this.blockSize = blockSize;
		this.image = image;
		this.cam = cam;
		this.world = world;
		this.size = size;
	}
	@Override
	public void run() {
		final Ray ray = cam.rayFor(size.width, size.height, x, size.height - y);
		final WritableRaster raster = image.getRaster();
		final ColorModel colorModel = image.getColorModel();
		raster.setDataElements(x,y,Util.dataElementsFromColor(new Tracer(1).trace(ray, world), colorModel));
	}

}
