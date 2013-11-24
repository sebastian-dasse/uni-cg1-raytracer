package raytracer.tests.graphical;

import java.awt.Dimension;

import raytracer.Raytracer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.Geometry;
import raytracer.ui.ShowImage;

/**
 * TODO DOK IT ALL!
 * 
 * @author 
 *
 */
public final class Tests {

	/**
	 * @return
	 */
	public static Raytracer testA() {
		final World world = Factory.buildWorld(new double[] { 0, 0, 0 });
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElement(
				Factory.buildPlane(new double[][] { { 0, -1, 0 }, { 0, 1, 0 },
						{ 0, 1, 0 } })
		);
		return new Raytracer(world, camera, new Dimension(800, 600));
	}
	
	/**
	 * @return
	 */
	public static Raytracer testB() {
		final World world = Factory.buildWorld(new double[] { 0, 0, 0 });
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElement(
				Factory.buildSphere(new double[][] { { 0, 0, -3 }, { 0.5 },
						{ 1, 0, 0 } })
		);
		return new Raytracer(world, camera, new Dimension(800, 600));
	}
	
	/**
	 * @return
	 */
	public static Raytracer testC() {
		final World world = Factory.buildWorld(new double[] { 0, 0, 0 });
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 3, 3, 3 }, { -3, -3, -3 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElement(
				Factory.buildAxisAlignedBox(new double[][] { { -0.5, 0, -0.5 }, { 0.5, 1, 0.5 },
						{ 0, 0, 1 } })
		);
		return new Raytracer(world, camera, new Dimension(800, 600));
	}
	
	/**
	 * @return
	 */
	public static Raytracer testD() {
		final World world = Factory.buildWorld(new double[] { 0, 0, 0 });
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElement(
				Factory.buildTriangle(new double[][] { { -0.5, 0.5, -3 }, { 0.5, 0.5, -3 }, { 0.5, -0.5, -3 },
						{ 1, 0, 1 } })
		);
		return new Raytracer(world, camera, new Dimension(800, 600));
	}
	
	/**
	 * @return
	 */
	public static Raytracer testE() {
		final World world = Factory.buildWorld(new double[] { 0, 0, 0 });
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { Math.PI / 4 } });
//		final Camera camera = Factory.buildOrthographicCamera(new double[][] { // TODO REMOVE
//				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { 3 } });
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
	 * @return
	 */
	public static Raytracer testF() {
		final World world = Factory.buildWorld(new double[] { 0, 0, 0 });
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
	
	/**
	 * @param args
	 */
	public static void main(final String[] args) {
//		ShowImage.from(testF());
		final Raytracer[] tracers = new Raytracer[]{testA(), testB(), testC(), testD(), testE(), testF()};
		for (int i = 0; i < tracers.length; i++) {
			ShowImage.from(tracers[i], 150 * i, 75 * i);
		}
	}
}
