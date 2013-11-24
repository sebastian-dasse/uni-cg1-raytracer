package raytracer.tests.graphical;

import java.awt.Dimension;

import raytracer.Raytracer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.Geometry;
import raytracer.ui.ShowImage;

public class Tests {

	public static Raytracer testA() {
		World world = Factory.buildWorld(new double[] { 0, 0, 0 });
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElement(
				Factory.buildPlane(new double[][] { { 0, -1, 0 }, { 0, 1, 0 },
						{ 0, 1, 0 } })
		);
		return new Raytracer(world, camera, new Dimension(800, 600));
	}
	
	/**
	 * Test fails
	 */
	public static Raytracer testB() {
		World world = Factory.buildWorld(new double[] { 0, 0, 0 });
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElement(
				Factory.buildSphere(new double[][] { { 0, 0, -3 }, { 0.5 },
						{ 1, 0, 0 } })
		);
		return new Raytracer(world, camera, new Dimension(800, 600));
	}

	/**
	 * Test fails
	 */
	public static Raytracer testC() {
		World world = Factory.buildWorld(new double[] { 0, 0, 0 });
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 3, 3, 3 }, { -3, -3, -3 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElement(
				Factory.buildAxisAlignedBox(new double[][] { { -0.5, 0, -0.5 }, { 0.5, 1, 0.5 },
						{ 0, 0, 1 } })
		);
		return new Raytracer(world, camera, new Dimension(800, 600));
	}

	/**
	 * Test fails
	 */
	public static Raytracer testD() {
		World world = Factory.buildWorld(new double[] { 0, 0, 0 });
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElement(
				Factory.buildTriangle(new double[][] { { -0.5, 0.5, -3 }, { 0.5, 0.5, -3 }, { 0.5, -0.5, -3 },
						{ 1, 0, 1 } })
		);
		return new Raytracer(world, camera, new Dimension(800, 600));
	}
	
	/**
	 * Test fails
	 */
	public static Raytracer testE() {
		World world = Factory.buildWorld(new double[] { 0, 0, 0 });
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElements(new Geometry[] {
				Factory.buildSphere(new double[][] { { -1, 0, -3 }, { 0.5 },
						{ 1, 0, 0 } }),
				Factory.buildSphere(new double[][] { { 1, 0, -6 }, { 0.5 },
						{ 1, 0, 0 } })
			}
		);
		return new Raytracer(world, camera, new Dimension(800, 600));
	}
	
	/**
	 * Test fails
	 */
	public static Raytracer testF() {
		World world = Factory.buildWorld(new double[] { 0, 0, 0 });
		final Camera camera = Factory.buildOrthographicCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { 3 } });
		world.addElements(new Geometry[] {
				Factory.buildSphere(new double[][] { { -1, 0, -3 }, { 0.5 },
						{ 1, 0, 0 } }),
				Factory.buildSphere(new double[][] { { 1, 0, -6 }, { 0.5 },
						{ 1, 0, 0 } })
			}
		);
		return new Raytracer(world, camera, new Dimension(800, 600));
	}
	
	public static void main(String[] args) {
		ShowImage.from(testF());
	}
}
