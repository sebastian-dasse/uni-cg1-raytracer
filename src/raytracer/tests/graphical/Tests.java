package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Renderer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.Geometry;
import raytracer.material.SingleColorMaterial;
import raytracer.ui.ShowImage;

/**
 * Generates and displays <code>Raytracer</code> objects according to the parameters defined by 
 * our professor. 
 * 
 * @author Simon Lischka
 *
 */
public final class Tests {
	
	/**
	 * Creates a green plane with a perspective camera.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	public static Renderer testA() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0, 0, 0} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElement(
				Factory.buildPlane(new double[][] { { 0, -1, 0 }, { 0, 1, 0 } },
						new SingleColorMaterial(new Color(0, 1, 0)))
		);
		return new Renderer(world, camera);
	}
	
	/**
	 * Creates a red sphere with a perspective camera.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	public static Renderer testB() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0, 0, 0} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElement(
				Factory.buildSphere(new double[][] { { 0, 0, -3 }, { 0.5 } },
						new SingleColorMaterial(new Color(1, 0, 0)))
		);
		return new Renderer(world, camera);
	}
	
	/**
	 * Creates a blue axis aligned box with a perspective camera.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	public static Renderer testC() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0, 0, 0} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 3, 3, 3 }, { -3, -3, -3 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElement(
				Factory.buildAxisAlignedBox(new double[][] { { -0.5, 0, -0.5 }, { 0.5, 1, 0.5 } },
						new SingleColorMaterial(new Color(0, 0, 1)))
		);
		return new Renderer(world, camera);
	}
	
	/**
	 * Creates a magenta triangle with a perspective camera.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	public static Renderer testD() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0, 0, 0} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElement(
				Factory.buildTriangle(new double[][] { { -0.5, 0.5, -3 }, { 0.5, 0.5, -3 }, { 0.5, -0.5, -3 }, 
						{ 1, 1, 1 }, { 1, 1, 1 }, { 1, 1, 1 } }, 
						new SingleColorMaterial(new Color(1, 0, 1)))
		);
		return new Renderer(world, camera);
	}
	
	/**
	 * Creates two red circles with a perspective camera.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	public static Renderer testE() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0, 0, 0} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { Math.PI / 4 } });
		world.addElements(new Geometry[] {
				Factory.buildSphere(new double[][] { { -1, 0, -3 }, { 0.5 } },
						new SingleColorMaterial(new Color(1, 0, 0))), 
				Factory.buildSphere(new double[][] { { 1, 0, -6 }, { 0.5 } },
						new SingleColorMaterial(new Color(1, 0, 0)))
			}
		);
		return new Renderer(world, camera);
	}
	
	/**
	 * Creates two red circles with an orthographic camera.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	public static Renderer testF() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0, 0, 0} }, 1);
		final Camera camera = Factory.buildOrthographicCamera(new double[][] {
				{ 0, 0, 0 }, { 0, 0, -1 }, { 0, 1, 0 }, { 3 } });
		world.addElements(new Geometry[] {
				Factory.buildSphere(new double[][] { { -1, 0, -3 }, { 0.5 } },
						new SingleColorMaterial(new Color(1, 0, 0))), 
				Factory.buildSphere(new double[][] { { 1, 0, -6 }, { 0.5 } },
						new SingleColorMaterial(new Color(1, 0, 0)))
			}
		);
		return new Renderer(world, camera);
	}
	
	/**
	 * Shows all the test objects in individual <code>JFrame</code> windows.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		final Renderer[] tracers = new Renderer[]{
			testA(),
			testB(),
			testC(), 
			testD(),
			testE(), 
			testF()
		};
		for (int i = 0; i < tracers.length; i++) {
			ShowImage.from(tracers[i], 50 * i, 25 * i);
		}
	}
}
