package game.tetris.block;

import game.tetris.BlockType;
import game.tetris.Point;

public class SRBlock extends Block {
    public SRBlock() {
        this.type = BlockType.SRBLOCK;
    }

    public SRBlock(int x, int y) {
        this.type = BlockType.SRBLOCK;
        this.origin = new Point(x, y);
    }

    @Override
    public Point[] getPoints() {
        Point[] points = new Point[4];

        int x = this.origin.getX();
        int y = this.origin.getY();

        points[0] = new Point(x,y);
        points[1] = new Point(x,y+1);
        points[2] = new Point(x+1,y+1);
        points[3] = new Point(x+1,y+2);

        return points;
    }
}
