package myMMO;

public class Font {
	private static String chars=""+"ABCDEFGHIJKLMNOPQRSTUVWXYZ      "+"0123456789.,:;'\"!?$%()-=+/      " ;

	public static void render(String msg, Display display, int x, int y, int colour)
	{
		msg=msg.toUpperCase();
		for(int i=0;i<msg.length();i++)
		{
			int charIndex = chars.indexOf(msg.charAt(i));
			if(charIndex>=0)
			{
				System.out.println("X: "+x+" Y: "+y);
				display.render(x+(i*16), y,charIndex+(15*16), colour);
			}
		}
	}
}



