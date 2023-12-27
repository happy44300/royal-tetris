package game.tetris.datastructure;

import java.util.List;

public interface TetrisGrid{
	Cell getCell(Point point);

	int getRows();

	int getColumns();

	void updateGrid(Runnable gridConsumer);

	List<Integer> removeCompletedLines();

	void removeLine(int line);
}
