package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Raytracer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.Geometry;
import raytracer.light.PointLight;
import raytracer.material.PhongMaterial;
import raytracer.math.Point3;
import raytracer.ui.ShowImage;

public class DemoScene2 {
	
	public static void main(String[] args) {
		final Raytracer[] tracers = new Raytracer[]{
				scene1()
			};
			for (int i = 0; i < tracers.length; i++) {
				ShowImage.from(tracers[i], 50 * i, 25 * i);
			}
	}
	
	private static Raytracer scene1() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.1, 0.1, 0.1} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 1, 1, 4 }, { -1, -1, -4 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64)),
				Factory.buildSphere(new double[][] { 
						{ 0, 1, 0 }, { 0.5 } }, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64))
//				Factory.buildAxisAlignedBox(new double[][] { 
//						{ 0.5, 0.5, 0.5 }, { 1.5, 1.5, 1.5 } }, new PhongMaterial(new Color(0.3, 1, 0.3), new Color(1, 1, 1), 64)),
			}
		);
		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(4, 4, 4)));
		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(-5, 5, 5)));
//		world.addLight(new PointLight(new Color(0.3, 0.3, 0.3), new Point3(-4, 4, 4)));
		return new Raytracer(world, camera);
	}
}
