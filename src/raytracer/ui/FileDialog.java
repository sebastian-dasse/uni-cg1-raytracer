package raytracer.ui;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;

public class FileDialog {
	public static File open() throws IOException {
		JFileChooser chooser = new JFileChooser();
		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
			System.exit(0);
		}
		return chooser.getSelectedFile();
	}
	
	public static File save() throws IOException {
		JFileChooser chooser = new JFileChooser();
		if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
			System.exit(0);
		}
		return chooser.getSelectedFile();
	}
}
