package game.tetris.datastructure;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cell{

    Point getPos() throws RemoteException;

    void setColor(TetrisColor color) throws RemoteException;

    TetrisColor getColor() throws RemoteException;

}
