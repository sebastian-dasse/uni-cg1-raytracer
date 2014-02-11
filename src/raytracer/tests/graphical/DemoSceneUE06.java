package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Constants;
import raytracer.Renderer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.camera.PerspectiveCamera;
import raytracer.geometry.AxisAlignedBox;
import raytracer.geometry.Node;
import raytracer.geometry.Plane;
import raytracer.geometry.ShapeFromFile;
import raytracer.geometry.Sphere;
import raytracer.light.DirectionalLight;
import raytracer.light.PointLight;
import raytracer.material.DayAndNightMaterial;
import raytracer.material.LambertMaterial;
import raytracer.material.Material;
import raytracer.material.PhongMaterial;
import raytracer.material.ReflectiveMaterial;
import raytracer.material.SingleColorMaterial;
import raytracer.material.TransparentMaterial;
import raytracer.math.Point3;
import raytracer.math.Transform;
import raytracer.math.Vector3;
import raytracer.texture.ImageTexture;
import raytracer.texture.InterpolatedImageTexture;
import raytracer.texture.SingleColorTexture;
import raytracer.texture.Texture;
import raytracer.ui.ShowImage;

/**
 * 
* Generates and displays <code>Renderer</code> objects for demo scenes as demanded in task 6.
* 
* @author Maxim Novichkov
* @author Sebastian Dass&eacute;
* @author Simon Lischka
*
*/
public class DemoSceneUE06 {

	/**
	 * Shows all the demo scenes in individual <code>JFrame</code> windows.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		final Renderer[] tracers = new Renderer[]{
				scene1(), 
				scene2(), 
				scene3(), 
				scene4(), 
//				scene5()
		};
		for (int i = 0; i < tracers.length; i++) {
			ShowImage.from(tracers[i], 50 * i, 25 * i);
		}
	}

	/**
	 * Generates a scene showing the earth with a high-resolution texture.
	 * 
	 * @return The <code>Renderer</code>.
	 */
	private static Renderer scene1() {
		final String path = "textures/earth1.jpg";
		final Texture imgTexture = new ImageTexture(path);
		final World world = new World(new Color(0, 0, 0), new Color(0.0, 0.0, 0.0), Constants.INDEX_OF_REFRACTION_VACUUM);
		world.addElements(
				new Node(
						new Sphere(
//								new SingleColorMaterial(imgTexture)
								new LambertMaterial(imgTexture)
						), 
						new Transform()
				)
		);
		world.addLights(new PointLight(new Color(1, 1, 1), new Point3(3, 3, 3), false));
		final Camera camera = new PerspectiveCamera(new Point3(2, 2, 2), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4.0);
		return new Renderer(world, camera, 10);
	}
	
	/**
	 * Generates a scene showing the earth with a low-resolution texture that is not interpolated.
	 * 
	 * @return The <code>Renderer</code>.
	 */
	private static Renderer scene2() {
		final String path = "textures/earth1-scaled.jpg";
		final Texture imgTexture = new ImageTexture(path);
		final World world = new World(new Color(0, 0, 0), new Color(0.0, 0.0, 0.0), Constants.INDEX_OF_REFRACTION_VACUUM);
		world.addElements(
				new Node(
						new Sphere(
//								new SingleColorMaterial(imgTexture)
								new LambertMaterial(imgTexture)
						), 
						new Transform()
				)
		);
		world.addLights(new PointLight(new Color(1, 1, 1), new Point3(3, 3, 3), false));
		final Camera camera = new PerspectiveCamera(new Point3(2, 2, 2), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4.0);
		return new Renderer(world, camera, 10);
	}
	
