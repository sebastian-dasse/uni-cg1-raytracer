package raytracer.model;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;

import raytracer.World;
import raytracer.camera.Camera;

public class RenderTaskParameter {
	public int yStartOffset;
	public int yEndOffset;
	public Dimension screenSize;
	public World world;
	public Camera cam;
	public Raster raster;
	public int recursion;

	public RenderTaskParameter(int yStartOffset, int yEndOffset, Dimension screenSize,
			World world, Camera cam, Raster raster, int recursion) {
		this.yStartOffset = yStartOffset;
		this.yEndOffset = yEndOffset;
		this.screenSize = screenSize;
		this.world = world;
		this.cam = cam;
		this.raster = raster;
		this.recursion = recursion;
	}
	
	public RenderTaskParameter(final World world, final Camera cam, final Dimension size, final int recursion) {
		
	}
}