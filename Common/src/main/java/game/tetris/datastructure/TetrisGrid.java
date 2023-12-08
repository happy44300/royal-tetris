package game.tetris.datastructure;

import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.Function;

public interface TetrisGrid{

	<T> T updateGrid(Function<TetrisGrid, T> gridFunction);

	Cell getCell(Point point);

	int getRows();

	int getColumns();

	void updateGrid(Consumer<TetrisGrid> gridConsumer);

	Collection<Integer> removeCompletedLines();
}
