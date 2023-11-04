package game.tetris;

import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.Color;
import game.tetris.datastructure.Point;
import game.tetris.datastructure.Rotation;

class Block extends AbstractBlock {

    private Block(int x, int y) {
        super(x,y);
    }

    public Point[] getPositions() {
        return new Point[0];
    }

    @Override
    public void move(Point point) {

    }

    @Override
    public void rotate(Rotation dir) {

    }

    @Override
    public boolean canMove(Point point) {
        return false;
    }

    @Override
    public boolean canRotate(Rotation dir) {
        return false;
    }
}
