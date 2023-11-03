package game.tetris.datastructure;


import java.rmi.RemoteException;
import java.util.function.Consumer;

public abstract class AbstractBlock {

	private Rotation rotation;
	private boolean isBlocked;
	private final Color color;
	private Point position;


	private AbstractBlock(int x, int y) {
		this.position = new Point(x, y);
		this.rotation = Rotation.UP;
		this.isBlocked = false;
		this.color = Color.NOTHING;
	}

	private AbstractBlock(Point origin) {
		this.position = origin;
		this.rotation = Rotation.UP;
		this.isBlocked = false;
		this.color = Color.NOTHING;
	}

	abstract Point[] getPositions();

	abstract void move(Point point);

	abstract void rotate(Rotation dir);

	void block() {
		this.isBlocked = true;
	};

	Color getColor(){
		return color ;
	}

	abstract boolean canMove(Point point);
	abstract boolean canRotate(Rotation dir);
	boolean isBlocked() {
		return this.isBlocked;
	};

}

