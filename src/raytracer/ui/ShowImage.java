package raytracer.ui;

import javax.swing.JFrame;

import raytracer.Raytracer;
import raytracer.image.TracerCanvas;

/**
 * TODO DOK IT ALL!
 * 
 * @author 
 *
 */
public final class ShowImage {
	
	public static void from(final Raytracer raytracer, final int x, final int y) {
		final TracerCanvas mainCanvas = new TracerCanvas(raytracer.trace());
		final JFrame frame = new JFrame();
		frame.getContentPane().add(mainCanvas);
		
		// TODO this does not seem to add any extra functionality yet, does it?
//		frame.addComponentListener(new ComponentAdapter() {
//			public void componentResized(ComponentEvent e) {
//				mainCanvas.setSize(frame.getSize());
//				frame.repaint();
//			}
//		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(raytracer.getSize().width, raytracer.getSize().height);
		frame.setLocation(x, y);
		frame.setVisible(true);
	}
	
	public static void from(final Raytracer raytracer) {
		from(raytracer, 0, 0);
	}
}
