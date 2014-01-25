package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Constants;
import raytracer.Renderer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.AxisAlignedBox;
import raytracer.geometry.Geometry;
import raytracer.geometry.Node;
import raytracer.light.SpotLight;
import raytracer.material.LambertMaterial;
import raytracer.math.Point3;
import raytracer.math.Transform;
import raytracer.math.Vector3;
import raytracer.ui.ShowImage;

public class TestDemoScene {
	
	public static void main(String[] args) {
		
		final Renderer[] tracers = new Renderer[]{
				scene1()
		};
		for (int i = 0; i < tracers.length; i++) {
			ShowImage.from(tracers[i], 50 * i, 25 * i);
		}
	}
	
	private static Renderer scene1() {
		
//		Node parent = new Node(new Transform());
//		Node child1 = new Node(new Sphere(new Point3(0, 0, 0), 1, new SingleColorMaterial(new Color(1, 0, 0))),
//				new Transform().scale(1, 2, 0.5));
//		Node child2 = new Node(new Sphere(new Point3(0, 0, 0), 1, new SingleColorMaterial(new Color(0, 1, 0))),
//				new Transform().scale(2, 1, 1).translate(1, 0, 0));
//		parent.add(child1);
//		parent.add(child2);
		
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.5, 0.5, 0.5} }, Constants.INDEX_OF_REFRACTION_VACUUM);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
//				new Node(new Sphere(new Point3(0, 0, 0), 1, new LambertMaterial(new Color(1, 0, 0))),
////				new Node(new Sphere(new Point3(0, 0, 0), 1, new PhongMaterial(new Color(1, 0, 0), new Color(0, 0, 0), 20)), 
////				new Node(new Sphere(new Point3(0, 0, 0), 1, new SingleColorMaterial(new Color(1, 0, 0))),
//						new Transform().scale(1, 2, 0.5))
				
				new Node(new AxisAlignedBox(new Point3(-0.5, -0.5, -0.5), new Point3(0.5, 0.5, 0.5), 
//						new SingleColorMaterial(new Color(1, 0, 0))), 
						new LambertMaterial(new Color(1, 1, 1))), 
						new Transform().rotateX(Math.toRadians(33)))
				}
		
		);
//		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(4, 4, 4)));
//		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(-5, 5, 5)));
//		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(4, 4, 4)));
//		world.addLight(new DirectionalLight(new Color(0.6, 0.6, 0.6), new Vector3(-10, -10, -40)));
		world.addLight(new SpotLight(new Color(1, 1, 1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI / 14.0));
		return new Renderer(world, camera, 10);
	}
}
