package raytracer.ui;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class FileDialog {
	
	public static File open() throws IOException {
		final JFileChooser chooser = new JFileChooser();
		final FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & PNG Images", "jpg", "png");
		chooser.setFileFilter(filter);
		String fileExtension = "";
		File file = null;
		do {
			if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
				System.exit(0);
			}
			file = chooser.getSelectedFile();
			fileExtension = getFileExtension(file);
		} while (!fileExtension.equals("jpg") && !fileExtension.equals("png"));
		return file;
	}
	
	//---- SEB: ich habe die alte open() mal durch obige Variante ersetzt, 
	// die die Dateiendung mit der privaten Methode (siehe unten) checkt
	
//	public static File open() throws IOException {
//		final JFileChooser chooser = new JFileChooser();
//		/**
//		 * We use a file chooser filter to see only .jpg and .gif images,
//		 * this will help us to avoid choosing not supported types of files
//		 **/
//		FileNameExtensionFilter filter = new FileNameExtensionFilter(
//		        "JPG & PNG Images", "jpg", "png");
//		chooser.setFileFilter(filter);
//		if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
//			System.exit(0);
//		}
//		return chooser.getSelectedFile();
//	}

	public static File save() throws IOException {
		JFileChooser chooser = new JFileChooser();
		if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
			System.exit(0);
		}
		
		//---- SEB: bisher wurde ohne Dateiendung gespeichert - das ist zumindest unter Windows ein Problem beim Öffnen
//		return chooser.getSelectedFile();
		
		// sieht allerdings noch etwas noch etwas nach einem schmutzigen Trick aus - vielleicht geht es noch eleganter und sicherer?
		return new File(chooser.getSelectedFile() + ".png");
	}
	
	/**
	 * Returns the extension of the given file as a lower case String.
	 * 
	 * @param file	The file to be checked for its extension
	 * @return		The file extension or null if the file has no extension
	 */
	private static String getFileExtension(File file) {
		String filename = file.getName();
		int i = filename.lastIndexOf('.');
		return (i == -1) ? null : filename.substring(i + 1, filename.length()).toLowerCase();
	}
}
