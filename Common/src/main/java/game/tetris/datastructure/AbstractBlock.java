package game.tetris.datastructure;


public abstract class AbstractBlock {

	private Rotation rotation;
	private boolean isBlocked;
	private final TetrisColor tetrisColor;
	private Point position;


	public AbstractBlock(int x, int y) {
		this.position = new Point(x, y);
		this.rotation = Rotation.UP;
		this.isBlocked = false;
		this.tetrisColor = TetrisColor.NOTHING;
	}

	private AbstractBlock(Point origin) {
		this.position = origin;
		this.rotation = Rotation.UP;
		this.isBlocked = false;
		this.tetrisColor = TetrisColor.NOTHING;
	}

	public abstract Point[] getPositions();

	public abstract void move(Point point);

	public abstract void rotate(Rotation dir);

	void block() {
		this.isBlocked = true;
	};

	TetrisColor getColor(){
		return tetrisColor;
	}
	public void setPosition(Point p){
		this.position = p;
	}

	public void setRotation(Rotation r){
		this.rotation = r;
	}
	public abstract boolean canMove(Point point);
	public abstract boolean canRotate(Rotation dir);
	boolean isBlocked() {
		return this.isBlocked;
	};

}

