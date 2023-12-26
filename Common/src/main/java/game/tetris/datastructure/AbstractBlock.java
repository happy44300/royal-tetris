package game.tetris.datastructure;


import java.rmi.RemoteException;
import java.util.function.Consumer;

public abstract class AbstractBlock {

	protected Point[] points = new Point[4];
	private Rotation rotation;
	private boolean isLocked;
	private final TetrisColor tetrisColor;
	private Point position;
	protected TetrisGrid tetrisGrid;


	public AbstractBlock(int x, int y, TetrisGrid tetrisGrid) {
		this.position = new Point(x, y);
		this.rotation = Rotation.LEFT;
		this.isLocked = false;
		this.tetrisColor = TetrisColor.NOTHING;
		this.tetrisGrid = tetrisGrid;
	}

	public Point getPosition() {
		return position;
	}

	public abstract Runnable translate(Point point) throws Exception;

	public abstract Runnable rotate(Rotation dir) throws Exception;

	public void block() {
		this.isLocked = true;
	};

	protected void unblock(){
		this.isLocked =false;
	}

	TetrisColor getColor(){
		return tetrisColor;
	}
	public void setPosition(Point p){
		this.position = p;
	}

	public void setRotation(Rotation r){
		this.rotation = r;
	}

	public abstract boolean canTranslate(Point point);

	public abstract boolean canRotate(Rotation dir);

	/**
	 * Test if this block movement has been locked, after falling down for instance
	 * @return a boolean true if this block is blocked
	 */
	boolean isLocked() {
		return this.isLocked;
	};

    public void goDown() {
		for(Point point : this.points){
			point.move(1, 0);
		}
	}

}

