package game.tetris.block;

import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.Point;
import game.tetris.datastructure.TetrisColor;
import game.tetris.datastructure.TetrisGrid;

public abstract class ServerBlock extends AbstractBlock {

    Point[] points = new Point[4];
    TetrisColor COLOR;

    public ServerBlock(int x, int y, TetrisGrid tetrisGrid) {
        super(x, y, tetrisGrid);
    }
}
