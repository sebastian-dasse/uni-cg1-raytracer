package raytracer;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

import raytracer.camera.Camera;
import raytracer.camera.OrthographicCamera;
import raytracer.geometry.Hit;
import raytracer.math.Point3;
import raytracer.math.Vector3;

/**
 * @author 
 *
 */
public class Raytracer {
	private World world;
	private Camera cam;
	
	public Raytracer(World world, Camera cam) {
		this.world = world;
		this.cam = cam;
	}
	
	public static Object dataElementsFromColor(Color myColor, ColorModel colorModel) {
		return  colorModel.getDataElements(new float[] {
				(float) myColor.r,
				(float) myColor.g,
				(float) myColor.b
			}, 0, null);
	}
	
	public BufferedImage traceToImage(int width, int height) {
		BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
		WritableRaster raster = image.getRaster();
		final ColorModel colorModel = image.getColorModel();
		final Object backgroundColor = dataElementsFromColor(world.backgroundColor, colorModel);

		for (int x = 0; x < image.getWidth()-1; x++) {
			for (int y = 0; y < image.getHeight()-1;y++) {
				final Ray ray = cam.rayFor(width, height, x, y);
				final Hit hit = world.hit(ray);
				if (hit == null) {
					raster.setDataElements(x,y,backgroundColor);
				}
				else {
					raster.setDataElements(x,y,dataElementsFromColor(hit.geo.color, colorModel));
				}
			}
		}	
		return image;
	}
	
	
}
