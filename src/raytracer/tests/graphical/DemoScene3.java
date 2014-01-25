package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Renderer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.AxisAlignedBox;
import raytracer.geometry.Geometry;
import raytracer.geometry.Node;
import raytracer.geometry.Plane;
import raytracer.geometry.Sphere;
import raytracer.light.PointLight;
import raytracer.light.SpotLight;
import raytracer.material.LambertMaterial;
import raytracer.material.PhongMaterial;
import raytracer.material.SingleColorMaterial;
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
		Node first = new Node(
				new Sphere(new Point3(1, 3, 1), 0.5, new LambertMaterial(new Color(0, 1, 0))), 
				new Transform().scale(2.0, 1.0, 2.0));
		//world.addElement(new Sphere(new Point3(0, 1, 0), 0.5, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64)));
		//world.addElement(new Plane(new Point3( 0, 0, 0), new Normal3(0, 1, 0), new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64)));
//		world.addElement(new Node(
//				new Sphere(new Point3(1, 3, 1), 0.5, new LambertMaterial(new Color(0, 1, 0))), 
//				new Transform().scale(2.0, 1.0, 2.0)));
		world.addElement(new Node(
				new AxisAlignedBox(new Point3(-0.5, 0, -0.5), new Point3(0.5, 1, 0.5), new LambertMaterial(new Color(0, 1, 0))), 
				new Transform().rotateX(8)));
		
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(4, 4, 4)));
		return new Renderer(world, camera, 10);
	}
	
}
