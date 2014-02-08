package raytracer.tests.graphical;

import raytracer.Renderer;
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
		// TODO Auto-generated method stub
		return null;
	}
}
