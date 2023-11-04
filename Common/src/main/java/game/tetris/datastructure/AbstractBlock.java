package game.tetris.datastructure;


import java.rmi.RemoteException;
import java.util.function.Consumer;

public abstract class AbstractBlock {

	private Rotation rotation;
	private boolean isBlocked;
	private final Color color;
	private Point position;


	public AbstractBlock(int x, int y) {
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

	public abstract Point[] getPositions();

	public abstract void move(Point point);

	public abstract void rotate(Rotation dir);

	void block() {
		this.isBlocked = true;
	};

	Color getColor(){
		return color ;
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

