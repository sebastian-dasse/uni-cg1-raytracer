package raytracer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
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
		System.out.println("Rendering...");
		final BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		final int nThreads = Runtime.getRuntime().availableProcessors();
		long startTime = System.currentTimeMillis();
		final ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		final int interval = nThreads;
		
		for (int y = 0; y < size.height; y+= interval) {
			
			final Runnable worker = new Thread(new RenderTask(new RenderTaskParameter(y, interval, size, world,
					cam, image, recursion)));
			executor.execute(worker);
		
		}
		
		executor.shutdown();
		try {
			executor.awaitTermination(MAX_RENDER_TIME_MINUTES, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			System.err.println("Thread was interrupted.");
		}
		final long timeMillis = System.currentTimeMillis() - startTime;
		final int timeSek = (int)(timeMillis / 1000);
		System.out.printf("Done rendering after %02d min %02d sec %02d msec%n", timeSek / 60, timeSek % 60, timeMillis % 1000);
		return image;
	}
	
	public Dimension getSize() {
		return size;
	}
	
}
