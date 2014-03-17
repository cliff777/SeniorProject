package myMMO;

public class tileEntity extends Entity {
	private Game game;
	private int idX;
	private int idY;
	public tileEntity(Game game,String ref, int x, int y,int idX,int idY) {
		super(ref, x, y);
		this.game=game;
		this.idX=idX;
		this.idY=idY;
		// TODO Auto-generated constructor stub
	}

	public void move(long delta)
	{

		x=Game.mapX+idX*32;
		y=Game.mapY+idY*32;
		super.move(delta);
	}
	public void doLogic()
	{

	}
	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub

	}

}
