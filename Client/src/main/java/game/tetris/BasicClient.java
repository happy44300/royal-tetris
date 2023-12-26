package game.tetris;

import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.Point;
import game.tetris.datastructure.Rotation;
import game.tetris.datastructure.TetrisGrid;

import java.rmi.RemoteException;
import java.util.List;

public class BasicClient implements Client{

    @Override
    public void blockDescentUpdate() {

    }

    @Override
    public void blockUpdate(AbstractBlock block) {

    }

    @Override
    public void lockBlockUpdate(AbstractBlock lockedBlock, AbstractBlock newBlock) {

    }

    @Override
    public void handleLineRemoval(List<Integer> linesToRemove) {

    }

}
