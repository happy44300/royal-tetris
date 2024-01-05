package game.tetris.grid;

import java.io.Serializable;

public class Cell implements Serializable {
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
