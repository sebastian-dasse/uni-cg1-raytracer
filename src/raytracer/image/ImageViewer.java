package raytracer.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import raytracer.ui.FileDialog;

/**
 * This class allows the user to choose, open and view an image file.
 * <p>
 * The <code>open()</code> dialog of the <code>FileDialog</code> utility class is being used for the file selection.
 * 
 * @author Maxim Novichkov
 *
 */

public class ImageViewer {
    /**
     * Opens up a selection dialog and shows the chosen image inside a <code>JFrame</code>.
     * 
     * @args
     * @throws IOException if it is not possible to read the resulting stream by the registered ImageReader.
     */
    public static void main(final String[] args) throws IOException {
        final BufferedImage image = ImageIO.read(FileDialog.open());
        final JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}