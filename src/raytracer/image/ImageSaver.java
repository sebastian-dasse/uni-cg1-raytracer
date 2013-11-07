package raytracer.image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import raytracer.ui.FileDialog;


public class ImageSaver {
	public static void main(String[] args) throws IOException {
		final BufferedImage image = new BufferedImage(100, 50,
                BufferedImage.TYPE_INT_ARGB);
        final JFrame frame = new JFrame();
        
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenuItem saveItem = new JMenuItem("Save");
        menu.add(saveItem);
        menubar.add(menu);
        frame.setJMenuBar(menubar);
        
        /* 
         * Doesn't work jet - saves red mini image. Mega Quick and Dirty,
         * has to be refactored. 
         */
        saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImageIO.write(image, "jpg", FileDialog.save());
				}
				catch (IOException ie) {
					
				}
			}
        });
        
        frame.setSize(640, 480);
        final ImageCanvas drawing = new ImageCanvas(image);
        frame.getContentPane().add(drawing);
        
        /*
         * Listener could be refactored too - look for intelligent solution 
         * for ListenerClasses.
         */
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
