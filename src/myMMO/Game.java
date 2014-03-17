package myMMO;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import javax.swing.JFrame;


public class Game extends Canvas implements Runnable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	//private BufferStrategy strategy;
	private JFrame frame;
	//public static Graphics2D g;
	//private Entity test;
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	private ArrayList<Entity> removeList = new ArrayList<Entity>();
	public static boolean mainMenu = true;
	public static boolean StartGame=false;
	private boolean logicRequiredThisLoop = false;
	//private static long delta;
	static double mapX=0;
	static double mapY=0;
	public static boolean leftPressed= false;
	public static boolean rightPressed = false;
	public static boolean upPressed=false;
	public static boolean downPressed=false;
	private int moveSpeed=10;
	private int mouseX=0;
	//	private int mouseY=0;
	Entity Player;
	//Image Player;


	public boolean running = false;
	public int tickCount= 0;
	int WIDTH= 160;
	int HEIGHT=WIDTH/12*9;
	int SCALE = 7;


	private BufferedImage image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_RGB);
	private int[] pixels=((DataBufferInt)image.getRaster().getDataBuffer()).getData();
	private int[] colours = new int[6*6*6];

	private Display display;
	public KeyInputHandler input;


	//sets up the frame 
	public Game()
	{
		frame = new JFrame("I Need A Name");


		setPreferredSize(new Dimension(WIDTH*SCALE,HEIGHT*SCALE));
		frame.setLayout(new BorderLayout());

		frame.add(this,BorderLayout.CENTER);
		setIgnoreRepaint(true);

		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		//checks the red close button
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//allows for mouse and key commands to be detected
		//addKeyListener(new KeyInputHandler());
		//addMouseListener(new MouseListener());

		requestFocus();

		//	createBufferStrategy(2);
		//	strategy = getBufferStrategy();
		//displays the main menu
		//	initMainMenu();
	}
	//starts the game when called
	/*public void startGame()
	{
		//clears any keys pressed and loads entitys
		initEntities();
		leftPressed= false;
		rightPressed = false;
		upPressed=false;
		downPressed=false;
	}

	private void initMainMenu() {

		//the main menu
	//	g=(Graphics2D)strategy.getDrawGraphics();

		g.setColor(Color.BLUE);
		g.fillRect(0, 0, WIDTH*SCALE+20,HEIGHT*SCALE+20);

		g.setColor(Color.BLACK);
		g.setFont(new Font("Plain",Font.BOLD,40));
		g.drawString("Main menu!", 100, 100);

	}


	private void initEntities() {

		//draws grass
		for(int x=0;x<30;x++)
		{
			for(int y=0;y<20;y++)
			{
				Entity tileTest=new tileEntity(this,"sprites/32X32Grass.gif",(int)mapY+32*x,(int)mapX+32*y,x,y);
				entities.add(tileTest);
			}
		}
		//draws red square
		Entity test = new TestEntity(this, "sprites/EntityTest.gif",(int)mapX+500,(int)mapY+500);
		entities.add(test);
		//draws the trees
		for(int x=0;x<10;x++)
		{
			for(int y=0;y<10;y++)
			{
				if(x>=1&&y>0&&x<9)
				{
					y=y+8;
				}
				Entity tree = new TreeEntity(this,"sprites/Tree.gif",(int)mapX+x*450,(int)mapY+y*400,x,y);
				entities.add(tree);

			}
		}
		Player = new PlayerEntity(this,"playerSprite/PlayerBeginSmall.gif",160/2, 160/2);
		entities.add(Player);

	}
	public void updateLogic()
	{
		logicRequiredThisLoop=true;
	}

	//removes an entity if its no longer in the game
	public void removeEntity(Entity entity)
	{
		removeList.add(entity);
	}*/


	public void init()
	{
		int index=0;
		for(int r =0;r<6;r++)
		{
			for(int g=0;g<6;g++)
			{
				for(int b=0;b<6;b++)
				{
					int rr=(r*255/5);
					int gg=(g*255/5);
					int bb=(b*255/5);

					colours[index++]=rr<<16|gg<<8|bb;
				}
			}
		}

		display=new Display(WIDTH,HEIGHT,new spriteSheet("/sprites/spriteSheet.png"));
		input = new KeyInputHandler(this);
	}



	public synchronized void start()
	{
		running = true;
		new Thread(this).start();
	}
	//the game
	public void run()
	{
		long lastLoopTime = System.nanoTime();
		double milliTick=1000000000/60;

		int ticks=0;
		int frames = 0;

		long lastTimer=System.currentTimeMillis();
		double delta=0;


		/*URL houseTest =getClass().getClassLoader().getResource("playerSprite/playerHouse.gif");
		Image HouseTest=getToolkit().getImage(houseTest);*/

		init();

		while(running)
		{
			long now=System.nanoTime();
			delta+=(now-lastLoopTime)/milliTick;
			lastLoopTime=now;
			boolean shouldRender=true;
			while(delta>=1)
			{
				ticks++;
				tick();
				delta-=1;
				shouldRender=true;
			}
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if(shouldRender)
			{
				frames++;
				render();
			}
			if(System.currentTimeMillis()-lastTimer>=1000)
			{
				lastTimer+=1000;
				System.out.println("Ticks: "+ticks+", frames: "+frames);
				frames=0;
				ticks=0;
			}
			/*delta = System.currentTimeMillis() - lastLoopTime;
			lastLoopTime = System.currentTimeMillis();
			//int frameX = frame.getWidth();
			//int frameY = frame.getHeight();

			g = (Graphics2D) strategy.getDrawGraphics();
			if(StartGame)
			{
				startGame();
				StartGame=false;
			}


			g.setColor(new Color(mouseX/255,1,1));

			if(!mainMenu&&!StartGame)
			{
				//if not at the mainmenu then draw all the entitys
				g.drawImage(HouseTest,(int)mapX+1000,(int)mapY+1000,null);
				entityStuff();
			}
			//153,240
			//int imgY = Player.getHeight(null);
			//int imgX = Player.getWidth(null);
			g.setColor(Color.red);
			Rectangle2D headBox = new Rectangle2D.Float(820,360, 110, 95);
			Rectangle2D bodyBox = new Rectangle2D.Float(820,460, 110,118);
			g.draw(headBox);
			g.draw(bodyBox);



			if(upPressed)
			{

				mapY=mapY+moveSpeed;
			}
			if(downPressed)
			{
				mapY=mapY-moveSpeed;
			}
			if(leftPressed)
			{

				mapX=mapX+moveSpeed;
			}
			if(rightPressed)
			{

				mapX=mapX-moveSpeed;
			}

			g.dispose();
			strategy.show();
			try{Thread.sleep(10);}catch(Exception e){}*/
		}
	}

	public void tick()
	{
		tickCount++;
		//display.xOffset--;
		//display.yOffset++;
		if(input.up.isPressed())
		{
			display.yOffset--;
		}
		if(input.down.isPressed())
		{
			display.yOffset++;
		}
		if(input.left.isPressed())
		{
			display.xOffset--;
		}
		if(input.right.isPressed())
		{
			display.xOffset++;
		}

	}
	public void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if(bs==null)
		{
			createBufferStrategy(3);
			return;
		}

		for(int y = 0;y<32;y++)
		{
			for(int x =0;x<32;x++)
			{
				boolean flipX=x%2==1;
				boolean flipY=y%2==1;
				display.render(x<<4,y<<4,0,Colours.get(555,505,055,550),false,false);
			}
		}
		
		//Font.render("Hellow World!0157",display,0,0,Colours.get(000, -1, -1, 555));
		
		
		for(int y = 0;y<display.height;y++)
		{
			for(int x =0;x<display.width;x++)
			{
				int colourCode=display.pixels[x+y*display.width];
				if(colourCode<255)
				{
					pixels[x+y*WIDTH]=colours[colourCode];
				}
			}
		}
		


		Graphics g = bs.getDrawGraphics();
		g.drawRect(0,0,getWidth(),getHeight());
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);




		//g.drawImage(image, 0, 0, getWidth(), getHeight(),null);
		//	g.fillRect(0, 0, getWidth(), getHeight());

		g.dispose();
		bs.show();
	}
	public synchronized void stop()
	{
		running = false;
	}



	/*public void entityStuff()
	{
		//moves entitys
		for (int i=0;i<entities.size();i++) {
			Entity entity = (Entity) entities.get(i);

			entity.move(delta);
		}
		//draws entities
		for(int i=0;i<entities.size();i++)
		{
			Entity entity =(Entity) entities.get(i);
			entity.draw(g);

		}

		//collision
		for (int p=0;p<entities.size();p++) {
			for (int s=p+1;s<entities.size();s++) {
				Entity me = (Entity) entities.get(p);
				Entity him = (Entity) entities.get(s);

				if (me.collidesWith(him)) {
					me.collidedWith(him);
					him.collidedWith(me);
				}
			}
		}
		entities.removeAll(removeList);
		removeList.clear();

		if (logicRequiredThisLoop) {
			for (int i=0;i<entities.size();i++) {
				Entity entity = (Entity) entities.get(i);
				entity.doLogic();
			}

			logicRequiredThisLoop = false;
		}

	}*/


	public static void main(String[] args) {
		new Game().start();

	}


}
