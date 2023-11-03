package game.tetris.datastructure;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Cell extends Remote {

    Point getPos() throws RemoteException;

}