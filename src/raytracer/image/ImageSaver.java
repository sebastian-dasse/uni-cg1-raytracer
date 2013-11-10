package raytracer.image;

import java.awt.Dimension;
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

public class ImageSaver {
	public static void main(String[] args) throws IOException {
		final Dimension globalSize = new Dimension (640,480);
		final ImageCanvas mainCanvas = new ImageCanvas(globalSize);
		/* 
		 * View
		 */
		final JFrame frame = new JFrame();
		frame.setSize(globalSize);
		JMenuBar menubar = new JMenuBar();
		JMenu menu = new JMenu("File");
		JMenuItem saveItem = new JMenuItem("Save");
		menu.add(saveItem);
		menubar.add(menu);
		frame.setJMenuBar(menubar);
		frame.getContentPane().add(mainCanvas);
		
		saveItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					ImageIO.write(mainCanvas.getImage(), "png", FileDialog.save());
				} catch (IOException ie) { 
				}
			}
		});
		
		frame.addComponentListener(new ComponentAdapter() {
			public void componentResized(ComponentEvent e) {
				mainCanvas.setSize(frame.getSize());
				frame.repaint();
			}
		});
		
		/*
		 * Show view
		 */
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}