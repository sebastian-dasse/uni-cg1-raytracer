package raytracer.image;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class ImageSaver {
	public static void main(String[] args) {
		BufferedImage image = new BufferedImage(100, 50,
                BufferedImage.TYPE_INT_ARGB);
        final JFrame frame = new JFrame();
        frame.setSize(640, 480);
        final ImageCanvas drawing = new ImageCanvas(image);
        frame.getContentPane().add(drawing);
        frame.addComponentListener(new ComponentAdapter() {
        	public void componentResized(ComponentEvent e) {
        		drawing.setSize(frame.getSize());
        		frame.repaint();
        	}
        });
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);	
	}

}
