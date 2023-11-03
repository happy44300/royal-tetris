package game.tetris.datastructure;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cell extends Remote {

    void setPos(Point point) throws RemoteException;

    Point getPos() throws RemoteException;

}
