package myMMO;

public class MapEntity extends Entity {

	@SuppressWarnings("unused")
	private Game game;
	private int mapidX;
	private int mapidY;
	public MapEntity(Game game,String ref, int x, int y,int mapidX,int mapidY) {
		super(ref, x, y);
		this.game=game;
		this.mapidX=mapidX;
		this.mapidY=mapidY;
		// TODO Auto-generated constructor stub
	}
	public void move(long delta)
	{
		x=Game.mapX+mapidX*2000;
		y=Game.mapY+mapidY*2000;
		super.move(delta);
	}

	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub

	}

}
