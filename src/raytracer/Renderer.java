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

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;

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
		
		final ExecutorService executor = Executors.newFixedThreadPool(nThreads * 20);
		
		final ProgressMonitor progressMonitor = new ProgressMonitor("Rendering", size.height, 5);
		
		final RenderTaskParameter taskPar = new RenderTaskParameter(0, size.height, size, world, cam, image, recursion);
		taskPar.splitBy(nThreads * 20);
		
		while (taskPar.hasNextChild()) {
			final Runnable worker = new Thread(new RenderTask(taskPar.getNextChild(), progressMonitor));
			executor.execute(worker);
		}
		
		executor.shutdown();
		try {
			executor.awaitTermination(MAX_RENDER_TIME_MINUTES, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			System.err.println("Thread was interrupted.");
		}
		
		final int numberOfFragments = 4;
		
		final int fragmentHeight = size.height / numberOfFragments;
		
		final int[] pixels1 = new int[size.width * fragmentHeight]; 
		final int[] pixels2 = new int[size.width * fragmentHeight]; 
		final int[] pixels3 = new int[size.width * fragmentHeight]; 
		final int[] pixels4 = new int[size.width * fragmentHeight]; 

		PixelGrabber grabber = new PixelGrabber(image, 0, 0, size.width, size.height, pixels1, 0, size.width);
		
		PixelGrabber grabber1 = new PixelGrabber(image, 0, 0, size.width, fragmentHeight, pixels1, 0, size.width);
		PixelGrabber grabber2 = new PixelGrabber(image, 0, fragmentHeight, size.width, fragmentHeight, pixels2, 0, size.width);
		PixelGrabber grabber3 = new PixelGrabber(image, 0, fragmentHeight * 2, size.width, fragmentHeight, pixels3, 0, size.width);
		PixelGrabber grabber4 = new PixelGrabber(image, 0, fragmentHeight * 3, size.width, fragmentHeight, pixels4, 0, size.width);
		
		try {
			grabber1.grabPixels();
			grabber2.grabPixels();
			grabber3.grabPixels();
			grabber4.grabPixels();
		} catch (InterruptedException e1) {
			System.err.println("Error with your biatch");
		}
		
		final int[] composedPixels = new int[size.width * size.height];
		//void java.lang.System.arraycopy(Object src, int srcPos, Object dest, int destPos, int length)
		System.arraycopy(pixels1, 0, composedPixels, 0, size.width * fragmentHeight);
		System.arraycopy(pixels2, 0, composedPixels, fragmentHeight * size.width * 1, size.width * fragmentHeight);
		System.arraycopy(pixels3, 0, composedPixels, fragmentHeight * size.width * 2, size.width * fragmentHeight);
		System.arraycopy(pixels4, 0, composedPixels, fragmentHeight * size.width * 3, size.width * fragmentHeight);
		
		final DataBufferInt dataBuffer = new DataBufferInt(composedPixels, size.width * size.height);

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
		
		BufferedImage img1 = img.getSubimage(0, 0, size.width, fragmentHeight);
		BufferedImage img2 = img.getSubimage(0, fragmentHeight, size.width, fragmentHeight);
		BufferedImage img3 = img.getSubimage(0, fragmentHeight * 2, size.width, fragmentHeight);
		BufferedImage img4 = img.getSubimage(0, fragmentHeight * 3, size.width, fragmentHeight);
		final BufferedImage imgc = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
		imgc.setData(img2.getRaster().getParent());
		
		
		return imgc;
	}
	
	public Dimension getSize() {
		return size;
	}
	
}
