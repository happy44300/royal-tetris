package game.tetris.block;

import game.tetris.grid.Point;
import game.tetris.action.Rotate;
import game.tetris.grid.TetrisColor;

public class ZBlock extends Block {
    public ZBlock() {
        this.type = BlockType.ZBLOCK;
    }

    public ZBlock(int x, int y) {
        this.type = BlockType.ZBLOCK;
        this.color = TetrisColor.GREEN;
        this.points[0] = new Point(x,y);
        this.points[1] = new Point(x-1,y);
        this.points[2] = new Point(x-1,y+1);
        this.points[3] = new Point(x-2,y+1);
    }

    @Override
    public Point[] computeRotation(Rotate rotate) {
        Point[] computedPoints = new Point[4];
        int x = this.points[0].getX();
        int y = this.points[0].getY();

        Orientation newOrientation = computeNewOrientation(rotate);

        switch(newOrientation){
            case UP:
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x,y-1);
                computedPoints[2] = new Point(x-1,y-1);
                computedPoints[3] = new Point(x-1,y-2);
                break;

            case LEFT:
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x-1,y);
                computedPoints[2] = new Point(x-1,y+1);
                computedPoints[3] = new Point(x-2,y+1);
                break;

            case RIGHT:
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x+1,y);
                computedPoints[2] = new Point(x+1,y-1);
                computedPoints[3] = new Point(x+2,y-1);
                break;

            default: // DOWN
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x,y+1);
                computedPoints[2] = new Point(x+1,y+1);
                computedPoints[3] = new Point(x+1,y+2);
                break;
        }

        return computedPoints;

    }
}
