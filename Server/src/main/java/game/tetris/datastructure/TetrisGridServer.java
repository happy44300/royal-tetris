package game.tetris.datastructure;

import java.util.function.Consumer;

public class TetrisGridServer implements TetrisGrid {


	private final int rows;
	private final int columns;
	private final Cell[][] cell;

	public TetrisGridServer(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		cell = new Cell[rows][columns];
		initializeGrid();
	}

	private void initializeGrid() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell clientCell = new ServerCell();
				cell[i][j] = clientCell;
			}
		}
	}

	@Override
	public void updateGridSynchronously(Consumer<TetrisGrid> gridModification){
		synchronized (this){
			gridModification.accept(this);
		}
	}

	@Override
	public void updateGridUnsafe(Consumer<TetrisGrid> gridConsumer) {
		//TODO: implement
		throw new RuntimeException("Not implemented");
	}



}
