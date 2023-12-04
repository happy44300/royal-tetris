package game.tetris.datastructure;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.function.Consumer;
import java.util.function.Function;

public interface TetrisGrid{

	<T> T updateGridSynchronously(Function<TetrisGrid, T> gridFunction);

	Cell getCell(Point point);

	int getRows();

	int getColumns();

	void updateGridSynchronously(Consumer<TetrisGrid> gridConsumer);

	void removeCompletedLines();
}