	/**
	 * Generates a scene showing the earth with an interpolated low-resolution texture.
	 * 
	 * @return The <code>Renderer</code>.
	 */
	private static Renderer scene3() {
		final String path = "textures/earth1-scaled.jpg";
		final Texture intImgTexture = new InterpolatedImageTexture(path);
		final World world = new World(new Color(0, 0, 0), new Color(0.0, 0.0, 0.0), Constants.INDEX_OF_REFRACTION_VACUUM);
		world.addElements(
				new Node(
						new Sphere(
//								new SingleColorMaterial(intImgTexture)
								new LambertMaterial(intImgTexture)
						), 
						new Transform()
				)
		);
		world.addLights(new PointLight(new Color(1, 1, 1), new Point3(3, 3, 3), false));
		final Camera camera = new PerspectiveCamera(new Point3(2, 2, 2), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4.0);
		return new Renderer(world, camera, 10);
	}
	
	/**
	 * Generates a scene showing the earth with a high-resolution texture. Depending on the amount of illumination for 
	 * a specific point the texture for day or night is shown.
	 * 
	 * @return The <code>Renderer</code>.
	 */
	private static Renderer scene4() {
		final Texture dayTexture = new ImageTexture("textures/earthDay.jpg");
		final Texture nightTexture = new ImageTexture("textures/earthNight.jpg");
		final World world = new World(new Color(0, 0, 0), new Color(0.0, 0.0, 0.0), Constants.INDEX_OF_REFRACTION_VACUUM);
		world.addElements(
				new Node(
						new Sphere(
								new DayAndNightMaterial(
										new LambertMaterial(dayTexture), 
										new LambertMaterial(nightTexture)
								)
						), 
						new Transform()
				)
		);
		world.addLights(
				new PointLight(
						new Color(1, 1, 1), 
						new Point3(15, 0, -4), 
						false
				)
		);
		final Camera camera = new PerspectiveCamera(new Point3(2, 2, 2), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4.0);
		return new Renderer(world, camera, 10);
	}
	
