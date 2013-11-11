package raytracer.dirty;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

import raytracer.ui.FileDialog;

public class DirtyViewer3  {
	public static void main(String[] args) throws IOException {
		final BufferedImage image = ImageIO.read(FileDialog.open());
		final JFrame frame = new JFrame();
		
		//---- SEB: NullPointException fangen finde ich eine schlechte Idee!
		// mit meiner Verbesserung (?) von FileDialog.open() sollte es aber auch 
		// keine NullPointerExceptions mehr geben
//		try{
//			frame.setSize(image.getHeight(), image.getWidth());
//		} catch (NullPointerException e) { 
//			frame.setSize(250, 100);
//			frame.setTitle("Fehlermeldung");
//			Container container = frame.getContentPane();
//			container.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 20));
//			container.add(new JLabel("Falsche Datei"));
//			frame.setVisible(true);
//		}
		
		final Canvas canvas = new Canvas() {
			private static final long serialVersionUID = 1L;

			public void paint(final Graphics g) {
				super.paint(g);
				g.drawImage(image, 0, 0, null);
			}
		};
		frame.getContentPane().add(canvas);
		
//		frame.getContentPane().add(new JLabel(new ImageIcon(image)));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.setSize(image.getHeight(), image.getWidth());
		frame.pack();
		frame.setVisible(true);
	}
}
