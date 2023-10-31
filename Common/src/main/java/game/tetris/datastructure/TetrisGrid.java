package game.tetris.datastructure;

import java.rmi.Remote;
import java.util.function.Consumer;

public interface TetrisGrid extends Remote {
	void updateGridSynchronously(Consumer<TetrisGrid> gridConsumer);
	void updateGridUnsafe(Consumer<TetrisGrid> gridConsumer);
}
