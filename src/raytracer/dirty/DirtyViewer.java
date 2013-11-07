package raytracer.dirty;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class DirtyViewer {

	public static void main(String[] args) throws IOException {

		// JFileChooser-Objekt erstellen
		JFileChooser chooser = new JFileChooser();
		// Dialog zum Oeffnen von Dateien anzeigen
		int rueckgabeWert = chooser.showOpenDialog(null);

		/* Abfrage, ob auf "Öffnen" geklickt wurde */
		if (rueckgabeWert == JFileChooser.APPROVE_OPTION) {
			// Ausgabe der ausgewaehlten Datei
//			System.out.println("Die zu öffnende Datei ist: "
//					+ chooser.getSelectedFile().getPath());
		}
		
		
		File file = new File(chooser.getSelectedFile().getPath());
		
		BufferedImage image = ImageIO.read(file);
        JLabel label = new JLabel(new ImageIcon(image));
        JFrame f = new JFrame();
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.getContentPane().add(label);
        f.pack();
        f.setLocation(200,200);
        f.setVisible(true);

	}

}
