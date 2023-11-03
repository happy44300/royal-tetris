package game.tetris.datastructure;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.function.Consumer;

public interface TetrisGrid extends Remote {
	void updateGridSynchronously(Consumer<TetrisGrid> gridConsumer) throws RemoteException;
	void updateGridUnsafe(Consumer<TetrisGrid> gridConsumer) throws RemoteException;
}
