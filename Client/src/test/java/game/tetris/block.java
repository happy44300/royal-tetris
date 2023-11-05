package game.tetris;

import game.tetris.datastructure.*;

class Block extends AbstractBlock {
    TetrisGridClient client;

    public Block(int x, int y, TetrisGrid tetrisGrid, TetrisGridClient client) {
        super(x, y, tetrisGrid);
        this.client = client;
    }

    @Override
    public void translate(Point point) {
        this.setPosition(point);
    }

    @Override
    public void rotate(Rotation dir) {
        this.setRotation(dir);
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
