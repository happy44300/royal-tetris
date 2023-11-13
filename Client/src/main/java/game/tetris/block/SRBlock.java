package game.tetris.block;

import game.tetris.datastructure.Point;
import game.tetris.datastructure.Rotation;
import game.tetris.datastructure.TetrisColor;
import game.tetris.datastructure.TetrisGrid;

import java.rmi.RemoteException;

public class SRBlock extends ClientBlock{
    public SRBlock(int x, int y, TetrisGrid grid) {
        super(x, y, grid);
        points[0] = new Point(x,y);
        points[1] = new Point(x,y+1);
        points[2] = new Point(x+1,y+1);
        points[3] = new Point(x+1,y+2);
        COLOR = TetrisColor.YELLOW;
        try {
            paint();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rotationCoordonate(Rotation dir){
        int x = points[0].getX();
        int y = points[0].getY();
        switch(dir){

            case RIGHT:
                points[0] = new Point(x,y);
                points[1] = new Point(x,y-1);
                points[2] = new Point(x-1,y-1);
                points[3] = new Point(x-1,y-2);
                break;

            case UP:
                points[0] = new Point(x,y);
                points[1] = new Point(x-1,y);
                points[2] = new Point(x-1,y+1);
                points[3] = new Point(x-2,y+1);
                break;

            case DOWN:
                points[0] = new Point(x,y);
                points[1] = new Point(x+1,y);
                points[2] = new Point(x+1,y-1);
                points[3] = new Point(x+2,y-1);
                break;
            default:
                points[0] = new Point(x,y);
                points[1] = new Point(x,y+1);
                points[2] = new Point(x+1,y+1);
                points[3] = new Point(x+1,y+2);
                break;
        }
    }



    @Override
    public boolean canRotate(Rotation dir) {
        return true;
    }
}
