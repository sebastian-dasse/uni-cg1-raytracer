package raytracer.image;

import java.awt.Canvas;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JLabel;
import raytracer.ui.FileDialog;

public class ImageViewer {
	public static void main(String[] args) throws IOException {
		final BufferedImage image = ImageIO.read(FileDialog.open());
		JFrame frame = new JFrame();
		try{
			frame.setSize(image.getHeight(), image.getWidth());
		} catch (NullPointerException e) { 
			frame.setSize(250, 100);
			frame.setTitle("Fehlermeldung");
			Container container = frame.getContentPane();
			container.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
			container.add(new JLabel("Falsche Datei"));
			frame.setVisible(true);
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final Canvas c = new Canvas() {
			private static final long serialVersionUID = 1L;

			public void paint(final Graphics g) {
				super.paint(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		frame.getContentPane().add(c);
		frame.setVisible(true);
	}
}