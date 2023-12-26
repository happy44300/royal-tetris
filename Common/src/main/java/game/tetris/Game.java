package game.tetris;

import game.tetris.datastructure.AbstractBlock;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Game extends Remote {
    public void submitGameAction(Runnable gameAction) throws RemoteException;
    public AbstractBlock getBlock() throws RemoteException;
    public void play();
}
