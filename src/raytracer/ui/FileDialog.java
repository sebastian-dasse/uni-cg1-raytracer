package raytracer.ui;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Class contains the File Dialog for opening and saving image.
 * 
 * @author Max Novichkov
 * @author Sebastian Dass&eacute;
 * @author Simon Lischka
 *
 */
public class FileDialog {
	
	/**
	 * Open Dialog, with predifined filter for JPG and PNG images.
	 * Prompts user for file selection until valid selection is made.
	 * 
	 * @return File
	 * @throws IOException
	 */
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

	/**
	 * Opens Save-Dialog, appends filename entry with .png ending
	 * after user entry.
	 * 
	 * @return File
	 * @throws IOException
	 */
	public static File save() throws IOException {
		JFileChooser chooser = new JFileChooser();
		if (chooser.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
			System.exit(0);
		}
		return new File(chooser.getSelectedFile() + ".png");
	}
	
	/**
	 * This private method returns the extension of the given file as a lower case String.
	 * 
	 * @param file	The file to be checked for its extension
	 * @return		The file extension or null if the file has no extension
	 */
	private static String getFileExtension(final File file) {
		final String filename = file.getName();
		final int i = filename.lastIndexOf('.');
		return (i == -1) ? null : filename.substring(i + 1, filename.length()).toLowerCase();
	}
}
