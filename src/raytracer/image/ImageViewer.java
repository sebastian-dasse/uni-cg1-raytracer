package raytracer.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import raytracer.ui.FileDialog;

/**
 * TODO
 * 
 * @author 
 *
 */
public class ImageViewer {
	public static void main(final String[] args) throws IOException {
		final BufferedImage image = ImageIO.read(FileDialog.open());
		final JFrame frame = new JFrame();
		frame.getContentPane().add(new JLabel(new ImageIcon(image)));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
}