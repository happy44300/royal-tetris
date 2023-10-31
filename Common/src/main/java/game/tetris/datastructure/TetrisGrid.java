package game.tetris.datastructure;

import java.util.function.Consumer;

public interface TetrisGrid {
	void updateGridSynchronously(Consumer<TetrisGrid> gridConsumer);
	void updateGridUnsafe(Consumer<TetrisGrid> gridConsumer);
}
