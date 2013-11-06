package raytracer.image;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

public class ImageSaver {
	public static void main(String[] args) {
		JFileChooser chooser = new JFileChooser();
//		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
//			System.exit(0);
//		}
		//BufferedImage image = ImageIO.read(chooser.getSelectedFile());
		BufferedImage image = new BufferedImage(100, 50,
                BufferedImage.TYPE_INT_ARGB);
        JFrame frame = new JFrame();
        frame.setSize(800, 600);
        frame.getContentPane().add(new ImageCanvas(image));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);	
	}

}
