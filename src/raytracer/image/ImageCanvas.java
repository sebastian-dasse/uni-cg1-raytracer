package raytracer.image;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ComponentSampleModel;
import java.awt.image.SampleModel;
import java.awt.image.WritableRaster;

public class ImageCanvas extends Canvas {
	private BufferedImage image;
	
	public void paint(final Graphics g) {
		super.paint(g);
		SampleModel sampleModel
		ColorModel colorModel = this.getColorModel();
		
		WritableRaster raster = new WritableRaster();
		this.image = new BufferedImage(colorModel);
	}
	
}
