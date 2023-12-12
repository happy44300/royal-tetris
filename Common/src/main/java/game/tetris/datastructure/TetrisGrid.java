package game.tetris.datastructure;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public interface TetrisGrid{
	Cell getCell(Point point);

	int getRows();

	int getColumns();

	void updateGrid(Runnable gridConsumer);

	Collection<Integer> removeCompletedLines();
}
