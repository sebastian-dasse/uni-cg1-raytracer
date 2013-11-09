package raytracer.ui;

import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileDialog {
	public static File open() throws IOException {
		JFileChooser chooser = new JFileChooser();
		/**
		 * We use a file chooser filter to see only .jpg and .gif images,
		 * this will help us to avoid choosing not supported types of files
		 **/
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & PNG Images", "jpg", "png");
		    chooser.setFileFilter(filter);
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