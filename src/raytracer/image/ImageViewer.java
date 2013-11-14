package raytracer.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import raytracer.ui.FileDialog;

/**
 * This class shows a graphical image by creating an ImageIcon from an image object, that was chosen by class FileDialog.
 * 
 * @author Maxim Novichkov
 *
 */

public class ImageViewer {
    /**
     * Opens FileDialog and loads selected image into 
     * the JFrame.
     * @args
     * @throws IOException if it is not possible to read the resulting stream by registered ImageReader.
     */
    public static void main(String[] args) throws IOException {
        /**
         * The image describes an accessible buffer of image data.
         */
        final BufferedImage image = ImageIO.read(FileDialog.open());
        /**
         * The frame represent a top-level container.
         */
        final JFrame frame = new JFrame();
        frame.getContentPane().add(new JLabel(new ImageIcon(image)));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}