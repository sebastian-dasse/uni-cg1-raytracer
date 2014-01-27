package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Constants;
import raytracer.Renderer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.AxisAlignedBox;
import raytracer.geometry.Geometry;
import raytracer.geometry.Node;
import raytracer.geometry.ShapeFromFile;
import raytracer.light.PointLight;
import raytracer.material.LambertMaterial;
import raytracer.material.Material;
import raytracer.material.PhongMaterial;
import raytracer.material.ReflectiveMaterial;
import raytracer.math.Point3;
import raytracer.math.Transform;
import raytracer.ui.ShowImage;

public final class TestDemoScene {
	
	public static void main(String[] args) {
		
		final Renderer[] tracers = new Renderer[]{
//				scene1(), 
//				scene2(), 
				scene3()
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
		
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 1 }, {0.0, 0.0, 0.0} }, Constants.INDEX_OF_REFRACTION_VACUUM);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
//				{ 20, 20, 20 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 40.0 } });
		world.addElements(
//				new Node(new Sphere(
//						new LambertMaterial(new Color(1, 0, 0))), 
//				new Node(new Sphere(
////						new SingleColorMaterial(new Color(1, 0, 0))), 
//						new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 20)), 
//						new Transform()
////							.scale(1, 3, 0.5)
////							.rotateX(Math.toRadians(-45))
//						), 
				
				new Node(new AxisAlignedBox( 
//						new SingleColorMaterial(new Color(1, 0, 0))), 
						new LambertMaterial(new Color(1, 0, 0))),
//						new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 20)),
						new Transform()
//							.scale(2, 0.5, 2)
							.rotateX(Math.toRadians(-45))
//							.rotateX(Math.toRadians(-199))
//							.rotateZ(Math.toRadians(270))
//							.rotateZ(Math.toRadians(-45))
						)
				
//				new AxisAlignedBox( 
////						new SingleColorMaterial(new Color(1, 0, 0))) 
//						new LambertMaterial(new Color(1, 0, 0)))
////						new PhongMaterial(new Color(1, 0, 0), new Color(0, 0, 0), 20))
		
		);
//		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(4, 4, 4)));
//		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(-5, 5, 5)));
		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(4, 4, 4), false));
//		world.addLight(new DirectionalLight(new Color(0.6, 0.6, 0.6), new Vector3(-10, -10, -40)));
//		world.addLight(new SpotLight(new Color(1, 1, 1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI / 14.0, false));
		return new Renderer(world, camera, 10);
	}
	
	private static Renderer scene2() {
		
//		Node parent = new Node(new Transform());
//		Node child1 = new Node(new Sphere(new Point3(0, 0, 0), 1, new SingleColorMaterial(new Color(1, 0, 0))),
//				new Transform().scale(1, 2, 0.5));
//		Node child2 = new Node(new Sphere(new Point3(0, 0, 0), 1, new SingleColorMaterial(new Color(0, 1, 0))),
//				new Transform().scale(2, 1, 1).translate(1, 0, 0));
//		parent.add(child1);
//		parent.add(child2);
		
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.0, 0.0, 0.0} }, Constants.INDEX_OF_REFRACTION_VACUUM);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
//				{ 20, 20, 20 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 40.0 } });
		
		final Material boxMaterial;
//		boxMaterial = new SingleColorMaterial(new Color(1, 0, 0));
//		boxMaterial = new LambertMaterial(new Color(1, 0, 0)); 
		boxMaterial = new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 20);
//		boxMaterial = new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 20, new Color(0.5, 0.5, 0.5));
		
		final Material planeMaterial;
//		planeMaterial = new SingleColorMaterial(new Color(0.5, 0.5, 0.5));
		planeMaterial = new LambertMaterial(new Color(0.5, 0.5, 0.5));
//		planeMaterial = new PhongMaterial(new Color(0.5, 0.5, 0.5), new Color(0.5, 0.5, 0.5), 20);
//		planeMaterial = new ReflectiveMaterial(new Color(0.5, 0.5, 0.5), new Color(0.5, 0.5, 0.5), 20, new Color(0.5, 0.5, 0.5));
		
		final Geometry box;
		box = new AxisAlignedBox(boxMaterial);
//		box = TriangleMesh.createTestTriangleMesh(boxMaterial);
		
		final Transform boxTransform = new Transform()
//			.scale(1, 1, 4)
//			.rotateX(Math.toRadians(45))
//			.rotateX(Math.toRadians(90))
//			.rotateX(Math.toRadians(-199))
	//		.rotateZ(Math.toRadians(270))
	//		.rotateZ(Math.toRadians(-45))
//			.scale(1, 1, 4)
			;
		
		world.addElements(
//				new Node(
//					new Plane(
//						new Point3(0, -1, 0), 
//						new Normal3(0, 1, 0), 
//						planeMaterial
//					), new Transform()),
				 
				new Node(box, boxTransform)
				
//				new Node(new Sphere(boxMaterial), boxTransform)
		);
//		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(4, 4, 4)));
//		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(-5, 5, 5)));
//		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(4, 4, 4), false));
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(4, 3, 3), false));
//		world.addLight(new DirectionalLight(new Color(0.6, 0.6, 0.6), new Vector3(-10, -10, -40)));
//		world.addLight(new SpotLight(new Color(1, 1, 1), new Point3(4, 4, 4), new Vector3(-1, -1, -1), Math.PI / 14.0, false));
		return new Renderer(world, camera, 10);
	}
	
private static Renderer scene3() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.0, 0.0, 0.0} }, Constants.INDEX_OF_REFRACTION_VACUUM);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 2.5, 2.5, 2.5 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		
		final Material meshMaterial;
//		meshMaterial = new SingleColorMaterial(new Color(1, 1, 1));
		meshMaterial = new LambertMaterial(new Color(1, 1, 1)); 
//		meshMaterial = new PhongMaterial(new Color(1, 1, 1), new Color(1, 1, 1), 20);
//		meshMaterial = new ReflectiveMaterial(new Color(1, 1, 1), new Color(1, 1, 1), 20, new Color(0.5, 0.5, 0.5));
		
//		String path = "models/cube-v.obj";
		String path = "models/teddy.obj";
//		String path = "models/ted.obj";
		final Geometry mesh = new ShapeFromFile(path, meshMaterial);
		
//		mesh = new AxisAlignedBox(meshMaterial);
//		box = TriangleMesh.createTestTriangleMesh(boxMaterial);
		
		final Transform boxTransform = new Transform()
//			.scale(1, 1, 4)
//			.rotateX(Math.toRadians(45))
//			.rotateX(Math.toRadians(90))
//			.rotateX(Math.toRadians(-199))
	//		.rotateZ(Math.toRadians(270))
	//		.rotateZ(Math.toRadians(-45))
//			.scale(1, 1, 4)
			;
		
		world.addElements(
//				new Node(
//					new Plane(
//						new Point3(0, -1, 0), 
//						new Normal3(0, 1, 0), 
//						planeMaterial
//					), new Transform()),
				 
				new Node(mesh, boxTransform
//						.rotateZ(Math.toRadians(180))
						)
				
//				new Node(new Sphere(boxMaterial), boxTransform)
		);
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(4, 3, 2), false));
		return new Renderer(world, camera, 10);
	}
}
