package game.tetris.datastructure;


import java.rmi.RemoteException;
import java.util.function.Consumer;

public abstract class AbstractBlock {

	private final TetrisGrid grid;
	private Rotation rotation;
	private boolean isBlocked;

	private final Color color;

	private AbstractBlock(TetrisGrid grid) {
		this.grid = grid;
		this.rotation = Rotation.UP;
		this.isBlocked = false;
		this.color = Color.NOTHING;
	}

	//TODO: need to think about when to lock things, for instance there is no need to lock the grid if no write happen
	protected void updateGridSynchronously(Consumer<TetrisGrid> gridConsumer){
		try {
			grid.updateGridSynchronously(gridConsumer);
		} catch (RemoteException e) {
			throw new RuntimeException(e);
		}
	}

	abstract void move(Point point);
	abstract void rotate(Rotation dir);

	abstract void block();

	Color getColor(){
		return color ;
	}

	abstract boolean canMove(Point point);
	abstract boolean canRotate(Rotation dir);
	abstract boolean canChangeColor();
	abstract boolean isBlocked();

}
