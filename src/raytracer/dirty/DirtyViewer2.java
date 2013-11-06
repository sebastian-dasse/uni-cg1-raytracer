package raytracer.dirty;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * 
 * @author Sebastian Dassé
 *
 */
public class DirtyViewer2 {
public static void main(String[] args) throws IOException {
		JFileChooser chooser = new JFileChooser();
		int dialogReturnVal = chooser.showOpenDialog(null);
		
		switch(dialogReturnVal) {
		case JFileChooser.APPROVE_OPTION:
			break;
		case JFileChooser.CANCEL_OPTION:
			return;
//			break;
		case JFileChooser.ERROR_OPTION:
			throw new IOException("An error occured in the process of choosing a file.");
//			break;
		}
		File file = chooser.getSelectedFile();
//		if (file == null) {
//			return;
//		}
		
		BufferedImage image = ImageIO.read(file);
		JLabel label = new JLabel(new ImageIcon(image));
		JFrame frame = new JFrame();
		frame.getContentPane().add(label);
		
		frame.pack();
		frame.setLocation(200,200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}
