package game.tetris;

public class BasicGrid implements Grid{
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


    //Intern methods
}
