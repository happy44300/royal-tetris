package game.tetris;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Game extends Remote {
    public void submitBlockUpdate(String playerID, PlayerAction action) throws RemoteException;
    public String connect();
}
