package game.tetris.datastructure;

import java.rmi.RemoteException;
import java.util.function.Consumer;
import java.util.function.Function;

public class ServerTetrisGrid implements TetrisGrid {

	private final int rows;
	private final int columns;
	private final ServerCell[][] grid;

	public ServerTetrisGrid(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		grid = new ServerCell[rows][columns];
		initializeGrid();
	}

	private void initializeGrid() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				ServerCell clientCell = new ServerCell(i, j);
				grid[i][j] = clientCell;
			}
		}
	}

	public boolean isNotInLimits(Point point) {
		return (point.getY() < 0
				|| point.getY() > this.columns
				|| point.getX() < 0
				|| point.getX() > this.rows);
	}

	public boolean isCellBlocked(Point point) {
		return grid[point.getX()][point.getY()].getIsBlocked();
	}

	@Override
	public Cell getCell(Point point) {
		return this.grid[point.getX()][point.getY()];
	}

	@Override
	public int getRows() {
		return this.rows;
	}

	@Override
	public int getColumns() {
		return this.columns;
	}

	@Override
	public <T> T updateGridSynchronously(Function<TetrisGrid, T> gameAction) {
		synchronized (this){
			if(this.isMoveValid(gameAction))
				return gameAction.apply(this);

			return null;
		}

	}

	private <T> boolean isMoveValid(Function<TetrisGrid,T> gameAction) {
		//TODO: implement check, LESS IMPORTANT THAN OTHER ISMOVEVALID METHOD
		return false;
	}

	@Override
	public void updateGridSynchronously(Consumer<TetrisGrid> gameAction) {
		synchronized (this){
			if(this.isMoveValid(gameAction))
				gameAction.accept(this);

		}
	}

	private boolean isMoveValid(Consumer<TetrisGrid> gameAction) {
		//TODO: implement check
		return false;
	}

}
