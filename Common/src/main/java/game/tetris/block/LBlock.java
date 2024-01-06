package game.tetris.block;

import game.tetris.grid.Point;
import game.tetris.action.Rotate;
import game.tetris.action.Translate;
import game.tetris.grid.TetrisColor;

public class LBlock extends Block {
    public LBlock() {
        this.type = BlockType.LBLOCK;
    }

    public LBlock(int x, int y) {
        this.type = BlockType.LBLOCK;
        this.color = TetrisColor.ORANGE;
        this.points[0] = new Point(x,y);
        this.points[1] = new Point(x,y+1);
        this.points[2] = new Point(x-1,y+1);
        this.points[3] = new Point(x-2,y+1);
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
            case DOWN:
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x-1,y);
                computedPoints[2] = new Point(x-1,y-1);
                computedPoints[3] = new Point(x-1,y-2);
                break;

            case LEFT:
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x,y+1);
                computedPoints[2] = new Point(x-1,y+1);
                computedPoints[3] = new Point(x-2,y+1);
                break;

            case RIGHT:
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x,y-1);
                computedPoints[2] = new Point(x+1,y-1);
                computedPoints[3] = new Point(x+2,y-1);
                break;

            default: // UP
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x-1,y);
                computedPoints[2] = new Point(x-1,y-1);
                computedPoints[3] = new Point(x-1,y-2);
                break;
        }

        return computedPoints;
    }

    @Override
    public Point[] computeTranslation(Translate translate) {
        return new Point[0];
    }
}
