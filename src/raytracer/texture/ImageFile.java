package raytracer.texture;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageFile {
	BufferedImage image;
	
	public ImageFile(String path) {
		if (path == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			System.err.println("Problem reading file.");
			throw new RuntimeException("Could not construct image texture from the specified path.");
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
}
