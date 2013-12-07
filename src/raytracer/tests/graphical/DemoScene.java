package raytracer.tests.graphical;

import java.awt.Dimension;

import raytracer.Color;
import raytracer.Raytracer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.Geometry;
import raytracer.light.PointLight;
import raytracer.material.LambertMaterial;
import raytracer.material.SingleColorMaterial;
import raytracer.math.Point3;
import raytracer.ui.ShowImage;

public final class DemoScene {
	public static final Dimension size = new Dimension(800, 600);
	
	public static void main(String[] args) {
//		ShowImage.from(scene1());
		ShowImage.from(scene2());
	}
	
	public static Raytracer scene1() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0, 0, 0} });
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new SingleColorMaterial(new Color(1, 0, 0))),
				Factory.buildSphere(new double[][] { 
						{ 1, 1, 1 }, { 0.5 } }, new SingleColorMaterial(new Color(0, 1, 0))),
				Factory.buildAxisAlignedBox(new double[][] { 
						{ -1.5, 0.5, 0.5 }, { -0.5, 1.5, 1.5 } }, new SingleColorMaterial(new Color(0, 0, 1))), 
				Factory.buildTriangle(new double[][] { 
						{ 0, 0, -1 }, { 1, 0, -1 }, { 1, 1, -1 }, 
						{ 0, 0, 1 }, { 0, 0, 1 }, { 0, 0, 1 } }, new SingleColorMaterial(new Color(1, 1, 0)))
			}
		);
		return new Raytracer(world, camera, size);
	}
	
	public static Raytracer scene2() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.3, 0.3, 0.9} });
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new LambertMaterial(new Color(1, 0, 0))),
				Factory.buildSphere(new double[][] { 
						{ 1, 1, 1 }, { 0.5 } }, new LambertMaterial(new Color(0, 1, 0))),
				Factory.buildAxisAlignedBox(new double[][] { 
						{ -1.5, 0.5, 0.5 }, { -0.5, 1.5, 1.5 } }, new LambertMaterial(new Color(0, 0, 1))), 
				Factory.buildTriangle(new double[][] { 
						{ 0, 0, -1 }, { 1, 0, -1 }, { 1, 1, -1 }, 
						{ 0, 0, 1 }, { 0, 0, 1 }, { 0, 0, 1 } }, new LambertMaterial(new Color(1, 1, 0)))
			}
		);
//		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(10, 10, 10)));
//		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(1, 10, 10)));
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(-10, 10, 10)));
//		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(-10, -10, 10)));
//		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(0, 10, 0)));
//		world.addLight(new PointLight(new Color(1, 0, 1), new Point3(-1, 10, 10))); // this s**t ain't gonna work
		return new Raytracer(world, camera, size);
	}
}
