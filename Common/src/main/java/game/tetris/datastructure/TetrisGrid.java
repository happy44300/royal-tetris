package game.tetris.datastructure;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.function.Consumer;
import java.util.function.Function;

public interface TetrisGrid extends Remote {

	<T> T updateGridSynchronously(Function<TetrisGrid, T> gridConsumer) throws RemoteException;

	Cell getCell(Point point) throws RemoteException;

	int getRows();

	int getColumns();

	void updateGridSynchronously(Consumer<TetrisGrid> tetrisGridObjectFunction)throws RemoteException;
}
