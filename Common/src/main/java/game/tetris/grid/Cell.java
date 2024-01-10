package game.tetris.grid;

import java.io.Serializable;

public class Cell implements Serializable {
    TetrisColor tetrisColor;
    boolean belongToPlayer = false;

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

    public void setBelongToPlayer(boolean b){this.belongToPlayer = b;}

    public boolean getBelongToPlayer(){return belongToPlayer;}
}
