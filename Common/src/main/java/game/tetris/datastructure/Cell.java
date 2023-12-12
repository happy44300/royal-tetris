package game.tetris.datastructure;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cell{

    Point getPos();

    void setColor(TetrisColor color);

    TetrisColor getColor();

}
