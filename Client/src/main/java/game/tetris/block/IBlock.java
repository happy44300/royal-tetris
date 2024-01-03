package game.tetris.block;

import game.tetris.datastructure.*;

import java.rmi.RemoteException;

public class IBlock extends ClientBlock{
    public IBlock(int x, int y, TetrisGrid grid){
        super(x, y, grid);

        points[0] = new Point(x,y);
        points[1] = new Point(x,y-1);
        points[2] = new Point(x,y-2);
        points[3] = new Point(x,y-3);
        COLOR = TetrisColor.TURQUOISE;
        this.type = BlockType.IBLOCK;
        try {
            paint();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void rotationCoordinate(Rotation dir){
        int x = points[0].getX();
        int y = points[0].getY();
        switch(dir){

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
            default:
                points[0] = new Point(x,y);
                points[1] = new Point(x,y-1);
                points[2] = new Point(x,y-2);
                points[3] = new Point(x,y-3);
                break;
        }
    }



    @Override
    public boolean canRotate(Rotation dir) {
        return true;
    }
}
