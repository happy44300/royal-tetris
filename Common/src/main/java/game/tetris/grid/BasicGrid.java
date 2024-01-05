package game.tetris.grid;

import game.tetris.grid.Cell;
import game.tetris.grid.Grid;
import game.tetris.grid.Point;

//This need to be in common or else the server and client will have different versions of the class and rmi will try to serialize it
// but since rmi is deprecated serialization does not work because of the deprecated security manager
public class BasicGrid implements Grid {

    public static final int CELL_SIZE = 30;
    Cell[][] grid;
    int columns;
    int rows;


    //Constructor and Initialization methods
    public BasicGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Cell[rows][columns];
        this.initializeGrid();
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = new Cell();
                grid[i][j] = cell;
            }
        }
    }


    //Interface Methods
    @Override
    public Cell getCell(Point point) {
        return this.grid[point.getX()][point.getY()];
    }

    @Override
    public Cell[][] getCells() {
        return this.grid;
    }

    @Override
    public int getNumberOfRows() {
        return this.rows;
    }

    @Override
    public int getNumberOfColumns() {
        return this.columns;
    }
}
