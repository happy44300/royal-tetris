package game.tetris.grid;

import game.tetris.grid.Cell;
import game.tetris.grid.Grid;
import game.tetris.grid.Point;

//This need to be in common or else the server and client will have different versions of the class and rmi will try to serialize it
// but since rmi is deprecated serialization does not work because of the deprecated security manager
public class BasicGrid implements Grid {

    Cell[][] grid;
    int rows;
    int columns;


    //Constructor and Initialization methods
    public BasicGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Cell[rows][columns];
        this.initializeGrid();
    }

    public BasicGrid(int rows, int columns, TetrisColor fillingColor) {
        this.rows = rows;
        this.columns = columns;
        this.grid = new Cell[rows][columns];
        this.initializeColoredGrid(fillingColor);
    }

    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = new Cell();
                grid[i][j] = cell;
            }
        }
    }

    private void initializeColoredGrid(TetrisColor color) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell cell = new Cell(color);
                grid[i][j] = cell;
            }
        }
    }


    //Interface Methods
    @Override
    public Cell getCell(Point point) {
        return this.grid[point.getY()][point.getX()];
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
