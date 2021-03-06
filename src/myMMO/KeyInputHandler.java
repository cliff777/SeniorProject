package myMMO;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;



public class KeyInputHandler implements KeyListener {
	
	public KeyInputHandler(Game game)
	{
		game.addKeyListener(this);
	}
	
	public class Key{
		private int numTimesPressed=0;
		private boolean pressed = false;
		
		public int getNumTimesPressed()
		{
			return numTimesPressed;
		}
		
		public boolean isPressed()
		{
			return pressed;
		}
		
		public void toggle(boolean isPressed)
		{
			pressed=isPressed;
			if(isPressed)numTimesPressed++;
		}
	}

	
	
	public Key up = new Key();
	public Key down = new Key();
	public Key left = new Key();
	public Key right = new Key();
	
	
	public void keyPressed(KeyEvent pressed) {
		toggleKey(pressed.getKeyCode(),true);
	}

	
	public void keyReleased(KeyEvent released) {
		toggleKey(released.getKeyCode(),false);

	}

	
	public void keyTyped(KeyEvent typed) {
	

	}
	public void toggleKey(int keyCode, boolean isPressed)
	{
		if(keyCode==KeyEvent.VK_W)
		{
			up.toggle(isPressed);
			
		}
		if(keyCode==KeyEvent.VK_A)
		{
			left.toggle(isPressed);
		}
		if(keyCode==KeyEvent.VK_S)
		{
			down.toggle(isPressed);
		}
		if(keyCode==KeyEvent.VK_D)
		{
			right.toggle(isPressed);
		}
	}

}
