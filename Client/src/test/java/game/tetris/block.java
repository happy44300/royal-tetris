package game.tetris;

import game.tetris.datastructure.*;

class Block extends AbstractBlock {
    ClientTetrisGrid client;

    public Block(int x, int y, TetrisGrid tetrisGrid, ClientTetrisGrid client) {
        super(x, y, tetrisGrid);
        this.client = client;
    }


    @Override
    public Runnable translate(Point point) throws Exception {
        return null;
    }

    @Override
    public Runnable rotate(Rotation dir) throws Exception {
        return null;
    }

    @Override
    public boolean canTranslate(Point point) {
        return true;
    }

    @Override
    public boolean canRotate(Rotation dir) {
        return true;
    }
}
