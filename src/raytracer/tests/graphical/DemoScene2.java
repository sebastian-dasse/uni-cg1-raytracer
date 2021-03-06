package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Constants;
import raytracer.Renderer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.camera.PerspectiveCamera;
import raytracer.geometry.AxisAlignedBox;
import raytracer.geometry.Geometry;
import raytracer.geometry.Plane;
import raytracer.geometry.Sphere;
import raytracer.geometry.Triangle;
import raytracer.light.PointLight;
import raytracer.light.SpotLight;
import raytracer.material.LambertMaterial;
import raytracer.material.PhongMaterial;
import raytracer.material.ReflectiveMaterial;
import raytracer.material.TransparentMaterial;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.texture.SingleColorTexture;
import raytracer.texture.TexCoord2;
import raytracer.ui.ShowImage;

public class DemoScene2 {
	
	public static void main(String[] args) {
		
		final Renderer[] tracers = new Renderer[]{
				scene1(),  
//				scene7(), 
//				scene4(), 
//				scene7b(), 
//				scene7c(), 
//				scene8()
		};
		for (int i = 0; i < tracers.length; i++) {
			ShowImage.from(tracers[i], 50 * i, 25 * i);
		}
	}
	
	private static Renderer scene1() {
		final Camera camera = new PerspectiveCamera(
				new Point3(4, 4, 4),
				new Vector3(-1, -1, -1),
				new Vector3(0, 1, 0),
				Math.PI / 4.0
		);
		
		World world = new World(
				new Color(0, 0, 0),
				new Color(0.1, 0.1, 0.1),
				1
		);
		
		world.addElements(new Geometry[] {
				new Plane(
						new PhongMaterial(
								new SingleColorTexture(
										new Color(1, 0, 0)
								),
								new SingleColorTexture(
										new Color(1, 1, 1)
								),
								64
						)
				),
		
		
				new Sphere(
						new PhongMaterial(
								new SingleColorTexture(
										new Color(0, 1, 0)
								),
								new SingleColorTexture(
										new Color(1, 1, 1)
								),
								64
						)
				),
		
				new AxisAlignedBox(
						new PhongMaterial(
								new SingleColorTexture(
										new Color(1, 0, 0)
								),
								new SingleColorTexture(
										new Color(1, 1, 1)
								),
								64
						)
				),
		
				new Triangle(
						new Point3(0, 0, -1),
						new Point3(1, 1, 2),
						new Point3(1, 2, 2),
						new Normal3(0, 0, 1),
						new Normal3(0, 0, 1),
						new Normal3(0, 0, 1),
						new PhongMaterial(
							new SingleColorTexture(
									new Color(0.3, 1, 0.3)
							),
							new SingleColorTexture(
									new Color(1, 1, 1)
							),
							64
						),
						new TexCoord2(0,0),
						new TexCoord2(0,0),
						new TexCoord2(0,0)
				),
			
				new Triangle(
						new Point3(0, 0, -1),
						new Point3(1, 0, -1),
						new Point3(1, 1, -1),
						new Normal3(0, 0, 1),
						new Normal3(0, 0, 1),
						new Normal3(0, 0, 1),
						new PhongMaterial(
							new SingleColorTexture(
									new Color(0.3, 1, 0.3)
							),
							new SingleColorTexture(
									new Color(1, 1, 1)
							),
							64
						),
						new TexCoord2(0,0),
						new TexCoord2(0,0),
						new TexCoord2(0,0)
				)
		});
		world.addLight(
				new SpotLight(
						new Color(1, 1, 1),
						new Point3(4, 4, 4),
						new Vector3(-1, -1, -1),
						Math.PI / 14.0
				)
		);
		return new Renderer(world, camera, 10);
	}
	
	private static Renderer scene7() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.1, 0.1, 0.1} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 1, 1, 4 }, { -1, -1, -4 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64)),
