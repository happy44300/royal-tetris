package game.tetris;

import com.almasb.fxgl.entity.Entity;
import game.tetris.datastructure.ClientCell;
import game.tetris.datastructure.Point;
import game.tetris.datastructure.TetrisGrid;
import kotlin.NotImplementedError;

import java.rmi.RemoteException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public class TetrisGridClient implements TetrisGrid {

	public static final int CELL_SIZE = 30;

	private final int rows;
	private final int columns;
	private final ClientCell[][] gridEntities;

	public TetrisGridClient(int rows, int columns) {
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

	@Override
	public <T> T updateGrid(Function<TetrisGrid, T> gridModification){
		//Client noes not need any synchronisation on it's grid
		return gridModification.apply(this);
	}


	@Override
	public ClientCell getCell(Point point){
		return gridEntities[point.getX()][point.getY()];
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
	public void updateGrid(Consumer<TetrisGrid> tetrisGridObjectFunction) {
		tetrisGridObjectFunction.accept(this);
	}

	@Override
	public Collection<Integer> removeCompletedLines() {
		//TODO: implement
		//This is mostly useful for server side grid, though it would be very convenient to have it implemented client side as well
		return new ArrayList<>();
	}

	public Entity[][] getGridEntities() {
		return gridEntities;
	}

}
