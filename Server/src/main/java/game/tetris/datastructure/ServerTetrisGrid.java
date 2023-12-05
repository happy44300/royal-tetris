package game.tetris.datastructure;

import game.tetris.Server;

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
			return gameAction.apply(this);
		}
	}

	@Override
	public void updateGridSynchronously(Consumer<TetrisGrid> gameAction){
		synchronized (this){
			gameAction.accept(this);
		}
	}

	@Override
	public void removeCompletedLines() {
		int numLine = 0;
		for (ServerCell[] cells : this.grid){
			if(this.areBlocked(cells)){
				this.removeLine(numLine);
			}
			numLine += 1;
		}
	}

	private void removeLine(int numLine) {
		this.shiftUpLinesDown(numLine);
		this.addOnTopEmptyLine();
	}

	private void shiftUpLinesDown(int numLine) {
		for(int i = 0; i < numLine; i++){
			this.grid[i] = this.grid[i+1];
		}
	}

	private void addOnTopEmptyLine() {
		ServerCell[] newLine = new ServerCell[columns];
		for (int j = 0; j < columns; j++) {
			newLine[j] = new ServerCell(0, j);
		}
		this.grid[0] = newLine;
	}

	private boolean areBlocked(ServerCell[] cells) {
		for (ServerCell cell : cells){
			if(!cell.getIsBlocked()) {
				return false;
			}
		}
		return true;
	}


}
