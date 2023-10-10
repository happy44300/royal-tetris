package game.tetris;

import com.almasb.fxgl.entity.Entity;
import game.tetris.datastructure.ClientCell;

import java.util.function.Consumer;

public class TetrisGrid {

	public static final int CELL_SIZE = 30;

	private final int rows;
	private final int columns;
	private final ClientCell[][] gridEntities;

	public TetrisGrid(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		gridEntities = new ClientCell[rows][columns];
		initializeGrid();
	}

	private void initializeGrid() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				ClientCell clientCell = new ClientCell();
				clientCell.setPosition(j * (CELL_SIZE +1d), i* ( CELL_SIZE +1d)); // Adjust the position based on cell size
				gridEntities[i][j] = clientCell;
			}
		}
	}

	public void updateGridSynchronously(Consumer<TetrisGrid> gridModification){
		synchronized (this){
			gridModification.accept(this);
		}
	}

	public ClientCell readGrid(int x, int y){
		//The copy is here to make sure we do not make an unsafe write to the cell
		//Todo add syncronisation to prevent reading while a write is in progress
		return (ClientCell) gridEntities[x][y].copy();
	}

	public Entity[][] getGridEntities() {
		return gridEntities;
	}
}
