package myMMO;

public class TestEntity extends Entity {

	@SuppressWarnings("unused")
	private Game game;
	private double moveSpeed = 5;
	private double testX=500;
	private double testY=500;
	private boolean goRight = false;
	private boolean goLeft = true;

	public TestEntity(Game game, String ref, int x, int y) {
		super(ref, x, y);
		this.game=game;
		dx = -moveSpeed;
		//dy = -moveSpeed;

		// TODO Auto-generated constructor stub
	}
	public void move(long delta)
	{
		if(Game.mapX+testX<Game.mapX)
		{
			goRight = true;
			goLeft=false;
		}
		if(Game.mapX+testX>Game.mapX+600)
		{
			goLeft=true;
			goRight=false;
			
		}
		if(goRight)
		{
			testX=testX-dx;	
		}
		if(goLeft)
		{
			testX=testX+dx;
		}
		

		
		x=Game.mapX+testX;
		y=Game.mapY+testY;


		// proceed with normal move
		super.move(delta);
	}

	public void doLogic()
	{
		//dx = -dx;
		//y += 10;




	}


	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub

	}

}
