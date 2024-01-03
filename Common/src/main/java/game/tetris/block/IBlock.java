package game.tetris.block;

import game.tetris.Orientation;
import game.tetris.Point;
import game.tetris.action.Rotate;

public class IBlock extends Block {
    public IBlock() {
        this.type = BlockType.IBLOCK;
    }

    public IBlock(int x, int y) {
        this.type = BlockType.IBLOCK;

        this.points[0] = new Point(x,y);
        this.points[1] = new Point(x,y-1);
        this.points[2] = new Point(x,y-2);
        this.points[3] = new Point(x,y-3);
    }

    @Override
    public Point[] getPoints() {
        return this.points;
    }

    @Override
    public Point[] computeRotation(Rotate rotate) {
        Point[] computedPoints = new Point[4];
        int x = this.points[0].getX();
        int y = this.points[0].getY();

        Orientation newOrientation = computeNewOrientation(rotate);

        switch(newOrientation){
            case RIGHT:
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x,y+1);
                computedPoints[2] = new Point(x,y+2);
                computedPoints[3] = new Point(x,y+3);
                break;

            case UP:
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x-1,y);
                computedPoints[2] = new Point(x-2,y);
                computedPoints[3] = new Point(x-3,y);
                break;

            case DOWN:
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x+1,y);
                computedPoints[2] = new Point(x+2,y);
                computedPoints[3] = new Point(x+3,y);
                break;

            default: // LEFT
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x,y-1);
                computedPoints[2] = new Point(x,y-2);
                computedPoints[3] = new Point(x,y-3);
                break;
        }

        return computedPoints;
    }
}
