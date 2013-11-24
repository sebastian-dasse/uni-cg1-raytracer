package raytracer.ui;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import javax.swing.JFrame;

import raytracer.Raytracer;
import raytracer.image.TracerCanvas;
public class ShowImage {
	
	public static void from (Raytracer raytracer) {
		final TracerCanvas mainCanvas = new TracerCanvas(raytracer.trace());
		final JFrame frame = new JFrame();
		frame.getContentPane().add(mainCanvas);
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				mainCanvas.setSize(frame.getSize());
				frame.repaint();
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(raytracer.getSize().width, raytracer.getSize().height);
		frame.setVisible(true);
	}
}
