package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Constants;
import raytracer.Renderer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.AxisAlignedBox;
import raytracer.geometry.Node;
import raytracer.geometry.ShapeFromFile;
import raytracer.geometry.Sphere;
import raytracer.light.PointLight;
import raytracer.material.LambertMaterial;
import raytracer.material.Material;
import raytracer.material.PhongMaterial;
import raytracer.math.Point3;
import raytracer.math.Transform;
import raytracer.ui.ShowImage;

public final class DemoSceneUE05 {

	public static void main(String[] args) {
		final Renderer[] tracers = new Renderer[]{
				smartieScene(), 
				boxScene(), 
				teddyScene(), 
//				bunnyScene()
		};
		for (int i = 0; i < tracers.length; i++) {
			ShowImage.from(tracers[i], 50 * i, 25 * i);
		}
	}

	private static Renderer smartieScene() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.1, 0.1, 0.1} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(
				new Node(
					new Sphere(new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64)), 
					new Transform()
						.rotateY(Math.PI/0.2)
						.rotateZ(Math.PI/0.9)
						.rotateX(Math.PI/1.15)
						.scale(2.0, 0.5, 2.0)
				)
		);
		
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(4, 4, 4), false));
		return new Renderer(world, camera, 10);
	}

	private static Renderer boxScene() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.1, 0.1, 0.1} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(
				new Node(
					new AxisAlignedBox(new LambertMaterial(new Color(1, 1, 0))), 
					new Transform()
						.rotateX(Math.PI/1.17)
						.rotateY(Math.PI/0.2) 
						.rotateZ(Math.PI/1.15) 
						.scale(1.0, 0.26, 3.7)
				)
		);
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(4, 4, 4), false));
		return new Renderer(world, camera, 10);
	}

	private static Renderer teddyScene() {
		final String path = "models/teddy.obj";
		final Material material = new LambertMaterial(new Color(1, 1, 1));
		
		final World world = Factory.buildWorld(new double[][]{{0, 0, 0}, {0, 0, 0}}, Constants.INDEX_OF_REFRACTION_AIR_AT_20_DEG);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][]{
				{2.5, 2.5, 2.5}, {-1, -1, -1}, {0, 1, 0}, {Math.PI / 4.0}});
		world.addElements(
				new Node(new ShapeFromFile(path, material), new Transform())
		);
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(3, 3, 3), false));
		return new Renderer(world, camera);
	}

	private static Renderer bunnyScene() {
		final String path = "models/bunny.obj";
		final Material material = new LambertMaterial(new Color(1, 1, 1));
		
		final World world = Factory.buildWorld(new double[][]{{0, 0, 0}, {0, 0, 0}}, Constants.INDEX_OF_REFRACTION_AIR_AT_20_DEG);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][]{
				{10, 10, 10}, {-1, -1, -1}, {0, 1, 0}, {Math.PI / 4.0}});
		world.addElements(
				new Node(new ShapeFromFile(path, material), new Transform())
		);
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(10, 10, 10), false));
		return new Renderer(world, camera);
	}
}