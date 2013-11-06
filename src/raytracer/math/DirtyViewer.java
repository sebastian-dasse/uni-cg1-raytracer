package raytracer.math;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;

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

		File f = new File(chooser.getSelectedFile().getPath());
		Desktop dt = Desktop.getDesktop();
		dt.open(f);

	}

}
