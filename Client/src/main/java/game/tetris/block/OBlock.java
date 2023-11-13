package game.tetris.block;

import game.tetris.datastructure.*;
import game.tetris.tetrominos.TetrominosTexture;

import java.rmi.RemoteException;
import java.security.InvalidParameterException;

public class OBlock extends ClientBlock {
    public OBlock(int x, int y, TetrisGrid grid) {
        super(x, y, grid);

        points[0] = new Point(x,y);
        points[1] = new Point(x+1,y);
        points[2] = new Point(x,y+1);
        points[3] = new Point(x+1,y+1);
        COLOR = TetrisColor.YELLOW;
        try {
            paint();
        } catch (RemoteException e) {
            throw new RuntimeException(e);
        }
    }




    @Override
    public void rotationCoordonate(Rotation dir){
        return;
    }


    @Override
    public boolean canRotate(Rotation dir) {
        return true;
    }
}
