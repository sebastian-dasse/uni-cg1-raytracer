package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Renderer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.Geometry;
import raytracer.geometry.Plane;
import raytracer.geometry.Sphere;
import raytracer.light.SpotLight;
import raytracer.material.PhongMaterial;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Transform;
import raytracer.math.Vector3;
import raytracer.ui.ShowImage;

public class DemoScene3 {

public static void main(String[] args) {
		
		final Renderer[] tracers = new Renderer[]{
				scene1(),
		};
		for (int i = 0; i < tracers.length; i++) {
			ShowImage.from(tracers[i], 50 * i, 25 * i);
		}
	}
	
	private static Renderer scene1() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.1, 0.1, 0.1} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		
		world.addElement(new Sphere(new Point3(0, 1, 0), 0.5, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64)));
		world.addElement(new Plane(new Point3( 0, 0, 0), new Normal3(0, 1, 0), new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64)));
		world.addElement(new Transform().scale(3.0, 1.0, 2.0));
		
		world.addLight(new SpotLight(new Color(1, 1, 1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI / 14.0));
		return new Renderer(world, camera, 10);
	}
	
}
