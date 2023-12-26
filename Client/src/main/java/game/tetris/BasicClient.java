package game.tetris;

import game.tetris.datastructure.AbstractBlock;
import game.tetris.datastructure.Point;
import game.tetris.datastructure.Rotation;

import java.rmi.RemoteException;

public class BasicClient implements Client{

    private void removeCompletedLines(){

    }

    @Override
    public void blockDescentUpdate() {

    }

    @Override
    public void blockRotateUpdate(AbstractBlock block, Rotation rotation) {

    }

    @Override
    public void blockTranslateUpdate(AbstractBlock block, Point point) {

    }

    @Override
    public void lockBlockUpdate(AbstractBlock lockedBlock, AbstractBlock newBlock) {

    }
}