//				Factory.buildPlane(new double[][] { 
//						{ -3, 0, 0 }, { -3, -0.5, -1} }, new PhongMaterial(new Color(1, 1, 1), new Color(1, 1, 1), 64)),
						
				Factory.buildSphere(new double[][] { 
						{ 1.5, 0, 0 }, { 0.5 } }, new PhongMaterial(new Color(0, 1, 0), new Color(1, 1, 1), 64)),
				Factory.buildSphere(new double[][] { 
						{ -1, 1, 0 }, { 0.25 } }, new PhongMaterial(new Color(0, 1, 1), new Color(1, 1, 1), 64)),
				Factory.buildSphere(new double[][] { 
						{ -1, 1, -5 }, { 1 } }, new PhongMaterial(new Color(1, 1, 0.2), new Color(1, 1, 1), 64)),
				Factory.buildAxisAlignedBox(new double[][] { 
						{ 0, 0, -5 }, { 0.5, 0.5, 5 } }, new PhongMaterial(new Color(0.3, 1, 0.3), new Color(1, 1, 1), 64)),
			}
		);
//		world.addLight(new PointLight(new Color(0.3, 0.3, 0.3), new Point3(4, 4, 4)));
//		world.addLight(new PointLight(new Color(0.3, 0.3, 0.3), new Point3(-4, 4, 4)));
//		world.addLight(new DirectionalLight(new Color(0.1, 0.1, 0.1), new Vector3(-1, -1, -1)));
		
//		world.addLight(new SpotLight(new Color(0.6, 0.6, 0.6), new Point3(1, 1, 4), new Vector3(-1, -1, -4), Math.PI / 7.0, true));
//		world.addLight(new SpotLight(new Color(0.6, 0.6, 0.6), new Point3(1, 1, -4), new Vector3(-1, -1, 4), Math.PI / 7.0, true));
		
		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(1, 1, 4), true));
		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(1, 1, -4), true));
		
//		world.addLight(new DirectionalLight(new Color(0.6, 0.6, 0.6), new Vector3(-1, -1, -4), true));
//		world.addLight(new DirectionalLight(new Color(0.6, 0.6, 0.6), new Vector3(-1, -1, 4), true));
		
//		world.addLight(new SpotLight(new Color(0.1, 0.1, 0.1), new Point3(1, 1, 1), new Vector3(-1, -1, -1), Math.PI / 7.0));
		return new Renderer(world, camera, 10);
	}
	
	private static Renderer scene7b() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.1, 0.1, 0.1} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 1, 1, 4 }, { -1, -1, -4 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		final Color specularColor = new Color(1, 1, 1);
		final Color reflectionColor = new Color(0.4, 0.4, 0.4);
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new ReflectiveMaterial(new Color(1, 0, 0), specularColor, 64, reflectionColor)),
//				Factory.buildPlane(new double[][] { 
//						{ -3, 0, 0 }, { -3, -0.5, -1} }, new ReflectiveMaterial(new Color(1, 1, 1), specularColor, 64, reflectionColor)),
						
				Factory.buildSphere(new double[][] { 
						{ 1.5, 0, 0 }, { 0.5 } }, new ReflectiveMaterial(new Color(0, 1, 0), specularColor, 64, reflectionColor)),
				Factory.buildSphere(new double[][] { 
						{ -1, 1, 0 }, { 0.25 } }, new ReflectiveMaterial(new Color(0, 1, 1), specularColor, 64, reflectionColor)),
				Factory.buildSphere(new double[][] { 
						{ -1, 1, -5 }, { 1 } }, new ReflectiveMaterial(new Color(1, 1, 0.2), specularColor, 64, reflectionColor)),
				Factory.buildAxisAlignedBox(new double[][] { 
						{ 0, 0, -5 }, { 0.5, 0.75, 5 } }, new ReflectiveMaterial(new Color(0.3, 1, 0.3), specularColor, 64, reflectionColor)),
//				Factory.buildAxisAlignedBox(new double[][] { 
//						{ 0, 0, -5 }, { 0.5, 0.75, 5 } }, new TransparentMaterial(Constants.INDEX_OF_REFRACTION_GLASS)),
			}
		);
