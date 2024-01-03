package game.tetris;

import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.BlockBluePrint;

import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

public interface UpdateHandler extends Remote {
    public void blockDescentUpdate() throws RemoteException;

    public void provideStartingBlocks(Map<String, BlockBluePrint> startingBlocks) throws RemoteException;

    public void blockUpdate(BlockBluePrint updatedBlock, String id) throws RemoteException;

    public void lockBlockUpdate(BlockBluePrint newBlock, String id) throws RemoteException;

    public void handleLineRemoval(List<Integer> linesToRemove) throws RemoteException;

    public void setGamePort(int port, String ip) throws RemoteException;

    public void setID(String ID) throws RemoteException;
}
