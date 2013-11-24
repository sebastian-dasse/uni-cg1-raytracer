package raytracer.tests.dirtyGraphical;

import java.awt.Dimension;

import raytracer.Raytracer;
import raytracer.World;
import raytracer.camera.Camera;
import raytracer.geometry.Geometry;
import raytracer.ui.ShowImage;

public class StraightForwardTests {
	public static void main (String [] args) {
		   World world = Factory.buildWorld(new double []{0.0,0.0,0.0});
		   final Camera cam = Factory.buildOrtographicCamera(new double [][] {{4,4,4},{-4,-4,-4},{0,1,0},{3}});
		   world.addElements(new Geometry [] {
				   Factory.buildPlane(new double [][]{{0,0,0},{0,1,0},{1,0,0.1}}),
				   Factory.buildAxisAlignedBox(new double[][] {{0.1,0.1,0.1},{0.1,0.1,0.1},{0.5,0.5,0.5}})
		   } );
		   ShowImage.from(new Raytracer(world,cam, new Dimension(800,600)));
	}
}

