package game.tetris;

import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.Point;
import game.tetris.datastructure.Rotation;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Client extends Remote {
    public void blockDescentUpdate();

    public void blockUpdate(AbstractBlock block);

    public void lockBlockUpdate(AbstractBlock lockedBlock, AbstractBlock newBlock);
}