//		world.addLight(new PointLight(new Color(0.3, 0.3, 0.3), new Point3(4, 4, 4)));
//		world.addLight(new PointLight(new Color(0.3, 0.3, 0.3), new Point3(-4, 4, 4)));
//		world.addLight(new DirectionalLight(new Color(0.1, 0.1, 0.1), new Vector3(-1, -1, -1)));
		
//		world.addLight(new SpotLight(new Color(0.6, 0.6, 0.6), new Point3(1, 1, 4), new Vector3(-1, -1, -4), Math.PI / 7.0, true));
//		world.addLight(new SpotLight(new Color(0.6, 0.6, 0.6), new Point3(1, 1, -4), new Vector3(-1, -1, 4), Math.PI / 7.0, true));
		
		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(1, 1, 4), true));
		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(1, 1, -4), true));
		
//		world.addLight(new DirectionalLight(new Color(0.6, 0.6, 0.6), new Vector3(-1, -1, -4), true));
//		world.addLight(new DirectionalLight(new Color(0.6, 0.6, 0.6), new Vector3(-1, -1, 4), true));
		
//		world.addLight(new SpotLight(new Color(0.1, 0.1, 0.1), new Point3(1, 1, 1), new Vector3(-1, -1, -1), Math.PI / 7.0));
		return new Renderer(world, camera, 10);
	}
	
	private static Renderer scene7c() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.1, 0.1, 0.1} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 1, 1 }, { -4, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		final Color specularColor = new Color(1, 1, 1);
		final Color reflectionColor = new Color(0.4, 0.4, 0.4);
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new ReflectiveMaterial(new Color(1, 0, 0), specularColor, 64, reflectionColor)),
//				Factory.buildPlane(new double[][] { 
//						{ -3, 0, 0 }, { -3, -0.5, -1} }, new ReflectiveMaterial(new Color(1, 1, 1), specularColor, 64, reflectionColor)),
						
//				Factory.buildSphere(new double[][] { 
//						{ 1.5, 0, 0 }, { 0.5 } }, new TransparentMaterial(Constants.INDEX_OF_REFRACTION_GLASS)),
				Factory.buildSphere(new double[][] { 
						{ 1.5, 0, 0 }, { 0.5 } }, new ReflectiveMaterial(new Color(0, 1, 0), specularColor, 64, reflectionColor)),
				Factory.buildSphere(new double[][] { 
						{ -1, 1, 0 }, { 0.25 } }, new ReflectiveMaterial(new Color(0, 1, 1), specularColor, 64, reflectionColor)),
				Factory.buildSphere(new double[][] { 
						{ -1, 1, -5 }, { 1 } }, new ReflectiveMaterial(new Color(1, 1, 0.2), specularColor, 64, reflectionColor)),
//				Factory.buildAxisAlignedBox(new double[][] { 
//						{ -0.5, -0.5, -5 }, { 0.5, 0.5, 5 } }, new LambertMaterial(new Color(0.3, 1, 0.3))),
				Factory.buildAxisAlignedBox(new double[][] { 
						{ -0.5, -0.5, -5 }, { 0.5, 0.5, 5 } }, new ReflectiveMaterial(new Color(0.3, 1, 0.3), specularColor, 64, reflectionColor)),
//				Factory.buildAxisAlignedBox(new double[][] { 
//						{ 0, 0, -5 }, { 0.5, 0.75, 5 } }, new ReflectiveMaterial(new Color(0.3, 1, 0.3), specularColor, 64, reflectionColor)),
//				Factory.buildAxisAlignedBox(new double[][] { 
//						{ 0, 0, -5 }, { 0.5, 0.75, 5 } }, new TransparentMaterial(Constants.INDEX_OF_REFRACTION_GLASS)),
			}
		);
//		world.addLight(new PointLight(new Color(0.3, 0.3, 0.3), new Point3(4, 4, 4)));
//		world.addLight(new PointLight(new Color(0.3, 0.3, 0.3), new Point3(-4, 4, 4)));
//		world.addLight(new DirectionalLight(new Color(0.1, 0.1, 0.1), new Vector3(-1, -1, -1)));
		
