package game.tetris;

import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.Point;
import game.tetris.datastructure.TetrisGrid;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.function.Consumer;
import java.util.function.Function;

public interface Game extends Remote {
    public void submitGameAction(Consumer<TetrisGrid> tetrisGridObjectFunction) throws RemoteException;
    public AbstractBlock getBlock() throws RemoteException;
}
