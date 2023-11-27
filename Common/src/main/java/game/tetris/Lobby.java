package game.tetris;

import game.tetris.datastructure.TetrisGrid;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Lobby extends Remote {
    void join(String playerName) throws RemoteException;
    void start() throws RemoteException;
    Game getGame() throws RemoteException;
}
