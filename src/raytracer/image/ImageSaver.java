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
 * This class shows opens a 604 x 480 px windows and generates an image of the same size. The image simply shows a red 
 * diagonal line drawn from the left upper corner to the right bottom corner on black background. The program
 * 
 * @author Max Novichkov
 * @author Simon Lischka
 * @author Sebastian Dassé
 *
 */
public class ImageSaver {
	/**
	 * Width definition for generated image
	 */
	public static final int WIDTH = 640;
	/**
	 * Height definition for generated image
	 */
	public static final int HEIGHT = 480;
	
	/**
	 * Generates an image containing a diagonal and draw the
	 * the applications view. 
	 * 
	 * Throws IOExcepztion when file cannot be saved.
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException {
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
 * Contains the Menubar View. Writes the image to 
 * the filename specified in the save-Dialog.
 * 
 * @author Max Novichkov
 * 
 *
 */
class ImageSaverMenuBar extends JMenuBar {
	/**
	 * Builds the menu structure and opens file dialog
	 * when Menu-Item "Save" is selected.
	 * 
	 * @param canvas
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
