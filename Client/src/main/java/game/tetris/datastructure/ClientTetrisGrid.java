package game.tetris.datastructure;

import game.tetris.grid.Cell;

public class ClientTetrisGrid {
    public static final int CELL_SIZE = 30;

    private final int rows;
    private final int columns;

    private final Cell[][] grid;
    public ClientTetrisGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        grid = new Cell[rows][columns];
        initializeGrid();
    }
    private void initializeGrid() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                Cell clientCell = new Cell();
                //TODO
                //clientCell.setPosition(j * (CELL_SIZE +1d), i* ( CELL_SIZE +1d)); // Adjust the position based on cell size
                grid[i][j] = clientCell;
            }
        }
    }
}
