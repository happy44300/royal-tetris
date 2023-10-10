package game.tetris.tetrominos;

import game.tetris.TetrisGrid;
import game.tetris.datasrtucture.Direction;

import java.util.function.Consumer;

public abstract class AbstractBlock {

	private final TetrisGrid grid;

	private AbstractBlock(TetrisGrid grid) {
		this.grid = grid;
	}

	//TODO: need to think about when to lock things, for instance there is no need to lock the grid if no write happen
	protected void updateGridSynchronously(Consumer<TetrisGrid> gridConsumer){
		grid.updateGridSynchronously(gridConsumer);
	}

	abstract void move(int x, int y);
	abstract void rotate(Direction dir);

	abstract boolean canMove(int x, int y);
	abstract boolean canRotate(Direction dir);

}
