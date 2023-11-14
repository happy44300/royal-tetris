package game.tetris.block;

import game.tetris.datastructure.*;

import java.rmi.RemoteException;

public class TBlock extends ServerBlock{
    public TBlock(int x, int y, ServerTetrisGrid grid){
        super(x, y, grid);

        points[0] = new Point(x,y);
        points[1] = new Point(x+1,y);
        points[2] = new Point(x+1,y+1);
        points[3] = new Point(x+1,y-1);
        COLOR = TetrisColor.PURPLE;
    }

    @Override
    public void translate(Point point) throws RemoteException {
        //TODO
    }

    @Override
    public void rotate(Rotation dir) {
        //TODO
    }

    @Override
    public boolean canTranslate(Point point) throws RemoteException {
        //TODO
        return false;
    }


    @Override
    public boolean canRotate(Rotation dir) {
        //TODO
        return false;
    }
}
