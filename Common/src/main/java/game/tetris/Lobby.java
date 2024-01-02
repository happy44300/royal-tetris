package game.tetris;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface Lobby extends Remote {
    List<String> join(String playerName) throws RemoteException;
    void start() throws RemoteException;
}
