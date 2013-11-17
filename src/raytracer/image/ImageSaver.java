package raytracer.image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import raytracer.ui.FileDialog;

/**
 * This class opens a 640 x 480 px window and generates an image of the same size. The image simply shows a red 
 * diagonal line drawn from the left upper corner to the right bottom corner on black background. The window has a menu 
 * bar with a save option. The window frame is allowed to be resized. In this case the image will be redrawn at the new 
 * window size.
 * 
 * @author Maxim Novichkov
 * @author Simon Lischka
 * @author Sebastian Dass&eacute;
 *
 */
public class ImageSaver {
	/**
	 * The predefined width of the generated image.
	 */
	public static final int WIDTH = 640;
	/**
	 * The predefined height of the generated image.
	 */
	public static final int HEIGHT = 480;
	
	/**
	 * Show the image in a <code>JFrame</code> with a <code>JMenuBar</code>.
	 * 
	 * @param args
	 */
	public static void main(final String[] args) {
		final ImageCanvas mainCanvas = new ImageCanvas(WIDTH, HEIGHT);
		final JFrame frame = new JFrame();
		frame.getContentPane().add(mainCanvas);
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				mainCanvas.setSize(frame.getSize());
				frame.repaint();
			}
		});
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		frame.setJMenuBar(new ImageSaverMenuBar(mainCanvas));
		frame.setVisible(true);
	}
}

/**
 * A menu bar for saving images. Allows to write the image to the filename specified in the save dialog.
 * 
 * @author Maxim Novichkov
 *
 */
class ImageSaverMenuBar extends JMenuBar {
	/**
	 * Builds the menu structure and opens a file dialog when the menu item "Save" is selected.
	 * 
	 * @param canvas	The <code>ImageCanvas</code> containing the image to be saved.
	 */
	public ImageSaverMenuBar(final ImageCanvas canvas) {
		final JMenu menu = new JMenu("File");
		final JMenuItem saveItem = new JMenuItem("Save");
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImageIO.write(canvas.getImage(), "png", FileDialog.save());
				} catch (IOException ie) {
					System.err.println("An error occured during writing.");
				}
			}
		});
		menu.add(saveItem);
		add(menu);
	}
}
