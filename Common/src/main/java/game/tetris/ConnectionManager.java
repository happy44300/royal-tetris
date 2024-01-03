package game.tetris;

import game.tetris.block.Block;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ConnectionManager extends Remote {
    void connect() throws RemoteException;
    void updateGrid(Grid updatedGrid) throws RemoteException;
    void updateBlock(String playerID, Block updatedBlock);
}
