package game.tetris.block;

import game.tetris.datastructure.*;

import java.rmi.RemoteException;

public class LRBlock extends ServerBlock{
    public LRBlock(int x, int y, ServerTetrisGrid grid){
        super(x, y, grid);

        points[0] = new Point(x,y);
        points[1] = new Point(x+1,y);
        points[2] = new Point(x+1,y+1);
        points[3] = new Point(x+1,y+2);
        COLOR = TetrisColor.BLUE;
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
