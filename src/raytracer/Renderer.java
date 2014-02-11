package raytracer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.DataBuffer;
import java.awt.image.DataBufferInt;
import java.awt.image.PixelGrabber;
import java.awt.image.Raster;
import java.awt.image.SinglePixelPackedSampleModel;
import java.awt.image.WritableRaster;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import raytracer.camera.Camera;
import raytracer.model.RenderTaskParameter;

/**
 * This class represents a renderer. It has a world, containing all the objects in a specific scene, a camera, a screen 
 * size and a field for the depth of recursion . The method <code>render()</code> returns a <code>BufferedImage</code>.
 * 
 * @author Simon Lischka
 *
 */
public class Renderer {
	/**
	 * The default screen width of 800 px.
	 */
	public static final int WIDTH = 800;
	/**
	 * The default screen height of 600 px.
	 */
	public static final int HEIGHT = 600;	
	/**
	 * The maximum render time in minutes
	 */
	public static final int MAX_RENDER_TIME_MINUTES = 200;
	
	/**
	 * The world of this renderer.
	 */
	private final World world;
	/**
	 * The camera of this renderer.
	 */
	private final Camera cam;
	/**
	 * The screen size of this renderer.
	 */
	private final Dimension size;
	/**
	 * The depth of recursion for recursive raytracing.
	 */
	private final int recursion;
	
	/**
	 * Constructs a new <code>Renderer</code> with the specified parameters.
	 * 
	 * @param world		The world of the <code>Renderer</code>.
	 * @param cam		The camera of the <code>Renderer</code>.
	 * @param size		The screen size of the <code>Renderer</code>.
	 * @param recursion	The depth of recursion of the <code>Renderer</code>.
	 */
	public Renderer(final World world, final Camera cam, final Dimension size, final int recursion) {
		if (world == null || cam == null || size == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.world = world;
		this.cam = cam;
		this.size = size;
		this.recursion = recursion;
	}
	
	/**
	 * Constructs a new <code>Renderer</code> with the specified parameters and a default screen size of 800 x 600.
	 * 
	 * @param world		The world of the <code>Renderer</code>.
	 * @param cam		The camera of the <code>Renderer</code>.
	 * @param recursion	The depth of recursion of the <code>Renderer</code>.
	 */
	public Renderer(final World world, final Camera cam, final int recursion) {
		this(world, cam, new Dimension(WIDTH, HEIGHT), recursion);
	}
	
	/**
	 * Constructs a new <code>Renderer</code> with the specified parameters and a default screen size of 800 x 600 as 
	 * well as a default depth of recursion of 2.
	 * 
	 * @param world		The world of the <code>Renderer</code>.
	 * @param cam		The camera of the <code>Renderer</code>.
	 */
	public Renderer(final World world, final Camera cam) {
		this(world, cam, new Dimension(WIDTH, HEIGHT), 2);
	}
	
	/**
	 * Returns a <code>BufferedImage</code> of a scene as defined by this world, this camera and this size.
	 * 
	 * @return	A <code>BufferedImage</code> of a scene.
	 */
	public BufferedImage render() {
		final BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		
		final int nThreads = Runtime.getRuntime().availableProcessors();
		
		final ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		final int interval = nThreads;
		
		final ProgressMonitor progressMonitor = new ProgressMonitor("Rendering", size.height, 5);
		
		for (int y = 0; y < size.height; y+= interval) {

			final Runnable worker = new Thread(new RenderTask(
					new RenderTaskParameter(y, y + interval, size, world, cam,
							image, recursion), progressMonitor));

			executor.execute(worker);
		
		}
		
		executor.shutdown();
		try {
			executor.awaitTermination(MAX_RENDER_TIME_MINUTES, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			System.err.println("Thread was interrupted.");
		}
		
		final int[] pixels = new int[size.width * size.height]; 
		// source, start width, start height, output array, line size begin, line size end
		PixelGrabber grabber = new PixelGrabber(image, 0, 0, size.width / 2, size.height, pixels, 0, size.width);
		try {
			grabber.grabPixels();
		} catch (InterruptedException e1) {
			System.err.println("Error with your biatch");
		}
		System.out.println(pixels[1113]);
		
		final DataBufferInt dataBuffer = new DataBufferInt(pixels, size.width * size.height);
		final int[] bitMask = new int[] {
				0xff0000,
				0xff00, 
				0xff,
				0xff000000
		};
		final SinglePixelPackedSampleModel sampleModel = new SinglePixelPackedSampleModel(
				DataBuffer.TYPE_INT, size.width, size.height, bitMask);
		
		final WritableRaster raster = Raster.createWritableRaster(sampleModel, dataBuffer, null);
		
		final BufferedImage img = new BufferedImage(ColorModel.getRGBdefault(), raster, false, null);
		
		
		return img;
	}
	
	public Dimension getSize() {
		return size;
	}
	
}
