package game.tetris;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
    public void deliver(String message) throws RemoteException;
}
