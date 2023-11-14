package game.tetris;

import game.tetris.datastructure.TetrisGrid;

import java.util.function.Consumer;
import java.util.function.Function;

public interface Game {
    public void submitGameAction(Consumer<TetrisGrid> tetrisGridObjectFunction);
}
