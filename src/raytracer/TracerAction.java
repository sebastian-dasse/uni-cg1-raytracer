package raytracer;

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

import raytracer.camera.Camera;
import raytracer.camera.OrthographicCamera;
import raytracer.geometry.AxisAlignedBox;
import raytracer.geometry.Geometry;
import raytracer.geometry.Plane;
import raytracer.image.ImageCanvas;
import raytracer.image.TracerCanvas;
import raytracer.math.Normal3;
import raytracer.math.Point3;
import raytracer.math.Vector3;
import raytracer.ui.FileDialog;
public class TracerAction {
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
		final World world = new World(new Color(0,0,0));
		final Camera cam = new OrthographicCamera(new Point3(4, 4, 4), new Vector3(-4, -4, -4), new Vector3(0, 1, 0), 3);
		Raytracer myTracer = new Raytracer(world,cam);
		
		world.addElements(new Geometry[] {
//			new Plane(new Point3(0, 0, 0), new Normal3(0, 1, 0), new Color(1, 0, 0.1))
			new AxisAlignedBox(new Point3(0.1, 0.1, 0.1), new Point3(0.1, 0.1, 0.1), new Color(0.5, 0.5, 0.5))
		});
		
		
		final TracerCanvas mainCanvas = new TracerCanvas(myTracer.traceToImage(WIDTH, HEIGHT));
		
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
