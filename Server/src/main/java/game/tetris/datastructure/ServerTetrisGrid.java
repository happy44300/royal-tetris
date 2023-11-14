package game.tetris.datastructure;

import java.rmi.RemoteException;
import java.util.function.Consumer;
import java.util.function.Function;

public class ServerTetrisGrid implements TetrisGrid {


	private final int rows;
	private final int columns;
	private final Cell[][] grid;

	public ServerTetrisGrid(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		grid = new Cell[rows][columns];
		initializeGrid();
	}

	private void initializeGrid() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell clientCell = new ServerCell(i, j);
				grid[i][j] = clientCell;
			}
		}
	}

	@Override
	public boolean isInLimits(Point point) {

		if(point.getX() < 0 || point.getX() > this.rows){
			return false;
		}

		if(point.getY() < 0 || point.getY() > this.columns){
			return false;
		}

		return true;
	}

	@Override
	public Cell getCell(Point point) throws RemoteException {
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

	// bizarre le meme nom '-'
	@Override
	public <T> T updateGridSynchronously(Function<TetrisGrid, T> gridConsumer) throws RemoteException {
		//TODO
		return null;
	}

	@Override
	public void updateGridSynchronously(Consumer<TetrisGrid> tetrisGridObjectFunction) throws RemoteException {
		//TODO
	}

}
