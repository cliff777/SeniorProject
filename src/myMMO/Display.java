package myMMO;

public class Display {
	public static final int MAP_WIDTH=64;
	public static final int MAP_WIDTH_MASK=MAP_WIDTH-1;

	public int[]pixels;

	public int xOffset=0;
	public int yOffset=0;
	public int width;
	public int height;

	public spriteSheet sheet;
	public Display(int width,int height,spriteSheet sheet)
	{
		this.width=width;
		this.height=height;
		this.sheet=sheet;

		pixels=new int[width*height];
	}
	public void render(int xPos,int yPos,int tile,int colour)
	{
		render(xPos,yPos,tile,colour,false,false);
	}

	public void render(int xPos,int yPos,int tile,int colour,boolean mirrorX,boolean mirrorY)
	{
		xPos-=xOffset;
		yPos-=yOffset;

		int xTile=tile%16;
		int yTile=tile/16;
		int tileOffset=(xTile<<4)+(yTile<<4)*sheet.width;
		for(int y=0;y<16;y++)
		{

			if(y+yPos<0||y+yPos>=height)
			{
				continue;
			}
			int ySheet=y;
			if(mirrorY)
			{
				ySheet=16-y;
			}
			for(int x=0;x<16;x++)
			{

				if(x+xPos<0||x+xPos>=width)
				{
					continue;
				}
				int xSheet=x;
				if(mirrorX)
				{
					xSheet=16-x;
				}
				int col=(colour>>(sheet.pixels[xSheet+ySheet*sheet.width+tileOffset]*8))&255;
				if(col<255)
				{
					pixels[(x+xPos)+(y+yPos)*width]=col;
				}
			}
		}
	}
}