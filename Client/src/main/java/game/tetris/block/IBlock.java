package game.tetris.block;

import game.tetris.datastructure.Point;
import game.tetris.datastructure.Rotation;
import game.tetris.datastructure.TetrisColor;
import game.tetris.datastructure.TetrisGrid;

import java.rmi.RemoteException;

public class IBlock extends ClientBlock{
    public IBlock(int x, int y, TetrisGrid grid){
        super(x, y, grid);

        points[0] = new Point(x,y);
        points[1] = new Point(x,y-1);
        points[2] = new Point(x,y+1);
        points[3] = new Point(x,y+2);
        COLOR = TetrisColor.YELLOW;
        try {
            paint();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void rotate(Rotation dir) {
        //null op
    }



    @Override
    public boolean canRotate(Rotation dir) {
        return true;
    }
}
