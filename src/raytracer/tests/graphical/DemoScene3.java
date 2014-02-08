package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Renderer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.camera.PerspectiveCamera;
import raytracer.geometry.AxisAlignedBox;
import raytracer.geometry.Geometry;
import raytracer.geometry.Node;
import raytracer.geometry.Plane;
import raytracer.geometry.Sphere;
import raytracer.geometry.Triangle;
import raytracer.light.PointLight;
import raytracer.light.SpotLight;
import raytracer.material.LambertMaterial;
import raytracer.material.PhongMaterial;
import raytracer.material.ReflectiveMaterial;
import raytracer.material.SingleColorMaterial;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Transform;
import raytracer.math.Vector3;
import raytracer.texture.ImageTexture;
import raytracer.texture.SingleColorTexture;
import raytracer.texture.TexCoord2;
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
		final World world = new World(new Color( 0, 0, 0), new Color(0.1, 0.1, 0.1), 1);
		
		final PerspectiveCamera camera = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), 
										Math.PI / 4.0);
		world.addElements(
				
//				new Plane(
//						new ReflectiveMaterial(
//						new SingleColorTexture(new Color(0,0,1)), 
//						new SingleColorTexture(new Color(0,0,1)), 
//						64, 
//						new SingleColorTexture(new Color(0,0,1)))), 
//						new Transform()))
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
//				new Sphere(new PhongMaterial(
//						new SingleColorTexture(new Color(0,0,1)), 
//						new SingleColorTexture(new Color(1,0,0)), 
//						64)))
				
				
//				new Node(
//						new Sphere(new ReflectiveMaterial(
//						new SingleColorTexture(new Color(0,0,1)), 
//						new SingleColorTexture(new Color(1,0,0)), 
//						64, 
//						new SingleColorTexture(new Color(1,0,0)))), 
//						new Transform()
//						.translate(new Point3(-3.5, -1.5, 2.5))),
//						
				new Node(new Sphere(new ReflectiveMaterial(
						new ImageTexture("/raytracer-gottextures"), 
						new SingleColorTexture(new Color(1,0,0)), 
						64, 
						new SingleColorTexture(new Color(1,0,0)))), 
						new Transform()
						.translate(new Point3(0.5, 1.5, 1.5))))
						
//				new Node(new Plane(
//						new LambertMaterial(new SingleColorTexture(new Color(1,0,0)))),
//						(new SingleColorMaterial(new SingleColorTexture(new Color(1,0,0)))),
//						new ReflectiveMaterial(
//								new SingleColorTexture(new Color(0,0,1)), 
//								new SingleColorTexture(new Color(0,1,0)), 
//								10, 
//								new SingleColorTexture(new Color(1,0,0)))), 
//								new Transform()
//								.translate(new Point3(0, 0, 0)
//								)),
								
//				new Node(new Triangle( new Point3(0.7, 0.5, 3), new Point3(1.3, 0.5, 3), new Point3(0.7, 0.5, 4),
//						new Normal3(0, 1, 0), new Normal3(0, 1, 0), new Normal3(0, 1, 0), 
//						new SingleColorMaterial(new SingleColorTexture(new Color(1,0,1))), 
//						new TexCoord2(1,1), 
//						new TexCoord2(1,1), 
//						new TexCoord2(1,1)), 
//				new Transform()
//				.translate(new Point3(1, 2, -1)
//				)))
				;
						
						
				
//				.rotateY(Math.PI/0.2)
//				.rotateZ(Math.PI/0.9)
//				.rotateX(Math.PI/1.15)
//				.scale(2.0, 0.5, 2.0)
				
		
		
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(4, 4, 4), false));
		return new Renderer(world, camera, 10);
	}
	
}
