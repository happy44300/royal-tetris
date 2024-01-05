package game.tetris;

import game.tetris.block.Block;
import game.tetris.grid.Grid;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectionManager extends Remote {
    void updateGrid(Grid updatedGrid) throws RemoteException;
    void updateBlock(String playerID, Block updatedBlock) throws RemoteException;
}
