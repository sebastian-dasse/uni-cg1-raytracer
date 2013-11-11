package raytracer.ui;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * TODO
 * 
 * @author 
 *
 */
public class FileDialog {
	
	/**
	 * TODO
	 * 
	 * @return
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
	 * TODO
	 * 
	 * @return
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
