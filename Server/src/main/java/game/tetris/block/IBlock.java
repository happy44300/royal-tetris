package game.tetris.block;

import game.tetris.datastructure.*;

import java.util.function.Consumer;

public class IBlock extends ServerBlock{
    public IBlock(int x, int y, TetrisGrid grid){
        super(x, y, grid);

        points[0] = new Point(x,y);
        points[1] = new Point(x,y-1);
        points[2] = new Point(x,y-2);
        points[3] = new Point(x,y-3);
        COLOR = TetrisColor.TURQUOISE;
        this.type = BlockType.IBLOCK;
    }

    @Override
    public Runnable rotate(Rotation dir) throws Exception {
        return () -> {
            int x = points[0].getX();
            int y = points[0].getY();
            Rotation NewDir = rotateClockwise(dir);
            //We check if the rotation is valid within the rotate method
            if(!canRotate(NewDir)) return;

            switch(NewDir){
                case RIGHT:
                    points[0] = new Point(x,y);
                    points[1] = new Point(x,y+1);
                    points[2] = new Point(x,y+2);
                    points[3] = new Point(x,y+3);
                    break;

                case UP:
                    points[0] = new Point(x,y);
                    points[1] = new Point(x-1,y);
                    points[2] = new Point(x-2,y);
                    points[3] = new Point(x-3,y);
                    break;

                case DOWN:
                    points[0] = new Point(x,y);
                    points[1] = new Point(x+1,y);
                    points[2] = new Point(x+2,y);
                    points[3] = new Point(x+3,y);
                    break;

                default: // LEFT
                    points[0] = new Point(x,y);
                    points[1] = new Point(x,y-1);
                    points[2] = new Point(x,y-2);
                    points[3] = new Point(x,y-3);
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
                pointsToCheck[2] = new Point(x,y+2);
                pointsToCheck[3] = new Point(x,y+3);
                return this.ArePointsWithinBounds(pointsToCheck)
                        && this.ArePointsNotOnBlockedCells(pointsToCheck);

            case UP:
                pointsToCheck[0] = new Point(x,y);
                pointsToCheck[1] = new Point(x-1,y);
                pointsToCheck[2] = new Point(x-2,y);
                pointsToCheck[3] = new Point(x-3,y);
                return this.ArePointsWithinBounds(pointsToCheck)
                        && this.ArePointsNotOnBlockedCells(pointsToCheck);

            case DOWN:
                pointsToCheck[0] = new Point(x,y);
                pointsToCheck[1] = new Point(x+1,y);
                pointsToCheck[2] = new Point(x+2,y);
                pointsToCheck[3] = new Point(x+3,y);
                return this.ArePointsWithinBounds(pointsToCheck)
                        && this.ArePointsNotOnBlockedCells(pointsToCheck);

            default: // LEFT
                pointsToCheck[0] = new Point(x,y);
                pointsToCheck[1] = new Point(x,y-1);
                pointsToCheck[2] = new Point(x,y-2);
                pointsToCheck[3] = new Point(x,y-3);
                return this.ArePointsWithinBounds(pointsToCheck)
                        && this.ArePointsNotOnBlockedCells(pointsToCheck);
        }
    }
}
