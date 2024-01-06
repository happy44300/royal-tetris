package game.tetris.block;

import game.tetris.grid.Point;
import game.tetris.action.Rotate;
import game.tetris.grid.TetrisColor;

public class TBlock extends Block {
    public TBlock() {
        this.type = BlockType.TBLOCK;
    }

    public TBlock(int x, int y) {
        this.type = BlockType.TBLOCK;
        this.color = TetrisColor.PURPLE;
        this.points[0] = new Point(x,y);
        this.points[1] = new Point(x-1,y+1);
        this.points[2] = new Point(x-1,y);
        this.points[3] = new Point(x-1,y-1);
    }

    @Override
    public Point[] computeRotation(Rotate rotate) {
        Point[] computedPoints = new Point[4];
        int x = this.points[0].getX();
        int y = this.points[0].getY();

        Orientation newOrientation = computeNewOrientation(rotate);

        switch(newOrientation){
            case LEFT:
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x-1,y+1);
                computedPoints[2] = new Point(x-1,y);
                computedPoints[3] = new Point(x-1,y-1);
                break;

            case UP:
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x-1,y-1);
                computedPoints[2] = new Point(x,y-1);
                computedPoints[3] = new Point(x+1,y-1);
                break;

            case DOWN:
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x+1,y+1);
                computedPoints[2] = new Point(x,y+1);
                computedPoints[3] = new Point(x-1,y+1);
                break;

            default: // RIGHT
                computedPoints[0] = new Point(x,y);
                computedPoints[1] = new Point(x+1,y-1);
                computedPoints[2] = new Point(x+1,y);
                computedPoints[3] = new Point(x+1,y+1);
                break;
        }

        return computedPoints;

    }
}
