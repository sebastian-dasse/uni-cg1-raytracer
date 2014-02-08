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
import raytracer.texture.SingleColorTexture;
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
		world.addElements(
		new Node(
//				new AxisAlignedBox(new SingleColorMaterial(new SingleColorTexture(new Color(1,1,1)))), 
//				new Transform()
//		//ready transformed Box
//				.rotateX(Math.PI/1.17)
//				.rotateY(Math.PI/0.2) 
//				.rotateZ(Math.PI/1.15) 
//				.scale(1.0, 0.26, 3.7)
		//ready transformed Sphere
//		new Node(
//				new Sphere(new SingleColorMaterial(new SingleColorTexture(new Color(0,1,1)))), 
//				new Sphere(new LambertMaterial(new SingleColorTexture(new Color(0,1,1)))), 
				new Sphere(new PhongMaterial(
						new SingleColorTexture(new Color(0,0,1)), 
						new SingleColorTexture(new Color(1,0,0)), 
						64)),
				new Transform()
				
//				.rotateY(Math.PI/0.2)
//				.rotateZ(Math.PI/0.9)
//				.rotateX(Math.PI/1.15)
//				.scale(2.0, 0.5, 2.0)
				
		));
		
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(4, 4, 4), false));
		return new Renderer(world, camera, 10);
	}
	
}
