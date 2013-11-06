package raytracer.image;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ImageViewer {
	public static final File promptForImagefile() throws IOException {
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			System.exit(0);
		}
		return chooser.getSelectedFile();
	}

	public static void main(String[] args) throws IOException {
		final BufferedImage image = ImageIO.read(promptForImagefile());
		JFrame frame = new JFrame();
		frame.setSize(image.getHeight(), image.getWidth());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		/*
		 * Using canvas and anonymous paint method, since paint
		 * method body to short to justify a new class.
		 * Using canvas because that way we 
		 * use the same object that will be applied in the ImageSaver
		 * and code is easier to generalize in case it is desiiiiiired!
		 */
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
