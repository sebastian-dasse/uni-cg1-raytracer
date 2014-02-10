package raytracer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import raytracer.camera.Camera;
import raytracer.model.RenderTaskParameter;
/**
 * This class represents a Runnable object which can be used by the
 * ExecutorService to generate a thread. It is a fragment of the image to be rendered and
 * is parameterized by the necessary input data and an interval.
 * <br>
 * It is designed to be instantiated
 * in the for-loop of a renderer and has to receive the same interval parameter
 * to work correctly.<br>
 * The RenderTask renders the number of lines specified in the constructor.
 * 
 * @author Simon Lischka
 *
 */
public class RenderTask implements Runnable {
	/**
	 * Reference to the world used by the renderer
	 */
	private final World world;
	/**
	 * Reference to the camera used by the renderer
	 */
	private final Camera cam;
	/**
	 * Reference to the BufferedImage used by the renderer for render output
	 */
	private final BufferedImage image;
	/**
	 * The size of the canvas to be drawn to
	 */
	private final Dimension size;
	/**
	 * The recursion depth of the Tracer Object (e.g. used for reflections)
	 */
	private final int recursion;
	/**
	 * Start position of the fragment that should be rendered
	 */
	private final int yStart;
	/**
	 * End position of the fragment that should be rendered
	 */
	private final int yEnd;
	/**
	 * Creates a new RenderTask with the specified parameters.
	 * @param parameterObject TODO
	 */
	private ProgressMonitor progressMonitor;
	
	public RenderTask(RenderTaskParameter parameterObject, ProgressMonitor progressMonitor) {
		this.image = parameterObject.image;
		this.cam = parameterObject.cam;
		this.world = parameterObject.world;
		this.size = parameterObject.screenSize;
		this.recursion = parameterObject.recursion;
		this.yStart = parameterObject.yStartOffset;
		this.yEnd = parameterObject.yEndOffset;
		this.progressMonitor = progressMonitor;
	}
	@Override
	/**
	 * Renders the given interval, uses a tracer to render recursively.
	 * The method renders the x-Axis completely, only the y-Axis is fragmented to
	 * enable various simultaneous threads.
	 */
	public void run() {
		final WritableRaster raster = image.getRaster();
		final ColorModel colorModel = image.getColorModel();
		for (int y = yStart; y < yEnd; y++) {
			for (int x = 0; x < size.width; x++) {		
				final Ray ray = cam.rayFor(size.width, size.height, x, size.height - y);
				raster.setDataElements(
						x,
						y,
						Util.dataElementsFromColor(
								new Tracer(recursion).trace(ray, world), // Color value
								colorModel
						)
				);
			}
			progressMonitor.showProgress(y);
		}
	}
}
