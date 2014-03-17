package myMMO;

public class TreeEntity extends Entity {
	@SuppressWarnings("unused")
	private Game game;
	private int treeidX;
	private int treeidY;
	public TreeEntity(Game game,String ref, int x, int y, int treeidX,int treeidY) {
		super(ref, x, y);
		this.game=game;
		this.treeidX=treeidX;
		this.treeidY=treeidY;
		// TODOAuto-generated constructor stub
	}

	
	public void move(long delta)
	{
		
		x=Game.mapX+treeidX*450;
		y=Game.mapY+treeidY*350;
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
