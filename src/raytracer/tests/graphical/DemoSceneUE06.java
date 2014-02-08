package raytracer.tests.graphical;

import raytracer.Color;
import raytracer.Constants;
import raytracer.Renderer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.camera.PerspectiveCamera;
import raytracer.geometry.Node;
import raytracer.geometry.Plane;
import raytracer.light.PointLight;
import raytracer.material.SingleColorMaterial;
import raytracer.math.Point3;
import raytracer.math.Transform;
import raytracer.math.Vector3;
import raytracer.texture.ImageTexture;
import raytracer.ui.ShowImage;

public class DemoSceneUE06 {

	/**
	 * Shows all the demo scenes in individual <code>JFrame</code> windows.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		
		final Renderer[] tracers = new Renderer[]{
				testScene()
		};
		for (int i = 0; i < tracers.length; i++) {
			ShowImage.from(tracers[i], 50 * i, 25 * i);
		}
	}

	private static Renderer testScene() {
		final World world = new World(new Color(0, 0, 0), new Color(0.2, 0.2, 0.2), Constants.INDEX_OF_REFRACTION_AIR_AT_20_DEG);
		world.addElements(
				new Node(
					new Plane(
//							new SingleColorMaterial(new SingleColorTexture(new Color(1, 0, 0)))
//							new LambertMaterial(new SingleColorTexture(new Color(1, 0, 0)))
							
//							new SingleColorMaterial(new ImageTexture("textures/earth1.jpg"))
							new SingleColorMaterial(new ImageTexture("textures/UE06-Textur.png"))
//							new LambertMaterial(new SingleColorTexture(new Color(1, 0, 0)))
					), 
					new Transform()
				)
		);
		world.addLight(new PointLight(new Color(1, 1, 1), new Point3(3, 3, 3), false));
		
		final Camera camera = new PerspectiveCamera(new Point3(4, 4, 4), new Vector3(-1, -1, -1), new Vector3(0, 1, 0), Math.PI / 4.0);
		return new Renderer(world, camera, 10);
	}
}