	/**
	 * Generates a free scene.
	 * 
	 * @return The <code>Renderer</code>.
	 */
	private static Renderer scene5() {
//		final Material material = new LambertMaterial(
//		new SingleColorTexture(new Color(1, 1, 1)));
		
		final Material smarties = new ReflectiveMaterial(
						new SingleColorTexture(new Color(1, 1, 1)), 
						new SingleColorTexture(new Color(1, 1, 1)), 
						10, 
						new SingleColorTexture(new Color(1, 0.5, 0.5)));
//		new PhongMaterial(
//		new SingleColorTexture(new Color(1, 0, 0)), 
//		new SingleColorTexture(new Color(1, 0, 0)), 64);
		
		final Material sphere = new SingleColorMaterial(
				new SingleColorTexture(new Color(1,0,1)));
		final Material sphereReflective = new ReflectiveMaterial(
						new SingleColorTexture(new Color(0, 0, 1)), 
						new SingleColorTexture(new Color(1, 1, 1)), 
						10, 
						new SingleColorTexture(new Color(1, 0.5, 0.5)));
		final Material sphereTransparent = new TransparentMaterial(Constants.INDEX_OF_REFRACTION_WATER);
		
		
		final Material boxEis = new TransparentMaterial(Constants.INDEX_OF_REFRACTION_GLASS);
		final Material box = new SingleColorMaterial(new SingleColorTexture(new Color(1,1,0)));
		final Material boxReflected = new ReflectiveMaterial(
				new SingleColorTexture(new Color(1,0,1)), 
				new SingleColorTexture(new Color(1, 1, 1)), 
				10, 
				new SingleColorTexture(new Color(1, 0.5, 0.5)));
		
		
		final Material teddyEis = new TransparentMaterial(Constants.INDEX_OF_REFRACTION_ICE);
		final Material teddy = new SingleColorMaterial(
				new SingleColorTexture(new Color(0, 1, 1)));
		final Material teddyPhong = new PhongMaterial(
				new SingleColorTexture(new Color(1, 0, 0)), 
				new SingleColorTexture(new Color(1, 1, 1)), 
				64);  
		final ShapeFromFile teddyShape = new ShapeFromFile("models/teddy.obj", teddyPhong);
		
		
		final Material earth = new LambertMaterial(new ImageTexture("textures/earthDay.jpg"));
		
		
		final World world = new World(new Color(0, 0, 0), new Color(0.3, 0.3, 0.3), Constants.INDEX_OF_REFRACTION_VACUUM);
		world.addElements(
						
			//////////////////////////////// --yyyyyyyyyy
//						new Node(		
//						new Sphere(
//								new SingleColorMaterial(new SingleColorTexture(new Color(1,0,0)))
//						), 
//						new Transform()
//						.translate(0, 0.5, 0)
//						.scale(0.15, 0.15, 0.15)
//						),
//						
//						new Node(		
//						new Sphere(
//								new SingleColorMaterial(new SingleColorTexture(new Color(1,0,0)))
//						), 
//						new Transform()
//						.translate(0, 4, 0)
//						.scale(0.15, 0.15, 0.15)
//						),
//						
//						new Node(		
//						new Sphere(
//								new SingleColorMaterial(new SingleColorTexture(new Color(1,0,0)))
//						), 
//						new Transform()
//						.translate(0, 8, 0)
//						.scale(0.15, 0.15, 0.15)
//						),
//						
//						new Node(		
//								new Sphere(
//										new SingleColorMaterial(new SingleColorTexture(new Color(1,0,0)))
//								), 
//								new Transform()
//								.translate(0, 10, 0)
//								.scale(0.15, 0.15, 0.15)
//								),
//						
//						//////////////////////////////// --zzzzzzzzzzz
//						new Node(		
//						new Sphere(
//								new SingleColorMaterial(new SingleColorTexture(new Color(0,0,1)))
//						), 
//						new Transform()
//						.translate(0, 0, 0.5)
//						.scale(0.15, 0.15, 0.15)
//						),
//						
//						new Node(		
//						new Sphere(
//								new SingleColorMaterial(new SingleColorTexture(new Color(0,0,1)))
//						), 
//						new Transform()
//						.translate(0, 0, 4)
//						.scale(0.15, 0.15, 0.15)
//						),
//						
//						new Node(		
//						new Sphere(
//								new SingleColorMaterial(new SingleColorTexture(new Color(0,0,1)))
//						), 
//						new Transform()
//						.translate(0, 0, 8)
//						.scale(0.15, 0.15, 0.15)
//						),
//						
//						//////////xxxxxxxxxxxxxxxxxxxxxxxxxxxxx
//						new Node(		
//								new Sphere(
//										new SingleColorMaterial(new SingleColorTexture(new Color(0,1,0)))
//								), 
//								new Transform()
//								.translate(0.5, 0, 0)
//								.scale(0.15, 0.15, 0.15)
//								),
//								
//								new Node(		
//								new Sphere(
//										new SingleColorMaterial(new SingleColorTexture(new Color(0,1,0)))
//								), 
//								new Transform()
//								.translate(4, 0, 0)
//								.scale(0.15, 0.15, 0.15)
//								),
//								
//								new Node(		
//								new Sphere(
//										new SingleColorMaterial(new SingleColorTexture(new Color(0,1,0)))
//								), 
//								new Transform()
//								.translate(8, 0, 0)
//								.scale(0.15, 0.15, 0.15)
//								),
								
								
			////	//////////////////////////////////////////////	
								new Node(new Plane(
										new ReflectiveMaterial(
										new SingleColorTexture(new Color(0.8, 0.8, 0.8)), 
										new SingleColorTexture(new Color(1, 1, 1)), 
										10, 
										new SingleColorTexture(new Color(1, 1, 1)))), 
										new Transform()
										.translate(0, 0, 0)
										),
								
								new Node(		
										new Sphere(
										sphereTransparent
										), 
										new Transform()
										.translate(4.5, 2, 2.7)
										//.translate(3, 1, 3)
										.scale(0.6, 0.6, 0.6)
										),
										
								new Node(		
										new Sphere(
										sphereTransparent
										), 
										new Transform()
										.translate(4.5, 2, 6.4)
										//.translate(3, 1, 6)
										.scale(0.6, 0.6, 0.6)
										),
										
								new Node(		
										new Sphere(
										sphereTransparent
										), 
										new Transform()
										.translate(2.7, 2, 4.5)
										.scale(0.6, 0.6, 0.6)
										),
										
								new Node(		
										new Sphere(
										sphereTransparent
										), 
										new Transform()
										.translate(6.5, 2, 4.5)
										.scale(0.6, 0.6, 0.6)
										),
										
								new Node(		
										new AxisAlignedBox(
										boxReflected), 
										new Transform()
										.translate(4.5, 1, 4.5)
										.scale(2, 2, 2)
										),
								
								//small inside balls	
								new Node(		
										new Sphere(
										sphereReflective
										), 
										new Transform()
										.translate(4.5, 2, 2.7)
										.scale(0.15, 0.15, 0.15)
										),
												
								new Node(		
										new Sphere(
										sphereReflective
										), 
										new Transform()
										.translate(4.5, 2, 6.4)
										.scale(0.15, 0.15, 0.15)
										),
												
								new Node(		
										new Sphere(
										sphereReflective
										), 
										new Transform()
										.translate(2.7, 2, 4.5)
										.scale(0.15, 0.15, 0.15)
										),
												
								new Node(		
										new Sphere(
										sphereReflective
										), 
										new Transform()
										.translate(6.5, 2, 4.5)
										.scale(0.15, 0.15, 0.15)
										),	
										
										
									//Teddy
								new Node(teddyShape, 
										new Transform()
										.translate(3.5, 2.9, 5.1)
										.scale(1, 1, 1)
										.rotateY(0.6)
										),
								
								new Node(teddyShape, 
										new Transform()
										.translate(4.9, 2.9, 3.5)
										.scale(1, 1, 1)
										.rotateY(Math.PI+0.8)
										),
				
							    new Node(teddyShape, 
							    		new Transform()
							    		.translate(5.0, 2.9, 5.0)
							    		.scale(1, 1, 1)
							    		.rotateY(Math.PI+2.3)
							    		),
							    
							    new Node(teddyShape, 
							    		new Transform()
							    		.translate(3.5, 2.9, 3.5)
							    		.scale(1, 1, 1)
							    		.rotateY(Math.PI-0.8)
							    		),
								
							  //  smarties
							    new Node(		
							    		new Sphere(
							    			sphereTransparent
							    		), 
							    		new Transform()
							    		.translate(4.5, 4, 4.5)
							    		.scale(1.6, 0.1, 1.6)
							    		),
							    		
							    new Node(		
								   		new Sphere(
								   		sphereReflective
								   		), 
							    		new Transform()
							    		.translate(4.5, 4, 4.5)
							    		.scale(1.28, 0.05, 1.28)
							    		),

							    //world		
								new Node(		
										new Sphere(
										earth
										), 
										new Transform()
										.translate(4.5, 5.2, 4.5)
										.rotateY(Math.PI)
										.scale(1, 1, 1)
										)
												
		);
		world.addLights(
						new PointLight(new Color(0.1, 0.1, 0.1), new Point3(3, 3, 3), true),
						new PointLight(new Color(0.3, 0.3, 0.3), new Point3(5, 5, -10), true),
						new PointLight(new Color(0.3, 0.3, 0.3), new Point3(5, 5, 10), true), 
						new DirectionalLight(new Color(0.1, 0.1, 0.1), new Vector3(1, -1, 0)));
//		final Camera camera = new PerspectiveCamera(new Point3(4.5, 12, 4.5), new Vector3(0, -1, 0), new Vector3(0.1, 2, 0.1), Math.PI / 2.0);
//		final Camera camera = new PerspectiveCamera(new Point3(8, 6.5, 8), new Vector3(-1, -1.5, -1), new Vector3(0, 1, 0), Math.PI / 2.0);
		final Camera camera = new PerspectiveCamera(new Point3(10, 5, 8), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 2.0);

		return new Renderer(world, camera, 10);
	}
}
