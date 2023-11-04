package game.tetris.datastructure;


public abstract class AbstractBlock {

	private Rotation rotation;
	private boolean isBlocked;
	private final TetrisColor tetrisColor;
	private Point position;


	private AbstractBlock(int x, int y) {
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

	abstract Point[] getPositions();

	abstract void move(Point point);

	abstract void rotate(Rotation dir);

	void block() {
		this.isBlocked = true;
	};

	TetrisColor getColor(){
		return tetrisColor;
	}

	abstract boolean canMove(Point point);
	abstract boolean canRotate(Rotation dir);
	boolean isBlocked() {
		return this.isBlocked;
	};

}

