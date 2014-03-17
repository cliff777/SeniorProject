package myMMO;

public class PlayerEntity extends Entity{
	@SuppressWarnings("unused")
	private Game game;
	public PlayerEntity(Game game, String ref, int x, int y) {
		super(ref,x,y);
		this.game=game;
	}
	public void move(long delta)
	{
		super.move(delta);
	}
	@Override
	public void collidedWith(Entity other) {
		// TODO Auto-generated method stub

	}

}
