package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Constants;
import raytracer.Renderer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.Geometry;
import raytracer.light.DirectionalLight;
import raytracer.light.Light;
import raytracer.light.PointLight;
import raytracer.light.SpotLight;
import raytracer.material.LambertMaterial;
import raytracer.material.PhongMaterial;
import raytracer.material.ReflectiveMaterial;
import raytracer.material.TransparentMaterial;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.ui.ShowImage;

/**
 * Generates and displays <code>Raytracer</code> objects for demo scenes as demanded in task 4.
 * 
 * @author Sebastian Dass&eacute;
 * @author Maxim Novichkov
 *
 */
public final class DemoSceneUE04 {
	
	/**
	 * Shows all the demo scenes in individual <code>JFrame</code> windows.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		final Renderer[] tracers = new Renderer[]{
//			scene1(), 
//			scene2(), 
			scene3()
		};
		for (int i = 0; i < tracers.length; i++) {
			ShowImage.from(tracers[i], 50 * i, 25 * i);
		}
	}
	
	/**
	 * Generates figure 3 as shown in the task sheet.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	public static Renderer scene1() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.25, 0.25, 0.25} }, Constants.INDEX_OF_REFRACTION_AIR_AT_20_DEG);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 8, 8, 8 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
			Factory.buildPlane(new double[][] { 
					{ 0, 0, 0 }, { 0, 1, 0} }, new ReflectiveMaterial(new Color(0.1, 0.1, 0.1), new Color(0, 0, 0), 64, new Color(0.5, 0.5, 0.5))),
			Factory.buildSphere(new double[][] { 
					{ -3, 1, 0 }, { 1 } }, new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))),
			Factory.buildSphere(new double[][] { 
					{ 0, 1, 0 }, { 1 } }, new ReflectiveMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5))),
			Factory.buildSphere(new double[][] { 
					{ 3, 1, 0 }, { 1 } }, new ReflectiveMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 64, new Color(0.5, 0.5, 0.5)))
			}
		);
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(8, 8, 8)));
		return new Renderer(world, camera);
	}
	
	/**
	 * Generates figure 4 as shown in the task sheet.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	private static Renderer scene2() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.25, 0.25, 0.25} }, Constants.INDEX_OF_REFRACTION_AIR_AT_20_DEG);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
			Factory.buildPlane(new double[][] { 
					{ 0, 0, 0 }, { 0, 1, 0} }, new LambertMaterial(new Color(0.8, 0.8, 0.8))),
			Factory.buildAxisAlignedBox(new double[][] { 
					{ -0.5, 0, -0.5 }, { 0.5, 1, 0.5 } }, new LambertMaterial(new Color(1, 0, 0))),
			}
		);
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(8, 8, 0)));
		return new Renderer(world, camera);
	}
	
	/**
	 * Generates figure 5 as shown in the task sheet.
	 * 
	 * @return The <code>Raytracer</code> generated by the test method.
	 */
	private static Renderer scene3() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.1, 0.1, 0.1} }, Constants.INDEX_OF_REFRACTION_VACUUM);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 8, 8, 8 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
			Factory.buildPlane(new double[][] { 
					{ 0, 0, 0 }, { 0, 1, 0} }, new ReflectiveMaterial(new Color(1, 1, 1), new Color(1, 1, 1), 10, new Color(1, 1, 1))),
				
			Factory.buildSphere(new double[][] { 
					{ 0, 1, 0 }, { 0.5 } }, new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 10, new Color(1, 0.5, 0.5))),
			Factory.buildSphere(new double[][] { 
					{ -1.5, 1, 0 }, { 0.5 } }, new ReflectiveMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 10, new Color(1, 0.5, 0.5))),
			Factory.buildSphere(new double[][] { 
					{ 1.5, 1, 0 }, { 0.5 } }, new ReflectiveMaterial(new Color(0, 0, 1), new Color(1, 1, 1), 10, new Color(1, 0.5, 0.5))),
			Factory.buildSphere(new double[][] {
					{ 0, 1, -1.5 }, { 0.5 } }, new ReflectiveMaterial(new Color(0, 1, 1), new Color(1, 1, 1), 10, new Color(1, 0.5, 0.5))),
			Factory.buildSphere(new double[][] { 
					{ -1.5, 1, -1.5 }, { 0.5 } }, new ReflectiveMaterial(new Color(1, 0, 1), new Color(1, 1, 1), 10, new Color(1, 0.5, 0.5))),
			Factory.buildSphere(new double[][] { 
					{ 1.5, 1, -1.5 }, { 0.5 } }, new ReflectiveMaterial(new Color(1, 1, 0), new Color(1, 1, 1), 10, new Color(1, 0.5, 0.5))),
			
			Factory.buildSphere(new double[][] { 
					{ 0, 2, 1.5 }, { 0.5 } }, new TransparentMaterial(Constants.INDEX_OF_REFRACTION_WATER)),
			Factory.buildSphere(new double[][] { 
					{ -1.5, 2, 1.5 }, { 0.5 } }, new TransparentMaterial(Constants.INDEX_OF_REFRACTION_WATER)),
			Factory.buildSphere(new double[][] { 
					{ 1.5, 2, 1.5 }, { 0.5 } }, new TransparentMaterial(Constants.INDEX_OF_REFRACTION_WATER)),
																											
			Factory.buildAxisAlignedBox(new double[][] { 
					{ -0.5, 0, 3 }, { 0.5, 1, 4 } }, new TransparentMaterial(1.3)), 
			
			Factory.buildTriangle(new double[][] { 
					{ 0.7, 0.5, 3 }, { 1.3, 0.5, 3 }, { 0.7, 0.5, 4 }, 
					{ 0, 1, 0 }, { 0, 1, 0 }, { 0, 1, 0 } }, new PhongMaterial(new Color(0, 1, 0), new Color(0, 1, 0), 20))
			}
		);
		world.addLights(new Light[] {
			new SpotLight(new Color(0.3, 0.3, 0.3), new Point3(0, 5, -10), new Vector3(0, -1, 0), Math.PI / 8.0, true), 
			new PointLight(new Color(0.3, 0.3, 0.3), new Point3(5, 5, -10), true), 
			new DirectionalLight(new Color(0.3, 0.3, 0.3), new Vector3(1, -1, 0))
		});
		return new Renderer(world, camera, 10);
	}
}
