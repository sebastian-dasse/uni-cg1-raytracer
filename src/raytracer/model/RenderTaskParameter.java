package raytracer.model;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import raytracer.World;
import raytracer.camera.Camera;

public class RenderTaskParameter {
	public int yStart;
	public int interval;
	public Dimension size;
	public World world;
	public Camera cam;
	public BufferedImage image;
	public int recursion;

	public RenderTaskParameter(int yStart, int interval, Dimension size,
			World world, Camera cam, BufferedImage image, int recursion) {
		this.yStart = yStart;
		this.interval = interval;
		this.size = size;
		this.world = world;
		this.cam = cam;
		this.image = image;
		this.recursion = recursion;
	}
}