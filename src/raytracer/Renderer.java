package raytracer;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import raytracer.camera.Camera;

/**
 * This class represents a ray tracer. It has a world, containing all the objects in a specific scene, a camera and a 
 * screen size. The method <code>trace()</code> returns a <code>BufferedImage</code> of the ray tracer.
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
	 * The world of this raytracer.
	 */
	private final World world;
	/**
	 * The camera of this raytracer.
	 */
	private final Camera cam;
	/**
	 * The screen size of this raytracer.
	 */
	private final Dimension size;
	
	/**
	 * Constructs a new <code>Raytracer</code> with the specified parameters.
	 * 
	 * @param world	The world of this <code>Raytracer</code>.
	 * @param cam	The camera of this <code>Raytracer</code>.
	 * @param size	The screen size of this <code>Raytracer</code>.
	 */
	public Renderer(final World world, final Camera cam, Dimension size) {
		if (world == null || cam == null || size == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		this.world = world;
		this.cam = cam;
		this.size = size;
	}
	
	/**
	 * Constructs a new <code>Raytracer</code> with the specified parameters and a default screen size of 800 x 600.
	 * 
	 * @param world	The world of this <code>Raytracer</code>.
	 * @param cam	The camera of this <code>Raytracer</code>.
	 */
	public Renderer(final World world, final Camera cam) {
		this(world, cam, new Dimension(WIDTH, HEIGHT));
	}
	
	/**
	 * Returns a <code>BufferedImage</code> of a scene as defined by this world, this camera and this size.
	 * 
	 * @return	A <code>BufferedImage</code> of a scene.
	 */
	public BufferedImage render() {
		final BufferedImage image = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_RGB);
		final int nThreads = Runtime.getRuntime().availableProcessors();
		final int hBlock = image.getHeight() / nThreads * 2;
		final int wBlock = image.getWidth() / nThreads * 2;
		final int virtualHeight = hBlock / 2 * nThreads;
		final int virtualWidth = wBlock / 2 * nThreads;
		long t1 = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(nThreads);
		for (int y = 0; y < virtualHeight; y+= hBlock ) {
			for (int x = 0; x < virtualWidth; x+= wBlock) {
				Runnable worker = new Thread(new RenderTask(x, y, nThreads, size, world, cam, image));
				executor.execute(worker);
			}
		}
		executor.shutdown();
		try {
			executor.awaitTermination(60, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			System.err.println("Rendering took too long");
		}
		System.out.println(System.currentTimeMillis() - t1);
		return image;
	}
	
	public Dimension getSize() {
		return size;
	}
	
}
