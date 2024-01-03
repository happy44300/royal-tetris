package game.tetris;

import game.tetris.action.PlayerAction;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Game extends Remote {
    public void submitBlockUpdate(String playerID, PlayerAction action) throws RemoteException;
    public String connect() throws RemoteException;
}
