package game.tetris;

import game.tetris.datastructure.TetrisGrid;

import java.util.function.Consumer;

public class ServerTetrisGrid implements TetrisGrid {



    @Override
    public void updateGridSynchronously(Consumer<TetrisGrid> gridConsumer) {

    }

    @Override
    public void updateGridUnsafe(Consumer<TetrisGrid> gridConsumer) {

    }
}
