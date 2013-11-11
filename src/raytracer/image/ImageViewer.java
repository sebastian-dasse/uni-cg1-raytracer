package raytracer.image;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import raytracer.ui.FileDialog;

public class ImageViewer {
	public static void main(String[] args) throws IOException {
		final BufferedImage image = ImageIO.read(FileDialog.open());
		final JFrame frame = new JFrame();
		final Canvas canvas = new Canvas() {
			private static final long serialVersionUID = 1L;

			public void paint(final Graphics g) {
				super.paint(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		frame.getContentPane().add(canvas);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(image.getHeight(), image.getWidth());
		frame.setVisible(true);
	}
}