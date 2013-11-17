package raytracer.ui;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * This utility class contains selection dialogs for opening and saving JPG and PNG images.
 * 
 * @author Max Novichkov
 * @author Sebastian Dass&eacute;
 * @author Simon Lischka
 *
 */
public final class FileDialog {
	
	/**
	 * Pops up an "open" file chooser dialog with a predefined filter for JPG and PNG images. Prompts the user for file 
	 * selection until a valid selection is made or the dialog is cancelled.
	 * 
	 * @return The selected <code>File</code>.
	 * @throws IOException
	 */
	public static File open() throws IOException {
		final JFileChooser chooser = new JFileChooser();
		final FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "JPG & PNG Images", "jpg", "jpeg", "png");
		chooser.setFileFilter(filter);
		File file = null;
		do {
			if (chooser.showOpenDialog(null) != JFileChooser.APPROVE_OPTION) {
				System.exit(0);
			}
			file = chooser.getSelectedFile();
		} while (isValid(file));
		return file;
	}

	/**
	 * Pops up a "save" file chooser dialog and allows the user to save the current image as a PNG file. 
	 * The .png filename extension is appended to the entered filename automatically.
	 * 
	 * @return The selected <code>File</code>.
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
	 * @param file	The file to be checked for its extension.
	 * @return		The filename extension or null if the filename has no extension.
	 */
	private static String getFileExtension(final File file) {
		final String filename = file.getName();
		final int i = filename.lastIndexOf('.');
		return (i == -1) ? null : filename.substring(i + 1, filename.length()).toLowerCase();
	}
	
	/**
	 * Checks the specified file for validity, i.e. whether or not it has one of three allowed filename extensions: 
	 * ".jpg", ".jpeg" or ".png".
	 * 
	 * @param file	The file to be validated.
	 * @return		<code>true</code> if the specified file is valid.
	 */
	private static boolean isValid(File file) {
		String e = getFileExtension(file);
		return e == null || (!e.equals("jpg") && !e.equals("jpeg") && !e.equals("png"));
	}
}
