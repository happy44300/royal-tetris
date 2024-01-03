package game.tetris.block;

import game.tetris.grid.Point;
import game.tetris.action.Rotate;

public class OBlock extends Block {
    public OBlock() {
        this.type = BlockType.OBLOCK;
    }

    public OBlock(int x, int y) {
        this.type = BlockType.OBLOCK;

        this.points[0] = new Point(x,y);
        this.points[1] = new Point(x+1,y);
        this.points[2] = new Point(x,y+1);
        this.points[3] = new Point(x+1,y+1);
    }

    @Override
    public Point[] getPoints() {
        return this.points;
    }

    @Override
    public Point[] computeRotation(Rotate rotate) {
        return this.points;

    }
}
