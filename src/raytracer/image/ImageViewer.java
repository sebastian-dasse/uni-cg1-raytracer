package raytracer.image;

import java.awt.Container;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class ImageViewer {
	public static void main(String[] args) throws IOException{
		 /*
		  * Max: Open Dialog + Buffered Image
		  */
		 JFileChooser chooser = new JFileChooser();
		 if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			 System.exit(0);
		 }
		 File file = new File(chooser.getSelectedFile().getPath());
		 BufferedImage image = ImageIO.read(file);
		 JLabel label = new JLabel(new ImageIcon(image));
		 /*
		  * -- SIM
		  */
		JFrame frame = new JFrame("Image Viewer");
		frame.add(label);
		Container container = frame.getContentPane();
		container.setLayout(new GridLayout());
		container.add(new ImageCanvas());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(550, 100);
		frame.setVisible(true);	
	}
}
