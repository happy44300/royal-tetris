package game.tetris;

import game.tetris.datastructure.AbstractBlock;

import java.rmi.Remote;
import java.util.List;
import java.util.Map;

public interface UpdateHandler extends Remote {
    public void blockDescentUpdate();

    public void provideStartingBlocks(Map<String, AbstractBlock> startingBlocks);

    public void blockUpdate(AbstractBlock block, String id);

    public void lockBlockUpdate(AbstractBlock lockedBlock, AbstractBlock newBlock);

    public void handleLineRemoval(List<Integer> linesToRemove);

    public void setGame(Game game);
}
