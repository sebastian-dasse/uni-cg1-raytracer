package raytracer.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import raytracer.ui.FileDialog;

/**
<<<<<<< HEAD
 * TODO
 * 
 * @author 
 *
 */
=======
 * This class shows graphical image by creating an ImageIcon from an image
object, that was choosed by class FileDialog.
 */

>>>>>>> e45ac2a8989689378da9f6379de5f7c2e97c4f70
public class ImageViewer {
    /**
     * @args
     * @throws IOException if it is not possible to read the resulting
stream by registered ImageReader.
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