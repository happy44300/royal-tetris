package game.tetris;

import game.tetris.datastructure.TetrisGrid;

import java.rmi.RemoteException;
import java.util.function.Consumer;

public class ServerTetrisGrid implements TetrisGrid {


    @Override
    public void updateGridSynchronously(Consumer<TetrisGrid> gridConsumer) throws RemoteException {

    }

    @Override
    public void updateGridUnsafe(Consumer<TetrisGrid> gridConsumer) throws RemoteException {

    }
}
