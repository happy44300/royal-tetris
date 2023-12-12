package game.tetris.block;

import game.tetris.datastructure.*;

import java.util.function.Consumer;

public class SBlock extends ServerBlock{
    public SBlock(int x, int y, TetrisGrid grid) {
        super(x, y, grid);

        points[0] = new Point(x,y);
        points[1] = new Point(x,y-1);
        points[2] = new Point(x+1,y-1);
        points[3] = new Point(x+1,y-2);
        COLOR = TetrisColor.GREEN;
    }

    @Override
    public Runnable rotate(Rotation dir) throws Exception {
        return () -> {
            int x = points[0].getX();
            int y = points[0].getY();

            //We check if the rotation is valid within the rotate method
            if(!canRotate(dir)) return;

            switch(dir){
                case RIGHT:
                    points[0] = new Point(x,y);
                    points[1] = new Point(x,y+1);
                    points[2] = new Point(x-1,y+1);
                    points[3] = new Point(x-1,y+2);
                    break;

                case UP:
                    points[0] = new Point(x,y);
                    points[1] = new Point(x-1,y);
                    points[2] = new Point(x-1,y-1);
                    points[3] = new Point(x-2,y-1);
                    break;

                case DOWN:
                    points[0] = new Point(x,y);
                    points[1] = new Point(x+1,y);
                    points[2] = new Point(x+1,y+1);
                    points[3] = new Point(x+2,y+1);
                    break;

                default: // LEFT
                    points[0] = new Point(x,y);
                    points[1] = new Point(x,y-1);
                    points[2] = new Point(x+1,y-1);
                    points[3] = new Point(x+1,y-2);
                    break;
            }
        };
    }

    @Override
    public boolean canRotate(Rotation dir) {
        int x = points[0].getX();
        int y = points[0].getY();
        Point[] pointsToCheck = new Point[4];

        switch(dir){
            case RIGHT:
                pointsToCheck[0] = new Point(x,y);
                pointsToCheck[1] = new Point(x,y+1);
                pointsToCheck[2] = new Point(x-1,y+1);
                pointsToCheck[3] = new Point(x-1,y+2);
                return this.ArePointsWithinBounds(pointsToCheck)
                        && this.ArePointsNotOnBlockedCells(pointsToCheck);

            case UP:
                pointsToCheck[0] = new Point(x,y);
                pointsToCheck[1] = new Point(x-1,y);
                pointsToCheck[2] = new Point(x-1,y-1);
                pointsToCheck[3] = new Point(x-2,y-1);
                return this.ArePointsWithinBounds(pointsToCheck)
                        && this.ArePointsNotOnBlockedCells(pointsToCheck);

            case DOWN:
                pointsToCheck[0] = new Point(x,y);
                pointsToCheck[1] = new Point(x+1,y);
                pointsToCheck[2] = new Point(x+1,y+1);
                pointsToCheck[3] = new Point(x+2,y+1);
                return this.ArePointsWithinBounds(pointsToCheck)
                        && this.ArePointsNotOnBlockedCells(pointsToCheck);

            default: // LEFT
                pointsToCheck[0] = new Point(x,y);
                pointsToCheck[1] = new Point(x,y-1);
                pointsToCheck[2] = new Point(x+1,y-1);
                pointsToCheck[3] = new Point(x+1,y-2);
                return this.ArePointsWithinBounds(pointsToCheck)
                        && this.ArePointsNotOnBlockedCells(pointsToCheck);
        }
    }
}
