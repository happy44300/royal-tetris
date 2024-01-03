package game.tetris.datastructure;

public class ClientTetrisGrid {
    public static final int CELL_SIZE = 30;

    private final int rows;
    private final int columns;
    public ClientTetrisGrid(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        /*grid = new ClientCell[rows][columns];
        initializeGrid();*/
    }
}
