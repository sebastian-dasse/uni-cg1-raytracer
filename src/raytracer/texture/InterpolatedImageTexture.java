package raytracer.texture;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import raytracer.Color;

public class InterpolatedImageTexture implements Texture{
//	private final BufferedImage image;
	private final Raster imageRaster;
	private final int widthMinus1;
	private final int heightMinus1;
	private boolean originAtBottom;
	
	public InterpolatedImageTexture(final String path){
		if (path == null) {
			throw new IllegalArgumentException("The parameters must not be null.");
		}
		try {
			BufferedImage image = ImageIO.read(new File(path));
			imageRaster = image.getData();
			heightMinus1 = imageRaster.getHeight() - 1;
			widthMinus1 = image.getWidth() - 1;
		} catch (IOException e) {
			System.err.println("Problem reading file.");
			throw new RuntimeException("Could not construct image texture from the specified path.");
		}
		originAtBottom = true;
	}
	
	public void setOriginAtBottom(boolean originAtBottom) {
		this.originAtBottom = originAtBottom;
	}
	
	@Override
	public Color getColor(final double u, final double v) {
		final double mappedU =  (u * widthMinus1);
	    final double mappedV =  (v * heightMinus1);
	    final double resultingX = mappedU;
	    final double resultingY;
	    if (originAtBottom) {
		    resultingY = heightMinus1 - mappedV;
	    } else {
	    	resultingY = mappedV;
	    }
	    
	    double xa = resultingX - Math.floor(resultingX);
	    double ya = resultingY - Math.floor(resultingY);
	    
//	    val a = image.getRGB(math.floor( x ).asInstanceOf[Int], math.floor( y ).asInstanceOf[Int])
//	    val b = image.getRGB(math.ceil( x ).asInstanceOf[Int], math.floor( y ).asInstanceOf[Int])
//	    val c = image.getRGB(math.floor( x ).asInstanceOf[Int], math.ceil( y ).asInstanceOf[Int])
//	    val d = image.getRGB(math.ceil( x ).asInstanceOf[Int], math.ceil( y ).asInstanceOf[Int])
	    
	    final int[] a = imageRaster.getPixel((int)Math.floor(resultingX), (int)Math.floor(resultingY), new int[3]);
	    final int[] b = imageRaster.getPixel((int)Math.ceil(resultingX), (int)Math.floor(resultingY), new int[3]);
	    final int[] c = imageRaster.getPixel((int)Math.floor(resultingX), (int)Math.ceil(resultingY), new int[3]);
	    final int[] d = imageRaster.getPixel((int)Math.ceil(resultingX), (int)Math.ceil(resultingY), new int[3]);
	    
//	    val (redA,greenA,blueA) = extract( a ) extract( argb : Int ) = ((argb & 0xff0000) >> 16,(argb & 0xff00) >> 8, argb & 0xff)
//	    val (redB,greenB,blueB) = extract( b )
//	    val (redC,greenC,blueC) = extract( c )
//	    val (redD,greenD,blueD) = extract( d )
	    
	    Color a1 = new Color(a[0], a[1], a[2]);
	    Color b1 = new Color(b[0], b[1], b[2]);
	    Color c1 = new Color(c[0], c[1], c[2]);
	    Color d1 = new Color(d[0], d[1], d[2]);
	    
//	    val (redE,greenE,blueE) = (redA*(1.0-xa) + (redB * xa), greenA*(1.0-xa) + (greenB * xa), blueA*(1.0-xa) + (blueB * xa) )
//	    val (redF,greenF,blueF) = (redC*(1.0-xa) + (redD * xa), greenC*(1.0-xa) + (greenD * xa), blueC*(1.0-xa) + (blueD * xa) )
	    
	    Color a2 = new Color (a1.r*(1.0-xa) + (b1.r * xa), a1.g*(1.0-xa) + (b1.g * xa), a1.b*(1.0-xa) + (b1.b * xa));
	    Color b2 = new Color (c1.r*(1.0-xa) + (d1.r * xa), c1.g*(1.0-xa) + (d1.g * xa), c1.b*(1.0-xa) + (d1.b * xa));

//	    val (red,green,blue) = (redE*(1.0-ya) + redF *ya , greenE* (1.0-ya) + greenF * ya, blueE*(1.0-ya) + blueF * ya )
	    
	    Color fin = new Color(a2.r*(1.0-ya) + b2.r *ya , a2.g* (1.0-ya) + b2.g * ya, a2.b*(1.0-ya) + b2.b * ya);

	    return new Color(fin.r/255, fin.g/255, fin.b/255);
	    
	}

	@Override
	public Color getColor(final TexCoord2 textcoord) {
		return getColor(textcoord.u, textcoord.v);
	}

//	//---- Test
//	public static void main(String [] args) {
//		ImageTexture texture = new ImageTexture("textures/colorTest.jpg");
//		texture.setOriginAtBottom(true);
//		texture.getColor(0.1,0.1);
//		final double x = 0.5;
//		final double y = 0.1;
//		System.out.println(texture.getColor(x, y).r);
//		System.out.println(texture.getColor(x, y).g);
//		System.out.println(texture.getColor(x, y).b);
//	}
}
