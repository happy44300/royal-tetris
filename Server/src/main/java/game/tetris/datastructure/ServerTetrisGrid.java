package game.tetris.datastructure;

import java.rmi.RemoteException;
import java.util.function.Consumer;

public class ServerTetrisGrid implements TetrisGrid {


	private final int rows;
	private final int columns;
	private final Cell[][] cell;

	public ServerTetrisGrid(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		cell = new Cell[rows][columns];
		initializeGrid();
	}

	private void initializeGrid() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell clientCell = new ServerCell(i, j);
				cell[i][j] = clientCell;
			}
		}
	}

	@Override
	public void updateGridSynchronously(Consumer<TetrisGrid> gridModification) {
		synchronized (this){
			gridModification.accept(this);
		}
	}

}
