package game.tetris.datastructure;

import com.almasb.fxgl.entity.Entity;

import java.util.ArrayList;
import java.util.List;

public class ClientTetrisGrid implements TetrisGrid {

	public static final int CELL_SIZE = 30;

	private final int rows;
	private final int columns;
	private final ClientCell[][] grid;

	public ClientTetrisGrid(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		grid = new ClientCell[rows][columns];
		initializeGrid();
	}

	private void initializeGrid() {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				ClientCell clientCell = new ClientCell();
				clientCell.setPosition(j * (CELL_SIZE +1d), i* ( CELL_SIZE +1d)); // Adjust the position based on cell size
				grid[i][j] = clientCell;
			}
		}
	}

	@Override
	public ClientCell getCell(Point point){
		return grid[point.getX()][point.getY()];
	}

	@Override
	public int getRows() {
		return rows;
	}

	@Override
	public int getColumns() {
		return columns;
	}

	@Override
	public void updateGrid(Runnable gameAction) {
		gameAction.run();
	}

	@Override
	public List<Integer> removeCompletedLines() {
		//TODO: implement
		//This is mostly useful for server side grid, though it would be very convenient to have it implemented client side as well
		return new ArrayList<>();
	}

	public void removeLine(int numLine) {
		this.shiftUpLinesDown(numLine);
		this.addOnTopEmptyLine();
	}

	private void shiftUpLinesDown(int numLine) {
		for(int i = 0; i < numLine; i++){
			this.grid[i] = this.grid[i+1];
		}
	}

	private void addOnTopEmptyLine() {
		ClientCell[] newLine = new ClientCell[columns];
		for (int j = 0; j < columns; j++) {
			newLine[j] = new ClientCell(0, j);
		}
		this.grid[0] = newLine;
	}

	public Entity[][] getGrid() {
		return grid;
	}

}
