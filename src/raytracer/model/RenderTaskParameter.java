package raytracer.model;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import raytracer.World;
import raytracer.camera.Camera;

public class RenderTaskParameter {
	public int yStartOffset;
	public int yEndOffset;
	public Dimension screenSize;
	public World world;
	public Camera cam;
	public BufferedImage image;
	public int recursion;

	public RenderTaskParameter(int yStartOffset, int yEndOffset, Dimension screenSize,
			World world, Camera cam, BufferedImage image, int recursion) {
		this.yStartOffset = yStartOffset;
		this.yEndOffset = yEndOffset;
		this.screenSize = screenSize;
		this.world = world;
		this.cam = cam;
		this.image = image;
		this.recursion = recursion;
	}
}