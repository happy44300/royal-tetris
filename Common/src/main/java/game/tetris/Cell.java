package game.tetris;

public class Cell {
    TetrisColor tetrisColor;

    public Cell() {
        this.tetrisColor = TetrisColor.NOTHING;
    }

    public Cell(TetrisColor tetrisColor) {
        this.tetrisColor = tetrisColor;
    }

    public TetrisColor getColor() {
        return tetrisColor;
    }

    public void setColor(TetrisColor tetrisColor) {
        this.tetrisColor = tetrisColor;
    }
}