//		world.addLight(new SpotLight(new Color(0.6, 0.6, 0.6), new Point3(1, 1, 4), new Vector3(-1, -1, -4), Math.PI / 7.0, true));
//		world.addLight(new SpotLight(new Color(0.6, 0.6, 0.6), new Point3(1, 1, -4), new Vector3(-1, -1, 4), Math.PI / 7.0, true));
		
		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(1, 1, 4), true));
		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(1, 1, -4), true));
		
//		world.addLight(new DirectionalLight(new Color(0.6, 0.6, 0.6), new Vector3(-1, -1, -4), true));
//		world.addLight(new DirectionalLight(new Color(0.6, 0.6, 0.6), new Vector3(-1, -1, 4), true));
		
//		world.addLight(new SpotLight(new Color(0.1, 0.1, 0.1), new Point3(1, 1, 1), new Vector3(-1, -1, -1), Math.PI / 7.0));
		return new Renderer(world, camera, 10);
	}
	
	private static Renderer scene4() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.0, 0.0, 0.0} }, 1);
		final Color specularColor = new Color(1, 1, 1);
		final Color reflectionColor = new Color(0.4, 0.4, 0.4);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 3, 3, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new LambertMaterial(new Color(0.8, 0.8, 0.8))),
//				Factory.buildPlane(new double[][] { 
//						{ 0, 0, 0 }, { 0, 1, 0} }, new PhongMaterial(new Color(1, 0, 0), new Color(1, 1, 1), 64)),
				Factory.buildSphere(new double[][] { 
						{ 1.2, 1.5, 2 }, { 0.5 } }, new TransparentMaterial(Constants.INDEX_OF_REFRACTION_GLASS)),
				Factory.buildAxisAlignedBox(new double[][] { 
						{ -1.5, 0.5, 0.5 }, { -0.5, 1.5, 1.5 } }, new TransparentMaterial(Constants.INDEX_OF_REFRACTION_GLASS)), 
				Factory.buildTriangle(new double[][] { 
						{ -3, 0, 1 }, { -2, 0, 1 }, { -2, 1, 1 }, 
						{ 0, 0, 1 }, { 0, 0, 1 }, { 0, 0, 1 } }, new ReflectiveMaterial(new Color(1, 1, 0), specularColor, 64, reflectionColor))
			}
		);
		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(-1, 4, 4), true));
		world.addLight(new PointLight(new Color(0.6, 0.6, 0.6), new Point3(4, 4, 5), true));
		return new Renderer(world, camera, 2);
	}
	
	private static Renderer scene8() {
		final World world = Factory.buildWorld(new double[][] { { 0, 0, 0 }, {0.25, 0.25, 0.25} }, 1);
		final Camera camera = Factory.buildPerspectiveCamera(new double[][] {
				{ 4, 4, 4 }, { -1, -1, -1 }, { 0, 1, 0 }, { Math.PI / 4.0 } });
		world.addElements(new Geometry[] {
				Factory.buildPlane(new double[][] { 
						{ 0, 0, 0 }, { 0, 1, 0} }, new ReflectiveMaterial(new Color(0.2, 0.2, 0.2), new Color(1, 1, 1), 10, new Color(0.5, 0.5, 0.5))), 
				Factory.buildAxisAlignedBox(new double[][] { 
						{ -1.5, 0.5, 0.5 }, { -0.5, 1.5, 1.5 } }, new TransparentMaterial(Constants.INDEX_OF_REFRACTION_GLASS)), 
				Factory.buildSphere(new double[][] { 
						{ -3, 1, -1 }, { 1 } }, new ReflectiveMaterial(new Color(1, 0, 0), new Color(1, 0, 0), 64, new Color(0.6, 0.6, 0.6)))
			}
		);
		world.addLight(new PointLight(new Color(0.45, 0.45, 0.45), new Point3(4, 4, 4), true));
		world.addLight(new PointLight(new Color(0.45, 0.45, 0.45), new Point3(-4, 4, 4), true));
		return new Renderer(world, camera, 4);
	}
}
