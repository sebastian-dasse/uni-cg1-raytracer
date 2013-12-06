package raytracer.tests.graphical;

import java.awt.Dimension;

import raytracer.Raytracer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.Geometry;
import raytracer.ui.ShowImage;

public final class DemoScene {
	public static final Dimension size = new Dimension(800, 600);
	
	public static void main(String[] args) {
		ShowImage.from(scene());
	}
	
	public static Raytracer scene() {
		final World world = Factory.buildWorld(new double[] { 0, 0, 0 });
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] { { 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { { 0, 0, 0 }, { 0, 1, 0}, { 1, 0, 0 } }),
				Factory.buildSphere(new double[][] { { 1, 1, 1 }, { 0.5 }, { 0, 1, 0 } }),
				Factory.buildAxisAlignedBox(new double[][] { { -1.5, 0.5, 0.5 }, { -0.5, 1.5, 1.5 }, { 0, 0, 1 } }), 
				Factory.buildTriangle(new double[][] { { 0, 0, -1 }, { 1, 0, -1 }, { 1, 1, -1 }, {1, 1, 0} })
			}
		);
		return new Raytracer(world, camera, size);
	}
}
