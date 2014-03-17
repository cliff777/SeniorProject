package myMMO;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class fontSheet {
	public String fPath;
	public int fWidth;
	public int fHeight;
	public int[] fPixels;

	public fontSheet(String path) 
	{
		BufferedImage fImage = null;

		try {
			fImage=ImageIO.read(spriteSheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(fImage==null)
		{
			System.out.println("No image");
			return;
		}
		this.fPath=path;
		this.fWidth=fImage.getWidth();
		this.fHeight=fImage.getHeight();

		fPixels=fImage.getRGB(0, 0, fWidth, fHeight, null, 0, fWidth);
		


		for(int i=0;i<fPixels.length;i++)
		{
			fPixels[i]=(fPixels[i]&0xff)/64;



		}
	}
}

