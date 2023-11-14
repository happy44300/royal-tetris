package game.tetris.block;

import game.tetris.datastructure.*;

import java.rmi.RemoteException;

public class IBlock extends ServerBlock{
    public IBlock(int x, int y, TetrisGrid grid){
        super(x, y, grid);

        points[0] = new Point(x,y);
        points[1] = new Point(x,y-1);
        points[2] = new Point(x,y+1);
        points[3] = new Point(x,y+2);
        COLOR = TetrisColor.TURQUOISE;
    }

    @Override
    public void rotate(Rotation dir) {
        //TODO
    }

    @Override
    public boolean canRotate(Rotation dir) {
        //TODO
        return false;
    }
}
